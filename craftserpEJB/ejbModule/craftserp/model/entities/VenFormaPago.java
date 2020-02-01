package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ven_forma_pago database table.
 * 
 */
@Entity
@Table(name="ven_forma_pago")
@NamedQuery(name="VenFormaPago.findAll", query="SELECT v FROM VenFormaPago v")
public class VenFormaPago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_forma_pago")
	private Integer idFormaPago;

	private String nombre;

	//bi-directional many-to-one association to VenFacturaCabecera
	@OneToMany(mappedBy="venFormaPago")
	private List<VenFacturaCabecera> venFacturaCabeceras;

	public VenFormaPago() {
	}

	public Integer getIdFormaPago() {
		return this.idFormaPago;
	}

	public void setIdFormaPago(Integer idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<VenFacturaCabecera> getVenFacturaCabeceras() {
		return this.venFacturaCabeceras;
	}

	public void setVenFacturaCabeceras(List<VenFacturaCabecera> venFacturaCabeceras) {
		this.venFacturaCabeceras = venFacturaCabeceras;
	}

	public VenFacturaCabecera addVenFacturaCabecera(VenFacturaCabecera venFacturaCabecera) {
		getVenFacturaCabeceras().add(venFacturaCabecera);
		venFacturaCabecera.setVenFormaPago(this);

		return venFacturaCabecera;
	}

	public VenFacturaCabecera removeVenFacturaCabecera(VenFacturaCabecera venFacturaCabecera) {
		getVenFacturaCabeceras().remove(venFacturaCabecera);
		venFacturaCabecera.setVenFormaPago(null);

		return venFacturaCabecera;
	}

}