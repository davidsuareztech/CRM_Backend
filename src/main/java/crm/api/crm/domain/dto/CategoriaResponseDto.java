package crm.api.crm.domain.dto;

import java.util.UUID;

public record CategoriaResponseDto(
        UUID id,
        String nombre,
        String descripcion,
        boolean activo
) {}
