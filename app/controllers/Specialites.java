package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.Database;
import play.db.NamedDatabase;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import services.Specialite;

import javax.inject.Inject;
import java.util.List;

public class Specialites extends Controller {
    private Database db;

    @Inject
    public Specialites(@NamedDatabase("default") Database db){
        this.db = db;
    }

    public Result list(){
        List<Specialite> specialites = services.Specialite.findAll();
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/specialites/");
        result.put("status", 200);
        result.put("specialite", Json.toJson(specialites));
        return ok(result);
    }

    public Result read(String id){
        if(id == null){
            return notFound(String.format("Specialite %s does not exist.", id));
        }
        if(id.isEmpty()){
            return notFound(String.format("Specialite %s does not exist.", id));
        }
        services.Specialite specialite = services.Specialite.findById(id);
        if(specialite == null){
            return notFound(String.format("Specialite %s does not exist.", id));
        }
        return  ok(Json.toJson(specialite));
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
                services.Specialite specialite = Json.fromJson(json, services.Specialite.class);
                services.Specialite.save(specialite);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/specialites/");
                result.put("status", 202);
                result.put("specialite", Json.toJson(specialite).toString());
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
                services.Specialite specialite = Json.fromJson(json, services.Specialite.class);
                if(!specialite.getId().equals(id)){
                    return notFound("User not found");
                }
                services.Specialite.update(specialite);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/specialites/"+id);
                result.put("status", 200);
                result.put("specialite", Json.toJson(specialite).toString());
                return ok(result);
            }
        }
    }

    public Result delete(String id){
        if(id == null){
            return notFound(String.format("Specialite %s does not exist.", id));
        }
        if (id.isEmpty()){
            return notFound(String.format("Specialite %s does not exist.", id));
        }
        services.Specialite specialite = services.Specialite.findById(id);
        if(specialite == null){
            return notFound(String.format("Specialite %s does not exist.", id));
        }
        services.Specialite.remove(specialite);
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/specialites/"+id);
        result.put("status", 200);
        result.put("specialite", Json.toJson(specialite).toString());
        return ok(result);
    }
}
