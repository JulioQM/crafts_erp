package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prd_estado database table.
 * 
 */
@Entity
@Table(name="prd_estado")
@NamedQuery(name="PrdEstado.findAll", query="SELECT p FROM PrdEstado p")
public class PrdEstado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado")
	private Integer idEstado;

	private String nombre;

	//bi-directional many-to-one association to PrdPedidoCabecera
	@OneToMany(mappedBy="prdEstado")
	private List<PrdPedidoCabecera> prdPedidoCabeceras;

	//bi-directional many-to-one association to VenFacturaCabecera
	@OneToMany(mappedBy="prdEstado")
	private List<VenFacturaCabecera> venFacturaCabeceras;

	public PrdEstado() {
	}

	public Integer getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PrdPedidoCabecera> getPrdPedidoCabeceras() {
		return this.prdPedidoCabeceras;
	}

	public void setPrdPedidoCabeceras(List<PrdPedidoCabecera> prdPedidoCabeceras) {
		this.prdPedidoCabeceras = prdPedidoCabeceras;
	}

	public PrdPedidoCabecera addPrdPedidoCabecera(PrdPedidoCabecera prdPedidoCabecera) {
		getPrdPedidoCabeceras().add(prdPedidoCabecera);
		prdPedidoCabecera.setPrdEstado(this);

		return prdPedidoCabecera;
	}

	public PrdPedidoCabecera removePrdPedidoCabecera(PrdPedidoCabecera prdPedidoCabecera) {
		getPrdPedidoCabeceras().remove(prdPedidoCabecera);
		prdPedidoCabecera.setPrdEstado(null);

		return prdPedidoCabecera;
	}

	public List<VenFacturaCabecera> getVenFacturaCabeceras() {
		return this.venFacturaCabeceras;
	}

	public void setVenFacturaCabeceras(List<VenFacturaCabecera> venFacturaCabeceras) {
		this.venFacturaCabeceras = venFacturaCabeceras;
	}

	public VenFacturaCabecera addVenFacturaCabecera(VenFacturaCabecera venFacturaCabecera) {
		getVenFacturaCabeceras().add(venFacturaCabecera);
		venFacturaCabecera.setPrdEstado(this);

		return venFacturaCabecera;
	}

	public VenFacturaCabecera removeVenFacturaCabecera(VenFacturaCabecera venFacturaCabecera) {
		getVenFacturaCabeceras().remove(venFacturaCabecera);
		venFacturaCabecera.setPrdEstado(null);

		return venFacturaCabecera;
	}

}