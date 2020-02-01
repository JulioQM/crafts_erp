package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the seg_persona database table.
 * 
 */
@Entity
@Table(name="seg_persona")
@NamedQuery(name="SegPersona.findAll", query="SELECT s FROM SegPersona s")
public class SegPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_cedula")
	private String idCedula;

	private String apellido;

	private String clave;

	private String direccion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	private String genero;

	private String mail;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to AudBitacora
	@OneToMany(mappedBy="segPersona")
	private List<AudBitacora> audBitacoras;

	//bi-directional many-to-one association to SegCiudad
	@ManyToOne
	@JoinColumn(name="id_ciudad")
	private SegCiudad segCiudad;

	//bi-directional many-to-one association to SegEstadoCivil
	@ManyToOne
	@JoinColumn(name="id_estado_civil")
	private SegEstadoCivil segEstadoCivil;

	//bi-directional many-to-one association to SegRole
	@ManyToOne
	@JoinColumn(name="id_rol")
	private SegRole segRole;

	//bi-directional many-to-one association to VenFacturaCabecera
	@OneToMany(mappedBy="segPersona1")
	private List<VenFacturaCabecera> venFacturaCabeceras1;

	//bi-directional many-to-one association to VenFacturaCabecera
	@OneToMany(mappedBy="segPersona2")
	private List<VenFacturaCabecera> venFacturaCabeceras2;

	public SegPersona() {
	}

	public String getIdCedula() {
		return this.idCedula;
	}

	public void setIdCedula(String idCedula) {
		this.idCedula = idCedula;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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

	public List<AudBitacora> getAudBitacoras() {
		return this.audBitacoras;
	}

	public void setAudBitacoras(List<AudBitacora> audBitacoras) {
		this.audBitacoras = audBitacoras;
	}

	public AudBitacora addAudBitacora(AudBitacora audBitacora) {
		getAudBitacoras().add(audBitacora);
		audBitacora.setSegPersona(this);

		return audBitacora;
	}

	public AudBitacora removeAudBitacora(AudBitacora audBitacora) {
		getAudBitacoras().remove(audBitacora);
		audBitacora.setSegPersona(null);

		return audBitacora;
	}

	public SegCiudad getSegCiudad() {
		return this.segCiudad;
	}

	public void setSegCiudad(SegCiudad segCiudad) {
		this.segCiudad = segCiudad;
	}

	public SegEstadoCivil getSegEstadoCivil() {
		return this.segEstadoCivil;
	}

	public void setSegEstadoCivil(SegEstadoCivil segEstadoCivil) {
		this.segEstadoCivil = segEstadoCivil;
	}

	public SegRole getSegRole() {
		return this.segRole;
	}

	public void setSegRole(SegRole segRole) {
		this.segRole = segRole;
	}

	public List<VenFacturaCabecera> getVenFacturaCabeceras1() {
		return this.venFacturaCabeceras1;
	}

	public void setVenFacturaCabeceras1(List<VenFacturaCabecera> venFacturaCabeceras1) {
		this.venFacturaCabeceras1 = venFacturaCabeceras1;
	}

	public VenFacturaCabecera addVenFacturaCabeceras1(VenFacturaCabecera venFacturaCabeceras1) {
		getVenFacturaCabeceras1().add(venFacturaCabeceras1);
		venFacturaCabeceras1.setSegPersona1(this);

		return venFacturaCabeceras1;
	}

	public VenFacturaCabecera removeVenFacturaCabeceras1(VenFacturaCabecera venFacturaCabeceras1) {
		getVenFacturaCabeceras1().remove(venFacturaCabeceras1);
		venFacturaCabeceras1.setSegPersona1(null);

		return venFacturaCabeceras1;
	}

	public List<VenFacturaCabecera> getVenFacturaCabeceras2() {
		return this.venFacturaCabeceras2;
	}

	public void setVenFacturaCabeceras2(List<VenFacturaCabecera> venFacturaCabeceras2) {
		this.venFacturaCabeceras2 = venFacturaCabeceras2;
	}

	public VenFacturaCabecera addVenFacturaCabeceras2(VenFacturaCabecera venFacturaCabeceras2) {
		getVenFacturaCabeceras2().add(venFacturaCabeceras2);
		venFacturaCabeceras2.setSegPersona2(this);

		return venFacturaCabeceras2;
	}

	public VenFacturaCabecera removeVenFacturaCabeceras2(VenFacturaCabecera venFacturaCabeceras2) {
		getVenFacturaCabeceras2().remove(venFacturaCabeceras2);
		venFacturaCabeceras2.setSegPersona2(null);

		return venFacturaCabeceras2;
	}
	
}