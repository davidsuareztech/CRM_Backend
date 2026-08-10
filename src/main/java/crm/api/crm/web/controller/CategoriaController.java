package crm.api.crm.web.controller;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.domain.services.CategoriaService;
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
    public List<CategoriaDto> getAll(){
        return this.categoriaService.getAll();
    }
    @GetMapping("/categoriasActivas")
    public List<CategoriaDto> isActive(){
        return this.categoriaService.isActive();
    }

    @GetMapping("/categoria")
    public CategoriaDto filtrarPorNombre(@RequestParam String nombre){
        return categoriaService.filtrarPorNombre(nombre);
    }

    @PatchMapping("/categorias/{id}/estado")
    public void actualizarEstado(
            @PathVariable UUID id,
            @RequestParam boolean estado) {

        categoriaService.turnCategory(id, estado);
    }

    @PostMapping("/categorias")
    public CategoriaDto create(@RequestBody CategoriaDto categoriaDto) {
        return categoriaService.create(categoriaDto);
    }


}
