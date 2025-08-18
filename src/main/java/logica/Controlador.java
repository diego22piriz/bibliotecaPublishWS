package logica;

import interfaces.IControlador;
import excepciones.UsuarioRepetidoException;

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
}