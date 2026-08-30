package crm.api.crm.persistence.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 150, unique = true)
    private String nombre;
    @Column(length = 250)
    private String descripcion;
    @Column(nullable = false)
    private boolean activo;
    @Column(
            name = "creado_en",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Timestamp creado_en;
    @Column(
            name = "actualizado_en",
            nullable = false
    )
    private Timestamp actualizado_en;

    public CategoriaEntity(String nombre, String descripcion, boolean activo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }
        protected CategoriaEntity() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }


    public Timestamp getCreado_en() {
        return creado_en;
    }

    public Timestamp getActualizado_en() {
        return actualizado_en;
    }

    public void actualizar() {
        this.actualizado_en = Timestamp.valueOf(LocalDateTime.now());
    }

    private void activar(){this.activo =true;}

    private void desactivar(){this.activo =false;}

    public void actualizarEstado(boolean estado){
        if(estado){
            activar();
        }else{
            desactivar();
        }
        actualizar();
    }


}
