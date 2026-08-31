package crm.api.crm.domain.dto;

import java.util.UUID;

public record ActualizarProductoDto(
        UUID id_categoria,
        String nombre,
        String descripcion,
        String sku,
        boolean activo
) {
}
