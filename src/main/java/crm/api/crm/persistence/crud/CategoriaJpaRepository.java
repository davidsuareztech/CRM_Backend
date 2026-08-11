package crm.api.crm.persistence.crud;

import crm.api.crm.persistence.entities.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoriaJpaRepository extends CrudRepository<crm.api.crm.persistence.entities.CategoriaEntity, UUID> {

    Optional<CategoriaEntity> findByNombre(String nombre);
}
