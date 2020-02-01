package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pro_producto database table.
 * 
 */
@Entity
@Table(name="pro_producto")
@NamedQuery(name="ProProducto.findAll", query="SELECT p FROM ProProducto p")
public class ProProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_producto")
	private Integer idProducto;

	private String codigo;

	private BigDecimal costo;

	private String descripcion;

	@Column(name="fecha_actualizacion")
	private Timestamp fechaActualizacion;

	@Column(name="fecha_registro")
	private Timestamp fechaRegistro;

	private String imagen;

	private String nombre;

	@Column(name="precio_final")
	private BigDecimal precioFinal;

	@Column(name="precio_sin_iva")
	private BigDecimal precioSinIva;

	@Column(name="stock_minimo")
	private Integer stockMinimo;

	@Column(name="stock_total")
	private Integer stockTotal;

	//bi-directional many-to-one association to PrdPedidoDetalle
	@OneToMany(mappedBy="proProducto")
	private List<PrdPedidoDetalle> prdPedidoDetalles;

	//bi-directional many-to-one association to ProCategoria
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private ProCategoria proCategoria;

	//bi-directional many-to-one association to PrvProveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private PrvProveedor prvProveedor;

	//bi-directional many-to-one association to ProProductoCatalogo
	@OneToMany(mappedBy="proProducto")
	private List<ProProductoCatalogo> proProductoCatalogos;

	//bi-directional many-to-one association to VenFacturaDetalle
	@OneToMany(mappedBy="proProducto")
	private List<VenFacturaDetalle> venFacturaDetalles;

	public ProProducto() {
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getCosto() {
		return this.costo;
	}

	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Timestamp getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecioFinal() {
		return this.precioFinal;
	}

	public void setPrecioFinal(BigDecimal precioFinal) {
		this.precioFinal = precioFinal;
	}

	public BigDecimal getPrecioSinIva() {
		return this.precioSinIva;
	}

	public void setPrecioSinIva(BigDecimal precioSinIva) {
		this.precioSinIva = precioSinIva;
	}

	public Integer getStockMinimo() {
		return this.stockMinimo;
	}

	public void setStockMinimo(Integer stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public Integer getStockTotal() {
		return this.stockTotal;
	}

	public void setStockTotal(Integer stockTotal) {
		this.stockTotal = stockTotal;
	}

	public List<PrdPedidoDetalle> getPrdPedidoDetalles() {
		return this.prdPedidoDetalles;
	}

	public void setPrdPedidoDetalles(List<PrdPedidoDetalle> prdPedidoDetalles) {
		this.prdPedidoDetalles = prdPedidoDetalles;
	}

	public PrdPedidoDetalle addPrdPedidoDetalle(PrdPedidoDetalle prdPedidoDetalle) {
		getPrdPedidoDetalles().add(prdPedidoDetalle);
		prdPedidoDetalle.setProProducto(this);

		return prdPedidoDetalle;
	}

	public PrdPedidoDetalle removePrdPedidoDetalle(PrdPedidoDetalle prdPedidoDetalle) {
		getPrdPedidoDetalles().remove(prdPedidoDetalle);
		prdPedidoDetalle.setProProducto(null);

		return prdPedidoDetalle;
	}

	public ProCategoria getProCategoria() {
		return this.proCategoria;
	}

	public void setProCategoria(ProCategoria proCategoria) {
		this.proCategoria = proCategoria;
	}

	public PrvProveedor getPrvProveedor() {
		return this.prvProveedor;
	}

	public void setPrvProveedor(PrvProveedor prvProveedor) {
		this.prvProveedor = prvProveedor;
	}

	public List<ProProductoCatalogo> getProProductoCatalogos() {
		return this.proProductoCatalogos;
	}

	public void setProProductoCatalogos(List<ProProductoCatalogo> proProductoCatalogos) {
		this.proProductoCatalogos = proProductoCatalogos;
	}

	public ProProductoCatalogo addProProductoCatalogo(ProProductoCatalogo proProductoCatalogo) {
		getProProductoCatalogos().add(proProductoCatalogo);
		proProductoCatalogo.setProProducto(this);

		return proProductoCatalogo;
	}

	public ProProductoCatalogo removeProProductoCatalogo(ProProductoCatalogo proProductoCatalogo) {
		getProProductoCatalogos().remove(proProductoCatalogo);
		proProductoCatalogo.setProProducto(null);

		return proProductoCatalogo;
	}

	public List<VenFacturaDetalle> getVenFacturaDetalles() {
		return this.venFacturaDetalles;
	}

	public void setVenFacturaDetalles(List<VenFacturaDetalle> venFacturaDetalles) {
		this.venFacturaDetalles = venFacturaDetalles;
	}

	public VenFacturaDetalle addVenFacturaDetalle(VenFacturaDetalle venFacturaDetalle) {
		getVenFacturaDetalles().add(venFacturaDetalle);
		venFacturaDetalle.setProProducto(this);

		return venFacturaDetalle;
	}

	public VenFacturaDetalle removeVenFacturaDetalle(VenFacturaDetalle venFacturaDetalle) {
		getVenFacturaDetalles().remove(venFacturaDetalle);
		venFacturaDetalle.setProProducto(null);

		return venFacturaDetalle;
	}

}