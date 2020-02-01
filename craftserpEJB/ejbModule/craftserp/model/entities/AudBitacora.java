package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the aud_bitacora database table.
 * 
 */
@Entity
@Table(name="aud_bitacora")
@NamedQuery(name="AudBitacora.findAll", query="SELECT a FROM AudBitacora a")
public class AudBitacora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AUD_BITACORA_CODIGOEVENTO_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUD_BITACORA_CODIGOEVENTO_GENERATOR")
	@Column(name="codigo_evento")
	private Integer codigoEvento;

	private String descripcion;

	@Column(name="direccion_ip")
	private String direccionIp;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_evento")
	private Date fechaEvento;

	private String metodo;

	//bi-directional many-to-one association to SegPersona
	@ManyToOne
	@JoinColumn(name="codigo_usuario")
	private SegPersona segPersona;

	public AudBitacora() {
	}

	public Integer getCodigoEvento() {
		return this.codigoEvento;
	}

	public void setCodigoEvento(Integer codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccionIp() {
		return this.direccionIp;
	}

	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	public Date getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(Date fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public String getMetodo() {
		return this.metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public SegPersona getSegPersona() {
		return this.segPersona;
	}

	public void setSegPersona(SegPersona segPersona) {
		this.segPersona = segPersona;
	}

}