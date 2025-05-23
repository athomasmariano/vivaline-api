package fiap.tds.controllers;

import fiap.tds.dtos.ComercioRequestDto;
import fiap.tds.dtos.ComercioResponseDto;
import fiap.tds.services.ComercioService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/Comercios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ComercioResource {

    private final ComercioService service = new ComercioService();

    // GET /comercios - lista todos os comercios
    @GET
    public List<ComercioResponseDto> listarComercios() {
        return service.listarTodos();
    }

    // GET /comercios/{id} - busca comercio pelo id
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        ComercioResponseDto dto = service.buscarPorId(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(dto).build();
    }

    // POST /comercios - cria novo comercio
    @POST
    public Response criarComercio(ComercioRequestDto dto) {
        service.registrar(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    // PUT /comercios/{id} - atualiza comercio pelo id
    @PUT
    @Path("/{id}")
    public Response atualizarComercio(@PathParam("id") int id, ComercioRequestDto dto) {
        service.atualizar(id, dto);
        return Response.noContent().build();
    }

    // DELETE /comercios/{id} - deleta comercio pelo id
    @DELETE
    @Path("/{id}")
    public Response deletarComercio(@PathParam("id") int id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}