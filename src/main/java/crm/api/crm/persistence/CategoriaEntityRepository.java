package crm.api.crm.persistence;

import crm.api.crm.domain.repository.CategoriaRepository;
import crm.api.crm.persistence.crud.CategoriaJpaRepository;
import crm.api.crm.persistence.entities.CategoriaEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CategoriaEntityRepository implements CategoriaRepository {
    public CategoriaEntityRepository(CategoriaJpaRepository categoriaJpaRepository) {
        this.categoriaJpaRepository = categoriaJpaRepository;
    }


    @Override
    public List<CategoriaEntity> getAll() {
        List<CategoriaEntity> categorias = new ArrayList<>();
        categoriaJpaRepository.findAll().forEach(categorias::add);
        return categorias;
    }

    @Override
    public Optional<CategoriaEntity> findById(UUID id) {
        return categoriaJpaRepository.findById(id);
    }


    private final CategoriaJpaRepository categoriaJpaRepository;

    @Override
    public CategoriaEntity save(CategoriaEntity categoria){
        return categoriaJpaRepository.save(categoria);
    }

    @Override
    public Optional<CategoriaEntity> findByNombre(String nombre) {
        return categoriaJpaRepository.findByNombre(nombre);
    }

    @Override
    public boolean existsById(UUID id) {
        return categoriaJpaRepository.existsById(id);
    }

    @Override
    public CategoriaEntity deleteByID(UUID id){
        categoriaJpaRepository.deleteById(id);
        return null;
    }



}
