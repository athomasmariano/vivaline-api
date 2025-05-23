package fiap.tds.controllers;

import fiap.tds.dtos.UsuarioDto;
import fiap.tds.services.UsuarioService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private final UsuarioService usuarioService = new UsuarioService();

    @GET
    public Response getUsuarios() {
        List<UsuarioDto> lista = usuarioService.listarUsuarios();
        return Response.ok(lista).build();
    }

    public Response faultToleranceFallback() {
        return Response.status(Response.Status.TOO_MANY_REQUESTS)
                .entity("Você ultrapassou o limite de requisições deste endpoint")
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getUsuario(@PathParam("id") int id) {
        Optional<UsuarioDto> usuario = usuarioService.listarUsuarios().stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        if (usuario.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado")
                    .build();
        }
        return Response.ok(usuario.get()).build();
    }

    @POST
    public Response addUsuario(UsuarioDto usuarioDto) {
        if (!usuarioService.validarUsuario(usuarioDto)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados do usuário inválidos")
                    .build();
        }
        usuarioService.adicionarUsuario(usuarioDto);
        return Response.status(Response.Status.CREATED)
                .entity(usuarioDto)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUsuario(@PathParam("id") int id, UsuarioDto usuarioDto) {
        boolean updated = usuarioService.atualizarUsuario(id, usuarioDto);
        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado")
                    .build();
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUsuario(@PathParam("id") int id) {
        boolean deleted = usuarioService.removerUsuario(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuário não encontrado")
                    .build();
        }
        return Response.noContent().build();
    }
}
