package crm.api.crm.domain.repository;

import crm.api.crm.persistence.entities.CategoriaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository {
    List<CategoriaEntity> getAll();
    CategoriaEntity deleteByID(UUID id);
    Optional<CategoriaEntity> findById(UUID id);
    CategoriaEntity save(CategoriaEntity categoria);
    Optional<CategoriaEntity> findByNombre(String nombre);
    boolean existsById(UUID id);
}
