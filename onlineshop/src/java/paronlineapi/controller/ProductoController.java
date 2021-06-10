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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import paronlineapi.model.MessageErrors;
import paronlineapi.entity.Producto;
import paronlineapi.service.ProductoService;

/**
 *
 * @author mmendoza
 */
@Path("/productoRest")
public class ProductoController implements Serializable {

    ProductoService productoService = new ProductoService();

    // servicio que retorna la lista de productos
    @GET
    @Path("/listaProducto")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductoList() {
        JsonElement je;
        JsonObject jo;
        try {
            List<Producto> productoList = productoService.getAll();

            if (!productoList.isEmpty()) {
                je = (new Gson()).toJsonTree(productoList);
                jo = new JsonObject();
                jo.add("ProductoList", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de productos"));
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

    //Servicio que retorna productos por id
    @GET
    @Path("/producto/{codigoProducto}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductoByCodigo(@PathParam("codigoProducto") int codigoProducto) {
        JsonElement je;
        JsonObject jo;
        try {
            Producto producto = productoService.getProductoById(codigoProducto);

            if (producto != null) {
                je = (new Gson()).toJsonTree(producto);
                jo = new JsonObject();
                jo.add("Producto", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de producto"));
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

    //Servicio que retorna productos por id
    @GET
    @Path("/producto/desc/{descripcion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductoBydescripcion(@PathParam("descripcion") String descripcion) {
        JsonElement je;
        JsonObject jo;
        try {
            List<Producto> producto = productoService.getProductoByDescripcion(descripcion);

            if (producto != null) {
                je = (new Gson()).toJsonTree(producto);
                jo = new JsonObject();
                jo.add("Producto", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de producto"));
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

    //Servicio que retorna productos por id
    @GET
    @Path("/producto/categoria/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductoByCategoria(@PathParam("idCategoria") int idCategoria) {
        JsonElement je;
        JsonObject jo;
        try {
            List<Producto> producto = productoService.getProductoByIdCategoria(idCategoria);

            if (producto != null) {
                je = (new Gson()).toJsonTree(producto);
                jo = new JsonObject();
                jo.add("Producto", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de producto"));
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

    //Servicio que retorna productos por id
    @GET
    @Path("/producto/categoriaDescripcion/{idCategoria}/{descripcion}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getProductoByCategoriaDesc(@PathParam("idCategoria") int idCategoria,
            @PathParam("descripcion") String descripcion) {
        JsonElement je;
        JsonObject jo;
        try {
            List<Producto> producto = productoService.getProductoByIdCategoriaDescripcion(idCategoria, descripcion);

            if (producto != null) {
                je = (new Gson()).toJsonTree(producto);
                jo = new JsonObject();
                jo.add("Producto", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(404, "No existe registro de producto"));
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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setProducto(Producto bodyProducto) {
        JsonElement je;
        JsonObject jo;
        try {

            boolean resultado = productoService.insertar(bodyProducto);

            if (resultado != false) {
                je = (new Gson()).toJsonTree(new MessageErrors(200, "Datos insertados Correctamente"));
                jo = new JsonObject();
                jo.add("ProductoInsert", je);
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProducto(Producto bodyProducto) {
        JsonElement je;
        JsonObject jo;
        try {

            boolean resultado = productoService.update(bodyProducto);

            if (resultado != false) {
                je = (new Gson()).toJsonTree(new MessageErrors(200, "Datos Modificados Correctamente"));
                jo = new JsonObject();
                jo.add("ProductoUpdate", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(422, "Error al actualizar los datos"));
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
    public Response deleteProducto(Producto producto) {
        JsonElement je;
        JsonObject jo;
        try {

            boolean resultado = productoService.delete(producto);

            if (resultado != false) {
                je = (new Gson()).toJsonTree(new MessageErrors(200, "Datos Eliminados Correctamente"));
                jo = new JsonObject();
                jo.add("ProductoDelete", je);
                return Response.status(200).entity((new Gson()).toJson(jo)).build();
            } else {
                je = (new Gson()).toJsonTree(new MessageErrors(422, "Error al Borrar los datos"));
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

    private Response exceptionCatch(MessageErrors me, Integer statusCode) {
        JsonElement je = (new Gson()).toJsonTree(me);
        JsonObject jo = new JsonObject();
        jo.add("Mensaje", je);
        return Response.status(statusCode).entity((new Gson()).toJson(jo)).build();
    }
}
