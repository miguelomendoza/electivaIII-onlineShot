/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paronlineapi.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.Serializable;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import paronlineapi.model.MessageErrors;
import org.json.JSONObject;
import paronlineapi.entity.Cliente;
import paronlineapi.service.ClienteService;

/**
 *
 * @author mmendoza
 */
@Path("/acceso")
public class AccesoController implements Serializable {
    
    ClienteService clienteService = new ClienteService();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String json) {
        JsonElement je;
        JsonObject jo;
        JSONObject jsonObject = new JSONObject(json);
        
        String passwd, loginName;
        
        try {
            if (!jsonObject.has("login_name")) {
                je = (new Gson()).toJsonTree(new MessageErrors(400, "Falta parametros login_name!!"));
                jo = new JsonObject();
                jo.add("Retorno", je);
                return Response.status(400).entity((new Gson()).toJson(jo)).build();
            }
            if (!jsonObject.has("passwd")) {
                je = (new Gson()).toJsonTree(new MessageErrors(400, "Falta parametros password"));
                jo = new JsonObject();
                jo.add("Retorno", je);
                return Response.status(400).entity((new Gson()).toJson(jo)).build();
            }
            
        } catch (Exception e) {
            je = (new Gson()).toJsonTree(new MessageErrors(500, "Error interno del Servicio"));
            jo = new JsonObject();
            jo.add("Retorno", je);
            return Response.status(500).entity((new Gson()).toJson(jo)).build();
        }
        
        loginName = jsonObject.getString("login_name");
        passwd = jsonObject.getString("passwd");
        
        Cliente access = clienteService.getByid(loginName, passwd);
        
        if (access != null) {
            je = (new Gson()).toJsonTree(access);
            jo = new JsonObject();
            jo.add("Retorno", je);
            return Response.status(200).entity((new Gson()).toJson(jo)).build();
        } else {
            je = (new Gson()).toJsonTree(new MessageErrors(405, "Acceso incorrecto!!"));
            jo = new JsonObject();
            jo.add("Retorno", je);
            return Response.status(405).entity((new Gson()).toJson(jo)).build();
        }
    }
}
