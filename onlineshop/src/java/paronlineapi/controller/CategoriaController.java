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
import paronlineapi.entity.Categoria;
import paronlineapi.service.CategoriaService;

/**
 *
 * @author mmendoza
 */
@Path("/categoriaRest")
public class CategoriaController implements Serializable {

    CategoriaService categoriaService = new CategoriaService();

    // servicio que retorna la lista de categorias
    @GET
    @Path("/listaCategoria")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCategoriaList() {
        JsonElement je;
        JsonObject jo;
        try {
            List<Categoria> categoriaList = categoriaService.getAll();

            if (!categoriaList.isEmpty()) {
                je = (new Gson()).toJsonTree(categoriaList);
                jo = new JsonObject();
                jo.add("CategoriaList", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de categorias"));
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

    //Servicio que retorna categorias por id
    @GET
    @Path("/categoria/{codigoCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCategoria(@PathParam("codigoCategoria") int codigoCategoria) {
        JsonElement je;
        JsonObject jo;
        try {
            Categoria categoria = categoriaService.getCategoriaById(codigoCategoria);

            if (categoria != null) {
                je = (new Gson()).toJsonTree(categoria);
                jo = new JsonObject();
                jo.add("Categoria", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de categorias"));
                jo = new JsonObject();
                jo.add("Retorno", je);
                return Response.status(404).entity((new Gson()).toJson(jo)).build();
            }
        } catch (NumberFormatException e) {
            je = (new Gson()).toJsonTree(new MessageErrors(500, "Error interno del Servicio"));
            jo = new JsonObject();
            jo.add("Retorno", je);
            return Response.status(500).entity((new Gson()).toJson(jo)).build();
        }
    }

    private Response exceptionCatch(MessageErrors me, Integer statusCode) {
        JsonElement je = (new Gson()).toJsonTree(me);
        JsonObject jo = new JsonObject();
        jo.add("Mensaje", je);
        return Response.status(statusCode).entity((new Gson()).toJson(jo)).build();
    }
}
