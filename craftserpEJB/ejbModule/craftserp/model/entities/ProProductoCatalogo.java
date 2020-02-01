package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pro_producto_catalogo database table.
 * 
 */
@Entity
@Table(name="pro_producto_catalogo")
@NamedQuery(name="ProProductoCatalogo.findAll", query="SELECT p FROM ProProductoCatalogo p")
public class ProProductoCatalogo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto_catalogo")
	private Integer idProductoCatalogo;

	//bi-directional many-to-one association to ProCatalogo
	@ManyToOne
	@JoinColumn(name="id_catalogo")
	private ProCatalogo proCatalogo;

	//bi-directional many-to-one association to ProProducto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private ProProducto proProducto;

	public ProProductoCatalogo() {
	}

	public Integer getIdProductoCatalogo() {
		return this.idProductoCatalogo;
	}

	public void setIdProductoCatalogo(Integer idProductoCatalogo) {
		this.idProductoCatalogo = idProductoCatalogo;
	}

	public ProCatalogo getProCatalogo() {
		return this.proCatalogo;
	}

	public void setProCatalogo(ProCatalogo proCatalogo) {
		this.proCatalogo = proCatalogo;
	}

	public ProProducto getProProducto() {
		return this.proProducto;
	}

	public void setProProducto(ProProducto proProducto) {
		this.proProducto = proProducto;
	}

}