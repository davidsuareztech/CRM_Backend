package crm.api.crm.persistence.crud;

import crm.api.crm.persistence.entities.CategoriaEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CrudCategoriaEntity extends CrudRepository<CategoriaEntity, UUID> {
}
