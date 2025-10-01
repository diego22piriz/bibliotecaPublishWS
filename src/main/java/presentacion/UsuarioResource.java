package presentacion;

import datatypes.DtLogin;
import datatypes.DtUsuario;
import logica.Controlador;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioResource {

    private Controlador controlador = new Controlador();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(DtLogin login) {
        DtUsuario usuario = controlador.login(login.getCorreo(), login.getPassword());
        if (usuario == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(usuario).build();
    }
}