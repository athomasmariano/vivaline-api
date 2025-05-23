package fiap.tds.controllers;

import fiap.tds.entities.User;
import fiap.tds.services.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService service = new UserService();

    @POST
    public Response registrar(User user) {
        try {
            service.registrar(user);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/login")
    public Response login(User user) {
        boolean autenticado = service.autenticar(user.getEmail(), user.getSenha());

        if (autenticado) {
            return Response.ok("✅ Login realizado com sucesso!").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("❌ E-mail ou senha inválidos.")
                    .build();
        }
    }

    @GET
    public List<User> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        User user = service.buscarPorId(id);
        if (user != null) {
            return Response.ok(user).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, User user) {
        boolean atualizado = service.atualizar(id, user);
        if (atualizado) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        boolean deletado = service.deletar(id);
        if (deletado) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
