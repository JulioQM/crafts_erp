package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pro_catalogo database table.
 * 
 */
@Entity
@Table(name="pro_catalogo")
@NamedQuery(name="ProCatalogo.findAll", query="SELECT p FROM ProCatalogo p")
public class ProCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_catalogo")
	private Integer idCatalogo;

	private String descripcion;

	private String imagen;

	private String nombre;

	//bi-directional many-to-one association to ProProductoCatalogo
	@OneToMany(mappedBy="proCatalogo")
	private List<ProProductoCatalogo> proProductoCatalogos;

	public ProCatalogo() {
	}

	public Integer getIdCatalogo() {
		return this.idCatalogo;
	}

	public void setIdCatalogo(Integer idCatalogo) {
		this.idCatalogo = idCatalogo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ProProductoCatalogo> getProProductoCatalogos() {
		return this.proProductoCatalogos;
	}

	public void setProProductoCatalogos(List<ProProductoCatalogo> proProductoCatalogos) {
		this.proProductoCatalogos = proProductoCatalogos;
	}

	public ProProductoCatalogo addProProductoCatalogo(ProProductoCatalogo proProductoCatalogo) {
		getProProductoCatalogos().add(proProductoCatalogo);
		proProductoCatalogo.setProCatalogo(this);

		return proProductoCatalogo;
	}

	public ProProductoCatalogo removeProProductoCatalogo(ProProductoCatalogo proProductoCatalogo) {
		getProProductoCatalogos().remove(proProductoCatalogo);
		proProductoCatalogo.setProCatalogo(null);

		return proProductoCatalogo;
	}

}