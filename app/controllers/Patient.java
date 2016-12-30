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

public class Patient extends Controller {
    private Database db;

    @Inject
    public Patient(@NamedDatabase("default") Database db){
        this.db = db;
    }

    public Result list(){
        List<services.Patient> patients = services.Patient.findAll();
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/patients/");
        result.put("status", 200);
        result.put("patient", Json.toJson(patients));
        return ok(result);
    }

    public Result read(String id){
        if(id == null){
            return notFound(String.format("Medecin %s does not exist.", id));
        }
        if(id.isEmpty()){
            return notFound(String.format("Medecin %s does not exist.", id));
        }
        services.Patient patient = services.Patient.findById(id);
        if(patient == null){
            return notFound(String.format("Patient %s does not exist.", id));
        }
        return  ok(Json.toJson(patient));
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
                services.Patient patient = Json.fromJson(json, services.Patient.class);
                services.Patient.save(patient);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/patients/");
                result.put("status", 202);
                result.put("patient", Json.toJson(patient).toString());
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
                services.Patient patient = Json.fromJson(json, services.Patient.class);
                if(!patient.getId().equals(id)){
                    return notFound("User not found");
                }
                services.Patient.update(patient);
                ObjectNode result = Json.newObject();
                result.put("uri", "/v1/patients/"+id);
                result.put("status", 200);
                result.put("patient", Json.toJson(patient).toString());
                return ok(result);
            }
        }
    }

    public Result delete(String id){
        if(id == null){
            return notFound(String.format("Patient %s does not exist.", id));
        }
        if (id.isEmpty()){
            return notFound(String.format("Patient %s does not exist.", id));
        }
        services.Patient patient = services.Patient.findById(id);
        if(patient == null){
            return notFound(String.format("Patient %s does not exist.", id));
        }
        services.Patient.remove(patient);
        ObjectNode result = Json.newObject();
        result.put("uri", "/v1/patients/"+id);
        result.put("status", 200);
        result.put("patient", Json.toJson(patient).toString());
        return ok(result);
    }

}
