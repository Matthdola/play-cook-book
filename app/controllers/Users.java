package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.io.Files;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.User;
import play.libs.Json;

import views.html.users.userdetails;
import views.html.users.userlist;
import views.html.users.userlogin;
import views.html.users.userpage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Users extends Controller {
    public Result list(){
        List<User> users = User.findAll();
        return ok(userlist.render(users));
    }


    public  Result newUser(){
        final Form<User> userForm = Form.form(User.class);
        return ok(userdetails.render(userForm));
    }

    public  Result details(String name){
        final Form<User> userForm = Form.form(User.class);
        final User user = User.findByEan(name);
        if(user == null){
            return notFound(String.format("User %s does not exist.", name));
        }

        Form<User> filledForm = userForm.fill(user);
        return ok(userdetails.render(filledForm));
    }

    public Result delete(String name){
        final Form<User> userForm = Form.form(User.class);
        final User user = User.findByEan(name);
        if(user == null){
            return notFound(String.format("User %s does not exist.", name));
        }

        User.remove(user);
        return redirect(routes.Users.list());
    }

    public  Result openUserPage(){
        final Form<User> userForm = Form.form(User.class);
        return ok(userpage.render(userForm));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public  Result save(){
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        if (name == null){
            return badRequest("Missing parameter [name]");
        }
        final Form<User> userForm = Form.form(User.class);
        Form<User> boundForm = userForm.bindFromRequest();

        if(boundForm.hasErrors()){
            flash("error", "Please correct the form below.");
            return badRequest(userdetails.render(boundForm));
        }

        User user = boundForm.get();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart part = body.getFile("picture");

        if(part != null) {
            File picture = (File) part.getFile();
            try {
                user.image = Files.toByteArray(picture);
            } catch (IOException e) {
                return internalServerError("Error reading file upload");
            }
        }
        user.save();
        return redirect(routes.Users.list());
    }

    public  Result newLogin(){
        final Form<User> userForm = Form.form(User.class);
        return ok(userlogin.render(userForm));

    }
//
    public  Result login(){
        final Form<User> userForm = Form.form(User.class);
        Form<User> boundForm = userForm.bindFromRequest();
        if(boundForm.hasErrors()){
            flash("error", "Please correct the form below.");
            return badRequest(userdetails.render(boundForm));
        }

        User user = boundForm.get();

        final User user1 = User.findByNameAndPassword(user.name, user.password);
        if(user1 == null){
            return notFound(String.format("User %s does not exist.", user.name));
        }

        return redirect(routes.Users.openUserPage());
    }

}
