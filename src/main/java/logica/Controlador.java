package logica;

import interfaces.IControlador;
import excepciones.UsuarioRepetidoException;
import datatypes.DtFecha;
import datatypes.RedBiblioteca;
import datatypes.DtBibliotecario;
import datatypes.DtLector;
import datatypes.DtLibro;
import datatypes.DtArticulo;

public class Controlador implements IControlador {
    
    private ManejadorUsuario manejadorUsuario;
    private ManejadorMaterial manejadorMaterial;
    
    public Controlador() {
        this.manejadorUsuario = ManejadorUsuario.getInstancia();
        this.manejadorMaterial = ManejadorMaterial.getInstancia();
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

    // Métodos para materiales
    public void agregarLibro(DtLibro dtLibro) {
        manejadorMaterial.agregarLibro(dtLibro);
    }

    public void agregarArticulo(DtArticulo dtArticulo) {
        manejadorMaterial.agregarArticulo(dtArticulo);
    }
}
