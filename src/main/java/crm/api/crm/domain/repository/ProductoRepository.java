package crm.api.crm.domain.repository;

import crm.api.crm.persistence.entities.ProductoEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository {

        // CRUD
        List<ProductoEntity> getAll();
        Optional<ProductoEntity> findById(UUID id);
        ProductoEntity save(ProductoEntity producto);
        ProductoEntity deleteById(UUID id);
        boolean existsById(UUID id);


        // Búsqueda
        Optional<ProductoEntity> findByNombre(String nombre);
        List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);
        Optional<ProductoEntity> findBySku(String sku);


        // Categoría
        List<ProductoEntity> findByCategoriaId(UUID idCategoria);
        List<ProductoEntity> findByCategoriaIdAndActivoTrue(UUID idCategoria);


        // Estado
        List<ProductoEntity> findByActivoTrue();
        long countByActivoTrue();
        long countByActivoFalse();


        // Precio
        List<ProductoEntity> findByPrecio(BigDecimal precio);
        List<ProductoEntity> findByPrecioGreaterThan(BigDecimal precio);
        List<ProductoEntity> findByPrecioLessThan(BigDecimal precio);
        List<ProductoEntity> findByPrecioBetween(
                BigDecimal minimo,
                BigDecimal maximo
        );


        // Ordenamiento
        List<ProductoEntity> findAllByOrderByNombreAsc();

}
