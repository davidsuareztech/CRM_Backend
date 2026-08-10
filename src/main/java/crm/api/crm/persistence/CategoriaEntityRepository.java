package crm.api.crm.persistence;

import crm.api.crm.domain.repository.CategoriaRepository;
import crm.api.crm.persistence.crud.CrudCategoriaEntity;
import crm.api.crm.persistence.entities.CategoriaEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoriaEntityRepository implements CategoriaRepository {
    public CategoriaEntityRepository(CrudCategoriaEntity crudCategoriaEntity) {
        this.crudCategoriaEntity = crudCategoriaEntity;
    }

    @Override
    public List<CategoriaEntity> getAll() {
        List<CategoriaEntity> categorias = new ArrayList<>();
        crudCategoriaEntity.findAll().forEach(categorias::add);
        return categorias;
    }

    @Override
    public Optional<CategoriaEntity> findById(UUID id) {
        return crudCategoriaEntity.findById(id);
    }

    private final CrudCategoriaEntity crudCategoriaEntity;

    @Override
    public CategoriaEntity save(CategoriaEntity categoria){
        return crudCategoriaEntity.save(categoria);
    }


}
