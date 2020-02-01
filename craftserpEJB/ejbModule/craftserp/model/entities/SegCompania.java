package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_compania database table.
 * 
 */
@Entity
@Table(name="seg_compania")
@NamedQuery(name="SegCompania.findAll", query="SELECT s FROM SegCompania s")
public class SegCompania implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_compania")
	private Integer idCompania;

	private String direccion;

	@Column(name="id_ruc")
	private String idRuc;

	private String mail;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to SegCiudad
	@ManyToOne
	@JoinColumn(name="id_ciudad")
	private SegCiudad segCiudad;

	//bi-directional many-to-one association to VenFacturaCabecera
	@OneToMany(mappedBy="segCompania")
	private List<VenFacturaCabecera> venFacturaCabeceras;

	public SegCompania() {
	}

	public Integer getIdCompania() {
		return this.idCompania;
	}

	public void setIdCompania(Integer idCompania) {
		this.idCompania = idCompania;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getIdRuc() {
		return this.idRuc;
	}

	public void setIdRuc(String idRuc) {
		this.idRuc = idRuc;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public SegCiudad getSegCiudad() {
		return this.segCiudad;
	}

	public void setSegCiudad(SegCiudad segCiudad) {
		this.segCiudad = segCiudad;
	}

	public List<VenFacturaCabecera> getVenFacturaCabeceras() {
		return this.venFacturaCabeceras;
	}

	public void setVenFacturaCabeceras(List<VenFacturaCabecera> venFacturaCabeceras) {
		this.venFacturaCabeceras = venFacturaCabeceras;
	}

	public VenFacturaCabecera addVenFacturaCabecera(VenFacturaCabecera venFacturaCabecera) {
		getVenFacturaCabeceras().add(venFacturaCabecera);
		venFacturaCabecera.setSegCompania(this);

		return venFacturaCabecera;
	}

	public VenFacturaCabecera removeVenFacturaCabecera(VenFacturaCabecera venFacturaCabecera) {
		getVenFacturaCabeceras().remove(venFacturaCabecera);
		venFacturaCabecera.setSegCompania(null);

		return venFacturaCabecera;
	}

}