package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prv_proveedor database table.
 * 
 */
@Entity
@Table(name="prv_proveedor")
@NamedQuery(name="PrvProveedor.findAll", query="SELECT p FROM PrvProveedor p")
public class PrvProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor")
	private Integer idProveedor;

	private String apellido;

	private String direccion;

	@Column(name="id_ruc")
	private String idRuc;

	private String mail;

	private String nombre;

	@Column(name="nombre_empresa")
	private String nombreEmpresa;

	private String telefono;

	//bi-directional many-to-one association to PrdPedidoCabecera
	@OneToMany(mappedBy="prvProveedor")
	private List<PrdPedidoCabecera> prdPedidoCabeceras;

	//bi-directional many-to-one association to ProProducto
	@OneToMany(mappedBy="prvProveedor")
	private List<ProProducto> proProductos;

	//bi-directional many-to-one association to SegCiudad
	@ManyToOne
	@JoinColumn(name="id_ciudad")
	private SegCiudad segCiudad;

	public PrvProveedor() {
	}

	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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

	public String getNombreEmpresa() {
		return this.nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<PrdPedidoCabecera> getPrdPedidoCabeceras() {
		return this.prdPedidoCabeceras;
	}

	public void setPrdPedidoCabeceras(List<PrdPedidoCabecera> prdPedidoCabeceras) {
		this.prdPedidoCabeceras = prdPedidoCabeceras;
	}

	public PrdPedidoCabecera addPrdPedidoCabecera(PrdPedidoCabecera prdPedidoCabecera) {
		getPrdPedidoCabeceras().add(prdPedidoCabecera);
		prdPedidoCabecera.setPrvProveedor(this);

		return prdPedidoCabecera;
	}

	public PrdPedidoCabecera removePrdPedidoCabecera(PrdPedidoCabecera prdPedidoCabecera) {
		getPrdPedidoCabeceras().remove(prdPedidoCabecera);
		prdPedidoCabecera.setPrvProveedor(null);

		return prdPedidoCabecera;
	}

	public List<ProProducto> getProProductos() {
		return this.proProductos;
	}

	public void setProProductos(List<ProProducto> proProductos) {
		this.proProductos = proProductos;
	}

	public ProProducto addProProducto(ProProducto proProducto) {
		getProProductos().add(proProducto);
		proProducto.setPrvProveedor(this);

		return proProducto;
	}

	public ProProducto removeProProducto(ProProducto proProducto) {
		getProProductos().remove(proProducto);
		proProducto.setPrvProveedor(null);

		return proProducto;
	}

	public SegCiudad getSegCiudad() {
		return this.segCiudad;
	}

	public void setSegCiudad(SegCiudad segCiudad) {
		this.segCiudad = segCiudad;
	}

}