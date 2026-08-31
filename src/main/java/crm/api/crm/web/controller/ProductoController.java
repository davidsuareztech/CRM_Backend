package crm.api.crm.web.controller;

import crm.api.crm.domain.dto.ActualizarProductoDto;
import crm.api.crm.domain.dto.CategoriaResponseDto;
import crm.api.crm.domain.dto.CrearProductoDto;
import crm.api.crm.domain.dto.ProductoDto;
import crm.api.crm.domain.services.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")

public class ProductoController {

    private final ProductoService productoService;
    public ProductoController(ProductoService productoService){
        this.productoService=productoService;
    }

    // CRUD
    @GetMapping
    public ResponseEntity<List<ProductoDto>> getAll(){
        return ResponseEntity.ok(this.productoService.getAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity buscarPorId(@PathVariable UUID id){
        ProductoDto productoDto = this.productoService.findById(id);
        if (productoDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDto);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> create(@RequestBody CrearProductoDto crearProductoDto){
        ProductoDto productoDto = this.productoService.create(crearProductoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizar(
            @PathVariable UUID id,
            @RequestBody ActualizarProductoDto dto
    ) {
        return ResponseEntity.ok(
                productoService.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public void dellete(@PathVariable UUID id){
        productoService.delete(id);
    }

    // Búsqueda
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity buscarPorNombre(@PathVariable String nombre){
        ProductoDto productoDto =  this.productoService.findByNombre(nombre);
        if (productoDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDto);
    }

    @GetMapping("/nombre/contiene")
    public ResponseEntity <List<ProductoDto>> nombreContiene(@RequestParam String nombre){
        return ResponseEntity.ok(productoService.findByNombreContainingIgnoreCase(nombre));
    }
    @GetMapping("/sku/{sku}")
    public ResponseEntity buscarPorSku(@PathVariable String sku){
        ProductoDto productoDto = this.productoService.findBySku(sku);
        if (productoDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDto);
    }

    // Categoría
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity <List<ProductoDto>> filtrarCategoria(@PathVariable UUID idCategoria){
        return ResponseEntity.ok(this.productoService.findByCategoriaId(idCategoria));
    }
    @GetMapping("/categoria/{idCategoria}/activos")
    public ResponseEntity <List<ProductoDto>> filtrarpProductosActivosPorCategoria(@PathVariable UUID idCategoria){
        return ResponseEntity.ok(this.productoService.findByCategoriaIdAndActivoTrue(idCategoria));
    }

    // Estado
    @GetMapping("/activo")
    public ResponseEntity <List<ProductoDto>> productosActivos(){
        return ResponseEntity.ok(this.productoService.findByActivoTrue());
    }
    @GetMapping("/contar/activos")
    public ResponseEntity<Long> contarProductosActivos(){
        return ResponseEntity.ok(this.productoService.countByActivoTrue());
    }
    @GetMapping("/contar/inactivos")
    public ResponseEntity<Long> contarProductosInactivos(){
        return ResponseEntity.ok(this.productoService.countByActivoFalse());
    }

    // Precio
    @GetMapping("/precio/{precio}")
    public ResponseEntity <List<ProductoDto>> filtrarPorPrecio(@PathVariable BigDecimal precio){
        return ResponseEntity.ok(this.productoService.findByPrecio(precio));
    }
    @GetMapping("/precio/mayor")
    public ResponseEntity <List<ProductoDto>> productosDePrecioMayor(@RequestParam BigDecimal precio){
        return ResponseEntity.ok(this.productoService.findByPrecioGreaterThan(precio));
    }
    @GetMapping("/precio/menor")
    public ResponseEntity <List<ProductoDto>> productosDePrecioMenor(@RequestParam BigDecimal precio){
        return ResponseEntity.ok(this.productoService.findByPrecioLessThan(precio));
    }
    @GetMapping("/rango/precio")
    public ResponseEntity <List<ProductoDto>> rangoDePrecio(@RequestParam BigDecimal minimo, @RequestParam BigDecimal maximo){
        return ResponseEntity.ok(this.productoService.findByPrecioBetween(minimo, maximo));
    }
}
