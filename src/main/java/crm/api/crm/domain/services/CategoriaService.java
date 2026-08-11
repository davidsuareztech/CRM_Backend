package crm.api.crm.domain.services;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.domain.repository.CategoriaRepository;
import crm.api.crm.exception.CategoriaNotFoundException;
import crm.api.crm.persistence.entities.CategoriaEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository=categoriaRepository;
    }
    public List<CategoriaDto> getAll(){
        return this.categoriaRepository.getAll()
                .stream()
                .map(categoria -> new CategoriaDto(
                        categoria.getNombre(),
                        categoria.getDescripcion(),
                        categoria.isActivo()))
                        .toList();
    }

    public CategoriaDto filtrarPorNombre(String nombre) {
        CategoriaEntity categoria = categoriaRepository.findByNombre(nombre)
                .orElseThrow(() ->
                        new CategoriaNotFoundException(
                                "No se encontró una categoría con el nombre: " + nombre
                        )
                );
        return new CategoriaDto(
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.isActivo()
        );
    }

    public void turnCategory(UUID id, boolean estado){
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("No se encontró una categoría con el nombre: " + id));
        categoria.actualizarEstado(estado);
        categoriaRepository.save(categoria);
    }

    public List<CategoriaDto> isActive(){
        return this.categoriaRepository.getAll()
                .stream()
                .filter(categoriaEntity -> categoriaEntity.isActivo())
                .map(categoria -> new CategoriaDto(
                        categoria.getNombre(),
                        categoria.getDescripcion(),
                        categoria.isActivo()))
                .toList();
    }

    public CategoriaDto create(CategoriaDto categoriaDto){
        CategoriaEntity categoria = new CategoriaEntity(categoriaDto.nombre(), categoriaDto.descripcion(), categoriaDto.activo());
        CategoriaEntity guardada = categoriaRepository.save(categoria);

        return new CategoriaDto(
                guardada.getNombre(),
                guardada.getDescripcion(),
                guardada.isActivo()
        );
    }

    public void deleate(UUID id){
        CategoriaEntity eliminada = categoriaRepository.deleteByID(id);
    }

}
