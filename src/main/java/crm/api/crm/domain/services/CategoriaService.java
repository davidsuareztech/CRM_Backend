package crm.api.crm.domain.services;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.domain.dto.CategoriaResponseDto;
import crm.api.crm.domain.repository.CategoriaRepository;
import crm.api.crm.exception.CategoriaNotFoundException;
import crm.api.crm.persistence.entities.CategoriaEntity;
import crm.api.crm.persistence.mapper.CategoriaMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(
            CategoriaRepository categoriaRepository,
            CategoriaMapper categoriaMapper) {

        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaResponseDto> getAll() {
        return categoriaMapper.toResponseDto(
                categoriaRepository.getAll()
        );
    }

    public CategoriaResponseDto filtrarPorNombre(String nombre) {

        CategoriaEntity categoria = categoriaRepository.findByNombre(nombre)
                .orElseThrow(() ->
                        new CategoriaNotFoundException(
                                "No se encontró una categoría con el nombre: " + nombre
                        )
                );

        return categoriaMapper.toResponseDto(categoria);
    }

    public void turnCategory(UUID id, boolean estado) {

        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new CategoriaNotFoundException(
                                "No se encontró una categoría con el id: " + id
                        )
                );

        categoria.actualizarEstado(estado);
        categoriaRepository.save(categoria);
    }

    public List<CategoriaResponseDto> isActive() {

        return categoriaRepository.getAll()
                .stream()
                .filter(CategoriaEntity::isActivo)
                .map(categoriaMapper::toResponseDto)
                .toList();
    }

    public CategoriaResponseDto create(CategoriaDto categoriaDto) {

        CategoriaEntity categoria =
                categoriaMapper.toEntity(categoriaDto);

        CategoriaEntity guardada =
                categoriaRepository.save(categoria);

        return categoriaMapper.toResponseDto(guardada);
    }

    public void delete(UUID id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNotFoundException(
                    "No se encontró la categoría con id: " + id
            );
        }
        categoriaRepository.deleteByID(id);
    }
}