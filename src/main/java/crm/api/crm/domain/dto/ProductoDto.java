package crm.api.crm.domain.dto;

import crm.api.crm.persistence.entities.CategoriaEntity;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductoDto(
        UUID id,
        CategoriaResponseDto categoria,
        String nombre,
        String descripcion,
        String sku,
        BigDecimal precio,
        boolean activo

) {
}
