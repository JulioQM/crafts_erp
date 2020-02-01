package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seg_estado_civil database table.
 * 
 */
@Entity
@Table(name="seg_estado_civil")
@NamedQuery(name="SegEstadoCivil.findAll", query="SELECT s FROM SegEstadoCivil s")
public class SegEstadoCivil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado_civil")
	private Integer idEstadoCivil;

	private String nombre;

	//bi-directional many-to-one association to SegPersona
	@OneToMany(mappedBy="segEstadoCivil")
	private List<SegPersona> segPersonas;

	public SegEstadoCivil() {
	}

	public Integer getIdEstadoCivil() {
		return this.idEstadoCivil;
	}

	public void setIdEstadoCivil(Integer idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
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
		segPersona.setSegEstadoCivil(this);

		return segPersona;
	}

	public SegPersona removeSegPersona(SegPersona segPersona) {
		getSegPersonas().remove(segPersona);
		segPersona.setSegEstadoCivil(null);

		return segPersona;
	}
	

}