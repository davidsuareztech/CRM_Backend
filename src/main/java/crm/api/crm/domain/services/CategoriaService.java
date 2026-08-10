package crm.api.crm.domain.services;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.domain.repository.CategoriaRepository;
import crm.api.crm.persistence.entities.CategoriaEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public CategoriaDto filtrarPorNombre(String nombre){
        CategoriaEntity categoria= categoriaRepository.getAll()
                .stream()
                .filter(categoriaEntity -> categoriaEntity.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
        return new CategoriaDto(
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.isActivo()
        );
    }
    public void turnCategory(UUID id, boolean estado){
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria No Encontrada"));
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

}
