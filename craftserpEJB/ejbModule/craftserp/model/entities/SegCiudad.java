package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_ciudad database table.
 * 
 */
@Entity
@Table(name="seg_ciudad")
@NamedQuery(name="SegCiudad.findAll", query="SELECT s FROM SegCiudad s")
public class SegCiudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ciudad")
	private Integer idCiudad;

	private String nombre;

	//bi-directional many-to-one association to PrvProveedor
	@OneToMany(mappedBy="segCiudad")
	private List<PrvProveedor> prvProveedors;

	//bi-directional many-to-one association to SegCompania
	@OneToMany(mappedBy="segCiudad")
	private List<SegCompania> segCompanias;

	//bi-directional many-to-one association to SegPersona
	@OneToMany(mappedBy="segCiudad")
	private List<SegPersona> segPersonas;

	public SegCiudad() {
	}

	public Integer getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PrvProveedor> getPrvProveedors() {
		return this.prvProveedors;
	}

	public void setPrvProveedors(List<PrvProveedor> prvProveedors) {
		this.prvProveedors = prvProveedors;
	}

	public PrvProveedor addPrvProveedor(PrvProveedor prvProveedor) {
		getPrvProveedors().add(prvProveedor);
		prvProveedor.setSegCiudad(this);

		return prvProveedor;
	}

	public PrvProveedor removePrvProveedor(PrvProveedor prvProveedor) {
		getPrvProveedors().remove(prvProveedor);
		prvProveedor.setSegCiudad(null);

		return prvProveedor;
	}

	public List<SegCompania> getSegCompanias() {
		return this.segCompanias;
	}

	public void setSegCompanias(List<SegCompania> segCompanias) {
		this.segCompanias = segCompanias;
	}

	public SegCompania addSegCompania(SegCompania segCompania) {
		getSegCompanias().add(segCompania);
		segCompania.setSegCiudad(this);

		return segCompania;
	}

	public SegCompania removeSegCompania(SegCompania segCompania) {
		getSegCompanias().remove(segCompania);
		segCompania.setSegCiudad(null);

		return segCompania;
	}

	public List<SegPersona> getSegPersonas() {
		return this.segPersonas;
	}

	public void setSegPersonas(List<SegPersona> segPersonas) {
		this.segPersonas = segPersonas;
	}

	public SegPersona addSegPersona(SegPersona segPersona) {
		getSegPersonas().add(segPersona);
		segPersona.setSegCiudad(this);

		return segPersona;
	}

	public SegPersona removeSegPersona(SegPersona segPersona) {
		getSegPersonas().remove(segPersona);
		segPersona.setSegCiudad(null);

		return segPersona;
	}
	
	


}