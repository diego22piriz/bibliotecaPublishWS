package logica;

import interfaces.IControlador;
import excepciones.UsuarioRepetidoException;
import datatypes.DtFecha;
import datatypes.RedBiblioteca;

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
    public void agregarLector(String nombre,
                              String correo,
                              String direccion,
                              DtFecha fechaRegistro,
                              boolean activo,
                              RedBiblioteca redBiblioteca) throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(correo)) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + correo);
        }
        manejadorUsuario.agregarLector(nombre, correo, direccion, fechaRegistro, activo, redBiblioteca);
    }

    public void agregarBibliotecario(String nombre, String correo)
            throws UsuarioRepetidoException {
        if (manejadorUsuario.existeUsuario(correo)) {
            throw new UsuarioRepetidoException("Ya existe un usuario con el correo: " + correo);
        }
        manejadorUsuario.agregarBibliotecario(nombre, correo);
    }
}
