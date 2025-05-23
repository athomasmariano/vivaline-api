package fiap.tds.controllers;

import fiap.tds.entities.Noticia;
import fiap.tds.exceptions.BadRequestException;
import fiap.tds.exceptions.NotFoundException;
import fiap.tds.services.NoticiaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/noticias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoticiaResource {

    private static final Logger logger = Logger.getLogger(NoticiaResource.class);
    private final NoticiaService noticiaService = new NoticiaService();

    @GET
    public Response listar() {
        logger.info("📃 Listando todas as notícias...");
        try {
            List<Noticia> noticias = noticiaService.listarTodos();
            return Response.ok(noticias).build();
        } catch (Exception e) {
            logger.error("❌ Erro ao listar notícias", e);
            return Response.serverError().entity("Erro ao buscar notícias.").build();
        }
    }

    @POST
    public Response adicionar(Noticia noticia) {
        logger.info("📝 Registrando nova notícia...");
        try {
            noticiaService.registrar(noticia); // o ID será setado no objeto após persistência
            return Response.status(Response.Status.CREATED)
                    .entity(noticia)
                    .build();
        } catch (BadRequestException e) {
            logger.warn("⚠️ Dados inválidos: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("❌ Erro ao adicionar notícia", e);
            return Response.serverError().entity("Erro ao adicionar notícia.").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        logger.info("🔎 Buscando notícia com ID " + id);
        try {
            Noticia noticia = noticiaService.buscarPorId(id);
            if (noticia == null) {
                logger.warn("⚠️ Notícia com ID " + id + " não encontrada.");
                return Response.status(Response.Status.NOT_FOUND).entity("Notícia não encontrada.").build();
            }
            return Response.ok(noticia).build();
        } catch (Exception e) {
            logger.error("❌ Erro ao buscar notícia por ID", e);
            return Response.serverError().entity("Erro ao buscar notícia.").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Noticia noticia) {
        logger.info("✏️ Atualizando notícia com ID " + id);
        try {
            noticiaService.atualizar(id, noticia);
            return Response.ok("Notícia atualizada com sucesso.").build();
        } catch (NotFoundException e) {
            logger.warn("⚠️ Notícia não encontrada: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (BadRequestException e) {
            logger.warn("⚠️ Dados inválidos para atualização: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("❌ Erro ao atualizar notícia", e);
            return Response.serverError().entity("Erro ao atualizar notícia.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        logger.info("🗑️ Removendo notícia com ID " + id);
        try {
            noticiaService.deletar(id);
            return Response.ok("Notícia removida com sucesso.").build();
        } catch (NotFoundException e) {
            logger.warn("⚠️ Notícia não encontrada para remoção: " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            logger.error("❌ Erro ao remover notícia", e);
            return Response.serverError().entity("Erro ao remover notícia.").build();
        }
    }
}
