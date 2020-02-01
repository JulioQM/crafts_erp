package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_roles database table.
 * 
 */
@Entity
@Table(name="seg_roles")
@NamedQuery(name="SegRole.findAll", query="SELECT s FROM SegRole s")
public class SegRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rol")
	private Integer idRol;

	private String nombre;

	//bi-directional many-to-one association to SegPersona
	@OneToMany(mappedBy="segRole")
	private List<SegPersona> segPersonas;

	public SegRole() {
	}

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<SegPersona> getSegPersonas() {
		return this.segPersonas;
	}

	public void setSegPersonas(List<SegPersona> segPersonas) {
		this.segPersonas = segPersonas;
	}

	public SegPersona addSegPersona(SegPersona segPersona) {
		getSegPersonas().add(segPersona);
		segPersona.setSegRole(this);

		return segPersona;
	}

	public SegPersona removeSegPersona(SegPersona segPersona) {
		getSegPersonas().remove(segPersona);
		segPersona.setSegRole(null);

		return segPersona;
	}
	

}