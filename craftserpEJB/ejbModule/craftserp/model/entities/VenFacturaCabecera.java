package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the ven_factura_cabecera database table.
 * 
 */
@Entity
@Table(name="ven_factura_cabecera")
@NamedQuery(name="VenFacturaCabecera.findAll", query="SELECT v FROM VenFacturaCabecera v")
public class VenFacturaCabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fact_cab")
	private Integer idFactCab;

	private String direccion;

	@Column(name="fecha_emision")
	private Timestamp fechaEmision;

	private BigDecimal iva;

	@Column(name="numero_factura")
	private String numeroFactura;

	private BigDecimal subtotal;

	private BigDecimal tarifa;

	@Column(name="tarifa_cero")
	private BigDecimal tarifaCero;

	private BigDecimal total;

	//bi-directional many-to-one association to PrdEstado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private PrdEstado prdEstado;

	//bi-directional many-to-one association to SegCompania
	@ManyToOne
	@JoinColumn(name="id_compania")
	private SegCompania segCompania;

	//bi-directional many-to-one association to SegPersona
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private SegPersona segPersona1;

	//bi-directional many-to-one association to SegPersona
	@ManyToOne
	@JoinColumn(name="id_cajero")
	private SegPersona segPersona2;

	//bi-directional many-to-one association to VenFormaPago
	@ManyToOne
	@JoinColumn(name="id_forma_pago")
	private VenFormaPago venFormaPago;

	//bi-directional many-to-one association to VenFacturaDetalle
	@OneToMany(mappedBy="venFacturaCabecera")
	private List<VenFacturaDetalle> venFacturaDetalles;

	public VenFacturaCabecera() {
	}

	public Integer getIdFactCab() {
		return this.idFactCab;
	}

	public void setIdFactCab(Integer idFactCab) {
		this.idFactCab = idFactCab;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Timestamp getFechaEmision() {
		return this.fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public String getNumeroFactura() {
		return this.numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

	public BigDecimal getTarifaCero() {
		return this.tarifaCero;
	}

	public void setTarifaCero(BigDecimal tarifaCero) {
		this.tarifaCero = tarifaCero;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public PrdEstado getPrdEstado() {
		return this.prdEstado;
	}

	public void setPrdEstado(PrdEstado prdEstado) {
		this.prdEstado = prdEstado;
	}

	public SegCompania getSegCompania() {
		return this.segCompania;
	}

	public void setSegCompania(SegCompania segCompania) {
		this.segCompania = segCompania;
	}

	public SegPersona getSegPersona1() {
		return this.segPersona1;
	}

	public void setSegPersona1(SegPersona segPersona1) {
		this.segPersona1 = segPersona1;
	}

	public SegPersona getSegPersona2() {
		return this.segPersona2;
	}

	public void setSegPersona2(SegPersona segPersona2) {
		this.segPersona2 = segPersona2;
	}

	public VenFormaPago getVenFormaPago() {
		return this.venFormaPago;
	}

	public void setVenFormaPago(VenFormaPago venFormaPago) {
		this.venFormaPago = venFormaPago;
	}

	public List<VenFacturaDetalle> getVenFacturaDetalles() {
		return this.venFacturaDetalles;
	}

	public void setVenFacturaDetalles(List<VenFacturaDetalle> venFacturaDetalles) {
		this.venFacturaDetalles = venFacturaDetalles;
	}

	public VenFacturaDetalle addVenFacturaDetalle(VenFacturaDetalle venFacturaDetalle) {
		getVenFacturaDetalles().add(venFacturaDetalle);
		venFacturaDetalle.setVenFacturaCabecera(this);

		return venFacturaDetalle;
	}

	public VenFacturaDetalle removeVenFacturaDetalle(VenFacturaDetalle venFacturaDetalle) {
		getVenFacturaDetalles().remove(venFacturaDetalle);
		venFacturaDetalle.setVenFacturaCabecera(null);

		return venFacturaDetalle;
	}

}