package crm.api.crm.persistence.mapper;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.domain.dto.CrearProductoDto;
import crm.api.crm.domain.dto.ProductoDto;
import crm.api.crm.persistence.entities.CategoriaEntity;
import crm.api.crm.persistence.entities.ProductoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = CategoriaMapper.class
)
public interface ProductoMapper {


    ProductoDto toDto(ProductoEntity productoEntity);


    @Mapping(target = "categoria", ignore = true)
    ProductoEntity toEntity(CrearProductoDto dto);

    List<ProductoDto> toDto(Iterable<ProductoEntity> entities);
}