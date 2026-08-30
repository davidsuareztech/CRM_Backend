package crm.api.crm.exception;

public class ProductoNotFoundException extends RuntimeException {

    public ProductoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
