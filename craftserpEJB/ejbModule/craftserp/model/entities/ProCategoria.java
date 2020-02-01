package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the pro_categoria database table.
 * 
 */
@Entity
@Table(name="pro_categoria")
@NamedQuery(name="ProCategoria.findAll", query="SELECT p FROM ProCategoria p")
public class ProCategoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categoria")
	private Integer idCategoria;

	private String descripcion;

	private String imagen;

	private String nombre;

	//bi-directional many-to-one association to ProProducto
	@OneToMany(mappedBy="proCategoria")
	private List<ProProducto> proProductos;

	public ProCategoria() {
	}

	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
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

	public List<ProProducto> getProProductos() {
		return this.proProductos;
	}

	public void setProProductos(List<ProProducto> proProductos) {
		this.proProductos = proProductos;
	}

	public ProProducto addProProducto(ProProducto proProducto) {
		getProProductos().add(proProducto);
		proProducto.setProCategoria(this);

		return proProducto;
	}

	public ProProducto removeProProducto(ProProducto proProducto) {
		getProProductos().remove(proProducto);
		proProducto.setProCategoria(null);

		return proProducto;
	}

}