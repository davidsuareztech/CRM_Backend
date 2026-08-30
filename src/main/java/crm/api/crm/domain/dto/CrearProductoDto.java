package crm.api.crm.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CrearProductoDto(
        UUID id_categoria,
        String nombre,
        String descripcion,
        String sku,
        BigDecimal precio,
        boolean activo
) {
}
