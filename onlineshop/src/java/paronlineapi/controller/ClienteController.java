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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import paronlineapi.model.MessageErrors;
import paronlineapi.entity.Cliente;
import paronlineapi.service.ClienteService;

/**
 *
 * @author mmendoza
 */
@Path("/clienteRest")
public class ClienteController implements Serializable {

    ClienteService clienteService = new ClienteService();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setCliente(Cliente clienteBody) {
        JsonElement je;
        JsonObject jo;
        try {

            boolean resultado = clienteService.insertar(clienteBody);

            if (resultado != false) {
                je = (new Gson()).toJsonTree(new MessageErrors(200, "Datos insertados Correctamente"));
                jo = new JsonObject();
                jo.add("ClienteInsert", je);
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

    @GET
    @Path("/listaClientes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getClientesList() {
        JsonElement je;
        JsonObject jo;
        try {
            List<Cliente> clienteList = clienteService.getAll();
            if (!clienteList.isEmpty()) {
                je = (new Gson()).toJsonTree(clienteList);
                jo = new JsonObject();
                jo.add("ClienteList", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de clientes"));
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyCliente(Cliente clienteBody) {
        JsonElement je;
        JsonObject jo;
        try {

            boolean resultado = clienteService.update(clienteBody);

            if (resultado != false) {
                je = (new Gson()).toJsonTree(new MessageErrors(200, "Datos modificados Correctamente"));
                jo = new JsonObject();
                jo.add("ClienteModify", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(422, "Error al modificar los datos cliente"));
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

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCliente(int clienteId) {
        JsonElement je;
        JsonObject jo;
        try {

            boolean resultado = clienteService.delete(clienteId);

            if (resultado != false) {
                je = (new Gson()).toJsonTree(new MessageErrors(200, "Datos Borrados Correctamente"));
                jo = new JsonObject();
                jo.add("ClienteDelete", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(422, "Error al borrar los datos cliente"));
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
