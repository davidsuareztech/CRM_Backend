package crm.api.crm.persistence.mapper;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.domain.dto.CategoriaResponseDto;
import crm.api.crm.persistence.entities.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDto toDto(CategoriaEntity entity);

    @Mapping(target = "id", ignore = true)
    CategoriaEntity toEntity(CategoriaDto dto);


    List<CategoriaDto> toDto(Iterable<CategoriaEntity> entities);

    CategoriaResponseDto toResponseDto(CategoriaEntity entity);

    List<CategoriaResponseDto> toResponseDto(Iterable<CategoriaEntity> entities);
}
