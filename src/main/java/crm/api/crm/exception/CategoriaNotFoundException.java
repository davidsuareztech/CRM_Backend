package crm.api.crm.exception;

public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException(String mensaje) {
        super(mensaje);
    }
}