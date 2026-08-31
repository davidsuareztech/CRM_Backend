package crm.api.crm.domain.services;


import crm.api.crm.domain.dto.ActualizarProductoDto;
import crm.api.crm.domain.dto.CrearProductoDto;
import crm.api.crm.domain.dto.ProductoDto;
import crm.api.crm.domain.repository.CategoriaRepository;
import crm.api.crm.domain.repository.ProductoRepository;
import crm.api.crm.exception.CategoriaNotFoundException;
import crm.api.crm.exception.ProductoNotFoundException;
import crm.api.crm.persistence.entities.CategoriaEntity;
import crm.api.crm.persistence.entities.ProductoEntity;
import crm.api.crm.persistence.mapper.ProductoMapper;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(
            ProductoRepository productoRepository,
            ProductoMapper productoMapper,
            CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.categoriaRepository=categoriaRepository;
    }

    // CRUD
    public List<ProductoDto> getAll() {
        return productoMapper.toDto(
                productoRepository.getAll()
        );
    }

    public ProductoDto findById(UUID id){
        ProductoEntity productoEntity = productoRepository.findById(id)
                .orElseThrow(() ->
                        new ProductoNotFoundException(
                                "No se encontró un producto con el id: " + id
                        )
                );
        return productoMapper.toDto(productoEntity);
    }

    public ProductoDto create(CrearProductoDto dto) {

        CategoriaEntity categoria = categoriaRepository
                .findById(dto.id_categoria())
                .orElseThrow(() ->
                        new CategoriaNotFoundException(
                                "No existe la categoría con id: "
                                        + dto.id_categoria()
                        )
                );

        ProductoEntity productoEntity = productoMapper.toEntity(dto);

        productoEntity.setCategoria(categoria);

        ProductoEntity guardado = productoRepository.save(productoEntity);

        return productoMapper.toDto(guardado);
    }

    public void delete (UUID id){
        if(!productoRepository.existsById(id)){
            throw new ProductoNotFoundException(
                    "No se encontró el producto con id: " + id
            );
        }
        productoRepository.deleteById(id);
    }

    public ProductoDto actualizar (UUID id, ActualizarProductoDto actualizarProductoDto){
        ProductoEntity productoEntity = productoRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        CategoriaEntity categoria = categoriaRepository.findById(actualizarProductoDto.id_categoria())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        productoEntity.setCategoria(categoria);
        productoEntity.setNombre(actualizarProductoDto.nombre());
        productoEntity.setDescripcion(actualizarProductoDto.descripcion());
        productoEntity.setSku(actualizarProductoDto.sku());
        productoEntity.actualizarEstado(actualizarProductoDto.activo());

        return productoMapper.toDto(
                productoRepository.save(productoEntity)
        );
    }

    // Búsqueda
    public ProductoDto findByNombre(String nombre){
        ProductoEntity productoEntity= productoRepository.findByNombre(nombre)
                .orElseThrow(() ->
                        new ProductoNotFoundException(
                                "No se encontró un producto con el nombre: " + nombre
                        )
                );
        return productoMapper.toDto(productoEntity);
    }

    public List<ProductoDto> findByNombreContainingIgnoreCase(String nombre){
        return productoMapper.toDto(
                productoRepository.findByNombreContainingIgnoreCase(nombre)
        );
    }
    public ProductoDto findBySku(String sku){
        ProductoEntity productoEntity = productoRepository.findBySku(sku)
                .orElseThrow(() ->
                        new ProductoNotFoundException(
                                "No se encontró un producto con el sku: " + sku
                        )
                );
        return productoMapper.toDto(productoEntity);
    }

    // Categoría
    public List<ProductoDto> findByCategoriaId(UUID idCategoria){
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new CategoriaNotFoundException(
                    "La categoría no existe"
            );
        }
        return productoMapper.toDto(
                productoRepository.findByCategoriaId(idCategoria)
        );
    }
    public List<ProductoDto> findByCategoriaIdAndActivoTrue(UUID idCategoria){
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new CategoriaNotFoundException(
                    "La categoría no existe"
            );
        }
        return productoMapper.toDto(
                productoRepository.findByCategoriaIdAndActivoTrue(idCategoria)
        );
    }
    // Estado
    public List<ProductoDto> findByActivoTrue(){
        return productoMapper.toDto(
                productoRepository.findByActivoTrue()
        );
    }
    public Long countByActivoTrue(){
        return productoRepository.countByActivoTrue();
    }
    public Long countByActivoFalse(){
        return productoRepository.countByActivoFalse();
    }

    // Precio
    public List<ProductoDto> findByPrecio(BigDecimal precio){
        return productoMapper.toDto(
                productoRepository.findByPrecio(precio)
        );
    }
    public List<ProductoDto> findByPrecioGreaterThan(BigDecimal precio){
        return productoMapper.toDto(
                productoRepository.findByPrecioGreaterThan(precio)
        );
    }
    public List<ProductoDto> findByPrecioLessThan(BigDecimal precio){
        return productoMapper.toDto(
                productoRepository.findByPrecioLessThan(precio)
        );
    }
    public List<ProductoDto> findByPrecioBetween(
            BigDecimal minimo,
            BigDecimal maximo
    ){
        return productoMapper.toDto(
                productoRepository.findByPrecioBetween(minimo, maximo)
        );
    }
    
}
