package logica;

import interfaces.IControlador;
import excepciones.UsuarioRepetidoException;
import datatypes.DtFecha;
import datatypes.RedBiblioteca;
import datatypes.DtBibliotecario;
import datatypes.DtLector;

public class Controlador implements IControlador {
    
    private ManejadorUsuario manejadorUsuario;
    
    public Controlador() {
        this.manejadorUsuario = ManejadorUsuario.getInstancia();
    }
    
    @Override
    public void agregarUsuario(String nombre, String correo, String tipo) throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(correo)) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + correo);
        }
        
        manejadorUsuario.agregarUsuario(nombre, correo, tipo);
    }

    // Métodos específicos requeridos por la UI
    public void agregarLector(DtLector dtLector) throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(dtLector.getCorreo())) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + dtLector.getCorreo());
        }
        manejadorUsuario.agregarLector(dtLector);
    }

    public void agregarBibliotecario(DtBibliotecario dtBibliotecario)
            throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(dtBibliotecario.getCorreo())) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + dtBibliotecario.getCorreo());
        }
        manejadorUsuario.agregarBibliotecario(dtBibliotecario);
    }
}
