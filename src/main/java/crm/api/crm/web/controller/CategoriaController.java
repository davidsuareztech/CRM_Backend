package crm.api.crm.web.controller;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.domain.dto.CategoriaResponseDto;
import crm.api.crm.domain.services.CategoriaService;
import crm.api.crm.exception.CategoriaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CategoriaController {
    private final CategoriaService categoriaService;
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService=categoriaService;
    }
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponseDto>> getAll(){
        return ResponseEntity.ok(this.categoriaService.getAll());
    }
    @GetMapping("/categoriasActivas")
    public ResponseEntity<List<CategoriaResponseDto>> isActive(){
        return ResponseEntity.ok(this.categoriaService.isActive());
    }

    @GetMapping("/categoria")
    public ResponseEntity filtrarPorNombre(@RequestParam String nombre){
        CategoriaResponseDto categoriaResponseDto = this.categoriaService.filtrarPorNombre(nombre);
        if (categoriaResponseDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoriaResponseDto);
    }

    @PatchMapping("/categorias/{id}/estado")
    public void actualizarEstado(
            @PathVariable UUID id,
            @RequestParam boolean estado) {
        categoriaService.turnCategory(id, estado);
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseDto> create(
            @RequestBody CategoriaDto categoriaDto) {
        CategoriaResponseDto categoriaResponse = this.categoriaService.create(categoriaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaResponse);
    }

    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable UUID id){
        categoriaService.delete(id);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(CategoriaNotFoundException.class)
        public ResponseEntity<String> manejarCategoriaNoEncontrada(
                CategoriaNotFoundException exception) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(exception.getMessage());
        }
    }


}
