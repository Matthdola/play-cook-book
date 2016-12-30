package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.Database;
import play.db.NamedDatabase;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

public class Medecins extends Controller {
    private Database db;

    @Inject
    public Medecins(@NamedDatabase("default") Database db){
        this.db = db;
    }

    public Result list(){
        List<services.Medecin> medecins = services.Medecin.findAll();
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/medecins/");
        result.put("status", 200);
        result.put("medecin", Json.toJson(medecins));
        return ok(result);
    }

    public Result listByCentre(String centre){
        List<services.Medecin> medecins = services.Medecin.listByCentre(centre);
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/medecins/");
        result.put("status", 200);
        result.put("medecin", Json.toJson(medecins));
        return ok(result);
    }


    public Result listBySpecialite(String specialite){
        List<services.Medecin> medecins = services.Medecin.listBySpecialite(specialite);
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/medecins/");
        result.put("status", 200);
        result.put("medecin", Json.toJson(medecins));
        return ok(result);
    }


    public Result read(String id){
        if(id == null){
            ObjectNode result = Json.newObject();
            result.put("uri", "/v1/medecins/"+id);
            result.put("status", 404);
            result.put("message", String.format("Medecin %s does not exist.", id));

            return notFound(result);
        }
        if(id.isEmpty()){
            ObjectNode result = Json.newObject();
            result.put("uri", "/v1/medecins/"+id);
            result.put("status", 404);
            result.put("message", String.format("Medecin %s does not exist.", id));

            return notFound(result);
        }
        services.Medecin medecin = services.Medecin.findById(id);
        if(medecin == null){
            ObjectNode result = Json.newObject();
            result.put("uri", "/v1/medecins/"+id);
            result.put("status", 404);
            result.put("message", String.format("Medecin %s does not exist.", id));

            return notFound(result);
        }
        return  ok(Json.toJson(medecin));
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
                services.Medecin medecin = Json.fromJson(json, services.Medecin.class);
                services.Medecin.save(medecin);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/medecins/");
                result.put("status", 202);
                result.put("medecin", Json.toJson(medecin).toString());
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
                services.Medecin medecin = Json.fromJson(json, services.Medecin.class);
                if(!medecin.getId().equals(id)){
                    return notFound("User not found");
                }
                services.Medecin.update(medecin);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/medecins/"+id);
                result.put("status", 200);
                result.put("medecin", Json.toJson(medecin).toString());
                return ok(result);
            }
        }
    }

    public Result delete(String id){
        if(id == null){
            return notFound(String.format("Medecin %s does not exist.", id));
        }
        if (id.isEmpty()){
            return notFound(String.format("Medecin %s does not exist.", id));
        }
        services.Medecin medecin = services.Medecin.findById(id);
        if(medecin == null){
            return notFound(String.format("Medecin %s does not exist.", id));
        }
        services.Medecin.remove(medecin);
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/medecins/"+id);
        result.put("status", 200);
        result.put("medecin", Json.toJson(medecin).toString());
        return ok(result);
    }
}
