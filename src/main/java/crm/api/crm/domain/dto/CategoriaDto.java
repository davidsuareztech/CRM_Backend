package crm.api.crm.domain.dto;

public record CategoriaDto(
        String nombre,
        String descripcion,
        boolean activo
) {
    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public String descripcion() {
        return descripcion;
    }

    @Override
    public boolean activo() {
        return activo;
    }
}
