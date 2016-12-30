package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.Database;
import play.db.NamedDatabase;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.*;

import javax.inject.Inject;
import java.util.List;

public class Centres extends Controller {
    private Database db;

    @Inject
    public Centres(@NamedDatabase("default") Database db){
        this.db = db;
    }

    public Result list(){
        List<services.Centre> centres = services.Centre.findAll();
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/centres/");
        result.put("status", 200);
        result.put("centre", Json.toJson(centres));
        return ok(result);
    }

    public Result read(String id){
        if(id == null){
            return notFound(String.format("Centre %s does not exist.", id));
        }
        if(id.isEmpty()){
            return notFound(String.format("Centre %s does not exist.", id));
        }
        services.Centre centre = services.Centre.findById(id);
        if(centre == null){
            return notFound(String.format("Centre %s does not exist.", id));
        }
        return  ok(Json.toJson(centre));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result create(){
        JsonNode json = request().body().asJson();
        if(json == null){
            return badRequest("Expecting Json data");
        } else {
            String name = json.findPath("name").textValue();
            if(name == null){
                return badRequest("Missing parameter [name]");
            }else {
                services.Centre centre = Json.fromJson(json, services.Centre.class);
                services.Centre.save(centre);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/centres/");
                result.put("status", 202);
                result.put("centre", Json.toJson(centre).toString());
                return ok(result);
            }
        }
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result update(String id){
        JsonNode json = request().body().asJson();
        if(json == null){
            return badRequest("Expecting Json data");
        } else {
            String name = json.findPath("name").textValue();
            if(name == null){
                return badRequest("Missing parameter [name]");
            }else {
                services.Centre centre = Json.fromJson(json, services.Centre.class);
                if(!centre.getId().equals(id)){
                    return notFound("User not found");
                }
                services.Centre.update(centre);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/centres/"+id);
                result.put("status", 200);
                result.put("centre", Json.toJson(centre).toString());
                return ok(result);
            }
        }
    }

    public Result delete(String id){
        if(id == null){
            return notFound(String.format("Centre %s does not exist.", id));
        }
        if (id.isEmpty()){
            return notFound(String.format("Centre %s does not exist.", id));
        }
        services.Centre centre = services.Centre.findById(id);
        if(centre == null){
            return notFound(String.format("Centre %s does not exist.", id));
        }
        services.Centre.remove(centre);
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/centres/"+id);
        result.put("status", 200);
        result.put("centre", Json.toJson(centre).toString());
        return ok(result);
    }
}
