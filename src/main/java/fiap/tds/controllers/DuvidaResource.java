package fiap.tds.controllers;

import fiap.tds.entities.Duvida;
import fiap.tds.exceptions.BadRequestException;
import fiap.tds.services.DuvidaService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/Duvidas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DuvidaResource {

    private static final Logger logger = Logger.getLogger(DuvidaResource.class);
    private final DuvidaService duvidaService = new DuvidaService();

    @GET
    public Response listarFaqs() {
        logger.info("Iniciando listagem de FAQs...");
        try {
            List<Duvida> faqs = duvidaService.listarFaqs();

            if (faqs.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhuma FAQ encontrada.")
                        .build();
            }

            return Response.ok(faqs).build();

        } catch (Exception e) {
            logger.error("Erro ao listar FAQs: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao listar FAQs.")
                    .build();
        }
    }

    @POST
    public Response registrarFaq(Duvida dto) {
        logger.info("Iniciando registro de contato...");
        try {
            String resultado = duvidaService.registrarFaq(dto);

            if (resultado == null || resultado.isBlank()) {
                throw new BadRequestException("Erro ao registrar contato. Verifique os dados enviados.");
            }

            return Response.status(Response.Status.CREATED)
                    .entity(resultado)
                    .build();

        } catch (BadRequestException e) {
            logger.error("❌ Erro ao registrar contato: " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();

        } catch (Exception e) {
            logger.error("❌ Erro inesperado ao registrar contato: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao registrar contato.")
                    .build();
        }
    }
    @PUT
    @Path("/{id}")
    public Response atualizarFaq(@PathParam("id") int id, Duvida dto) {
        Duvida existente = duvidaService.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("FAQ não encontrada.")
                    .build();
        }

        existente.setPergunta(dto.getPergunta());
        existente.setResposta(dto.getResposta());

        duvidaService.atualizar(existente);
        return Response.ok("FAQ atualizada com sucesso.").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarFaq(@PathParam("id") int id) {
        Duvida existente = duvidaService.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("FAQ não encontrada.")
                    .build();
        }

        duvidaService.deletar(id);
        return Response.ok("FAQ deletada com sucesso.").build();
    }

}
