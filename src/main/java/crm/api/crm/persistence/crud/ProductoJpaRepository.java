package crm.api.crm.persistence.crud;

import crm.api.crm.persistence.entities.ProductoEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoJpaRepository extends CrudRepository<ProductoEntity, UUID> {
    Optional<ProductoEntity> findByNombre(String nombre);
    List<ProductoEntity> findByPrecio(BigDecimal precio);
    List<ProductoEntity> findByCategoriaId(UUID categoriaId);
    List<ProductoEntity> findAllByOrderByNombreAsc();
    List<ProductoEntity> findByPrecioGreaterThan(BigDecimal precio);
    List<ProductoEntity> findByPrecioLessThan(BigDecimal precio);
    List<ProductoEntity> findByPrecioBetween(
            BigDecimal minimo,
            BigDecimal maximo
    );
    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);
    List<ProductoEntity> findByActivoTrue();
    Optional<ProductoEntity> findBySku(String sku);
    List<ProductoEntity> findByCategoriaIdAndActivoTrue(UUID idCategoria);
    long countByActivoTrue();
    long countByActivoFalse();

}
