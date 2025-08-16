package interfaces;

import excepciones.UsuarioRepetidoException;

public interface IControlador {
    
    public void agregarUsuario(String nombre, String correo, String tipo) throws UsuarioRepetidoException;
}