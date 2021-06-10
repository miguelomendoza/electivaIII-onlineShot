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
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import paronlineapi.model.MessageErrors;
import paronlineapi.entity.TransaccionesDet;
import paronlineapi.service.TransaccionDetService;

/**
 *
 * @author mmendoza
 */
@Path("/transaccionDetRest")
public class TransaccionDetController implements Serializable {

    TransaccionDetService transaccionDetService = new TransaccionDetService();

    // servicio que retorna la lista de categorias
    @GET
    @Path("/listaTransaccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTransaccionList() {
        JsonElement je;
        JsonObject jo;
        try {
            List<TransaccionesDet> transaccionDetList = transaccionDetService.getAll();

            if (!transaccionDetList.isEmpty()) {
                je = (new Gson()).toJsonTree(transaccionDetList);
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

    @GET
    @Path("/listaDetalle/{idTransaccion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getDetalleByTransaccionId(@PathParam("idTransaccion") int idTransaccion) {
        JsonElement je;
        JsonObject jo;
        try {
            List<TransaccionesDet> transaccionDetList = transaccionDetService.getByTransaccionId(idTransaccion);

            if (!transaccionDetList.isEmpty()) {
                je = (new Gson()).toJsonTree(transaccionDetList);
                jo = new JsonObject();
                jo.add("DetailList", je);
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
}
