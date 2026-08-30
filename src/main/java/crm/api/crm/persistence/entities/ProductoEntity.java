package crm.api.crm.persistence.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "producto")
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(length = 250)
    private String descripcion;
    @Column(nullable = false, length = 50, unique = true)
    private String sku;
    @Column(nullable = false)
    private BigDecimal precio;
    @Column(nullable = false)
    private boolean activo;
    @Column(
            name = "creado_en",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Timestamp creadoEn;
    @Column(
            name = "actualizado_en",
            nullable = false
    )
    private Timestamp actualizadoEn;

    public ProductoEntity(CategoriaEntity categoria, String nombre, String descripcion, String sku, BigDecimal precio, boolean activo){
        this.categoria=categoria;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.sku=sku;
        this.activo=activo;
        this.precio =precio;
    }

    protected ProductoEntity(){}

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public boolean isActivo() {
        return activo;
    }

    public Timestamp getActualizado_en() {
        return actualizadoEn;
    }

    public void actualizar() {
        this.actualizadoEn = Timestamp.valueOf(LocalDateTime.now());
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
