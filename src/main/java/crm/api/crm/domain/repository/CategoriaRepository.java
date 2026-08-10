package crm.api.crm.domain.repository;

import crm.api.crm.domain.dto.CategoriaDto;
import crm.api.crm.persistence.entities.CategoriaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository {
    List<CategoriaEntity> getAll();
    Optional<CategoriaEntity> findById(UUID id);
    CategoriaEntity save(CategoriaEntity categoria);
}
