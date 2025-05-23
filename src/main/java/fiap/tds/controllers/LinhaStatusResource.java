package fiap.tds.controllers;

import fiap.tds.entities.LinhaStatus;
import fiap.tds.exceptions.BadRequestException;
import fiap.tds.exceptions.NotFoundException;
import fiap.tds.services.LinhaStatusService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/StatusLinhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LinhaStatusResource {

    private static final Logger logger = Logger.getLogger(LinhaStatusResource.class);
    private final LinhaStatusService statusService = new LinhaStatusService();

    @GET
    public Response listar() {
        logger.info("Listando status das linhas...");
        try {
            List<LinhaStatus> lista = statusService.listarTodos();
            return Response.ok(lista).build();
        } catch (Exception e) {
            logger.error("Erro ao listar status das linhas: " + e.getMessage(), e);
            return Response.serverError().entity("Erro ao buscar status das linhas.").build();
        }
    }

    @POST
    public Response adicionar(LinhaStatus linhaStatus) {
        logger.info("Adicionando novo status de linha...");
        try {
            statusService.registrar(linhaStatus);
            return Response.status(Response.Status.CREATED)
                    .entity(linhaStatus)
                    .build();
        } catch (BadRequestException e) {
            logger.warn("Dados inválidos: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Erro ao adicionar status: " + e.getMessage(), e);
            return Response.serverError().entity("Erro ao adicionar status da linha.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        logger.info("Buscando status da linha com ID " + id);
        try {
            LinhaStatus linhaStatus = statusService.buscarPorId(id);
            if (linhaStatus == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Status não encontrado").build();
            }
            return Response.ok(linhaStatus).build();
        } catch (Exception e) {
            logger.error("Erro ao buscar status: " + e.getMessage(), e);
            return Response.serverError().entity("Erro ao buscar status da linha.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, LinhaStatus linhaStatus) {
        logger.info("Atualizando status da linha com ID " + id);
        try {
            statusService.atualizar(id, linhaStatus);
            return Response.ok("Status atualizado com sucesso.").build();
        } catch (NotFoundException e) {
            logger.warn("Linha não encontrada: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (BadRequestException e) {
            logger.warn("Dados inválidos para atualização: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Erro ao atualizar status: " + e.getMessage(), e);
            return Response.serverError().entity("Erro ao atualizar status da linha.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        logger.info("Removendo status da linha com ID " + id);
        try {
            statusService.deletar(id);
            return Response.ok("Status removido com sucesso.").build();
        } catch (NotFoundException e) {
            logger.warn("Linha não encontrada para remoção: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("Erro ao remover status: " + e.getMessage(), e);
            return Response.serverError().entity("Erro ao remover status da linha.").build();
        }
    }
}
