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
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import paronlineapi.model.MessageErrors;
import paronlineapi.entity.TransaccionesCab;
import paronlineapi.service.TransaccionCabService;

/**
 *
 * @author mmendoza
 */
@Path("/transaccionCabRest")
public class TransaccionCabController implements Serializable {

    TransaccionCabService transaccionService = new TransaccionCabService();

    // servicio que retorna la lista de categorias
    @GET
    @Path("/listaTransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTransaccionList() {
        JsonElement je;
        JsonObject jo;
        try {
            List<TransaccionesCab> transaccionCabList = transaccionService.getAll();

            if (!transaccionCabList.isEmpty() && transaccionCabList != null) {
                je = (new Gson()).toJsonTree(transaccionCabList);
                jo = new JsonObject();
                jo.add("TransaccionesList", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de transaccion"));
                jo = new JsonObject();
                jo.add("Retorno", je);
                return Response.status(404).entity((new Gson()).toJson(jo)).build();
            }

        } catch (Exception e) {
            je = (new Gson()).toJsonTree(new MessageErrors(500, "Error interno del Servicio"));
            jo = new JsonObject();
            jo.add("Retorno", je);
            return Response.status(500).entity((new Gson()).toJson(jo)).build();
        }
    }
    
    // servicio que retorna la lista de categorias
    @GET
    @Path("/listaTransaccion/{idTransaccion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaccionById(@PathParam("idTransaccion") int idTransaccion) {
        JsonElement je;
        JsonObject jo;
        try {
            List<TransaccionesCab> transaccionCab = transaccionService.getByTransaccionesCabId(idTransaccion);

            if (transaccionCab != null) {
                je = (new Gson()).toJsonTree(transaccionCab);
                jo = new JsonObject();
                jo.add("TransaccionesList", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de transaccion"));
                jo = new JsonObject();
                jo.add("Retorno", je);
                return Response.status(404).entity((new Gson()).toJson(jo)).build();
            }

        } catch (Exception e) {
            je = (new Gson()).toJsonTree(new MessageErrors(500, "Error interno del Servicio"));
            jo = new JsonObject();
            jo.add("Retorno", je);
            return Response.status(500).entity((new Gson()).toJson(jo)).build();
        }
    }
    
     // servicio que retorna la lista de categorias
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setTransaccion(TransaccionesCab transaccionesCab) {
        JsonElement je;
        JsonObject jo;
        try {
            boolean transaccion = transaccionService.insertar(transaccionesCab);

            if (transaccion != false) {
                je = (new Gson()).toJsonTree(new MessageErrors(200, "Datos insertados Correctamente"));
                jo = new JsonObject();
                jo.add("TransaccionInsert", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(422, "Error al insertar los datos"));
                jo = new JsonObject();
                jo.add("Retorno", je);
                return Response.status(422).entity((new Gson()).toJson(jo)).build();
            }
        } catch (NumberFormatException e) {
            je = (new Gson()).toJsonTree(new MessageErrors(500, "Error interno del Servicio"));
            jo = new JsonObject();
            jo.add("Retorno", je);
            return Response.status(500).entity((new Gson()).toJson(jo)).build();
        }
    }
}
