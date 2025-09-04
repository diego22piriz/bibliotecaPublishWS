package excepciones;

public class PrestamoDuplicadoException extends Exception {
    
    public PrestamoDuplicadoException(String mensaje) {
        super(mensaje);
    }
    
    public PrestamoDuplicadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
