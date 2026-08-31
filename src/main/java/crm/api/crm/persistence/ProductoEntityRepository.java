package crm.api.crm.persistence;

import crm.api.crm.domain.repository.ProductoRepository;
import crm.api.crm.persistence.crud.ProductoJpaRepository;
import crm.api.crm.persistence.entities.ProductoEntity;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductoEntityRepository  implements ProductoRepository {
    private final ProductoJpaRepository productoJpaRepository;
    public ProductoEntityRepository(ProductoJpaRepository productoJpaRepository) {
        this.productoJpaRepository = productoJpaRepository;
    }
    //CRUD
    @Override
    public List<ProductoEntity> getAll() {
        return productoJpaRepository.findAllByOrderByNombreAsc();
    }
    @Override
    public Optional<ProductoEntity> findById(UUID id) {
        return productoJpaRepository.findById(id);
    }
    @Override
    public ProductoEntity save(ProductoEntity producto){
        return productoJpaRepository.save(producto);
    }
    @Override
    public ProductoEntity update(ProductoEntity producto){return productoJpaRepository.save(producto);}
    @Override
    public ProductoEntity deleteById(UUID id){
        productoJpaRepository.deleteById(id);
        return null;
    }
    @Override
    public boolean existsById(UUID id) {
        return productoJpaRepository.existsById(id);
    }

    //BUSQUEDA
    @Override
    public Optional<ProductoEntity> findByNombre(String nombre) {
        return productoJpaRepository.findByNombre(nombre);
    }
    @Override
    public List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre) {
        return productoJpaRepository.findByNombreContainingIgnoreCase(nombre);
    }
    @Override
    public Optional<ProductoEntity> findBySku(String sku) {
        return productoJpaRepository.findBySku(sku);
    }

    //Categoría
    @Override
    public List<ProductoEntity> findByCategoriaId(UUID id) {
        return productoJpaRepository.findByCategoriaId(id);
    }
    @Override
    public List<ProductoEntity> findByCategoriaIdAndActivoTrue(UUID idCategoria) {
        return productoJpaRepository.findByCategoriaIdAndActivoTrue(idCategoria);
    }

    //ESTADO
    @Override
    public List<ProductoEntity> findByActivoTrue() {
        return productoJpaRepository.findByActivoTrue();
    }
    @Override
    public long countByActivoTrue() {
        return productoJpaRepository.countByActivoTrue();
    }
    @Override
    public long countByActivoFalse() {
        return productoJpaRepository.countByActivoFalse();
    }

    //Precio
    @Override
    public List<ProductoEntity> findByPrecio(BigDecimal precio) {
        return productoJpaRepository.findByPrecio(precio);
    }
    @Override
    public List<ProductoEntity> findByPrecioGreaterThan(BigDecimal precio) {
        return productoJpaRepository.findByPrecioGreaterThan(precio);
    }
    @Override
    public List<ProductoEntity> findByPrecioLessThan(BigDecimal precio) {
        return productoJpaRepository.findByPrecioLessThan(precio);
    }
    @Override
    public List<ProductoEntity> findByPrecioBetween(BigDecimal minimo, BigDecimal maximo) {
        return productoJpaRepository.findByPrecioBetween(minimo, maximo);
    }

    // Ordenamiento
    @Override
    public List<ProductoEntity> findAllByOrderByNombreAsc() {
        return productoJpaRepository.findAllByOrderByNombreAsc();
    }

}
