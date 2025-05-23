package fiap.tds.controllers;

import fiap.tds.entities.Estacao;
import fiap.tds.services.EstacaoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/Estacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstacaoResource {

    private final EstacaoService service = new EstacaoService();

    @POST
    public Response cadastrar(Estacao estacao) {
        try {
            service.cadastrar(estacao);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    public List<Estacao> listarTodas() {
        return service.listarTodas();
    }

    @GET
    @Path("/{numeroEstacao}")
    public Response buscarPorNumero(@PathParam("numeroEstacao") int numeroEstacao) {
        Estacao estacao = service.buscarPorNumero(numeroEstacao);
        if (estacao != null) {
            return Response.ok(estacao).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{numeroEstacao}")
    public Response atualizar(@PathParam("numeroEstacao") int numeroEstacao, Estacao estacao) {
        boolean atualizado = service.atualizar(numeroEstacao, estacao);
        if (atualizado) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{numeroEstacao}")
    public Response deletar(@PathParam("numeroEstacao") int numeroEstacao) {
        boolean deletado = service.deletar(numeroEstacao);
        if (deletado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
