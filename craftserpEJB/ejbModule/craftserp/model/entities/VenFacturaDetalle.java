package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ven_factura_detalle database table.
 * 
 */
@Entity
@Table(name="ven_factura_detalle")
@NamedQuery(name="VenFacturaDetalle.findAll", query="SELECT v FROM VenFacturaDetalle v")
public class VenFacturaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fact_det")
	private Integer idFactDet;

	private Integer cantidad;

	private String descripcion;

	private Integer iva;

	private BigDecimal precio;

	//bi-directional many-to-one association to ProProducto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private ProProducto proProducto;

	//bi-directional many-to-one association to VenFacturaCabecera
	@ManyToOne
	@JoinColumn(name="id_fact_cab")
	private VenFacturaCabecera venFacturaCabecera;

	public VenFacturaDetalle() {
	}

	public Integer getIdFactDet() {
		return this.idFactDet;
	}

	public void setIdFactDet(Integer idFactDet) {
		this.idFactDet = idFactDet;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getIva() {
		return this.iva;
	}

	public void setIva(Integer iva) {
		this.iva = iva;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public ProProducto getProProducto() {
		return this.proProducto;
	}

	public void setProProducto(ProProducto proProducto) {
		this.proProducto = proProducto;
	}

	public VenFacturaCabecera getVenFacturaCabecera() {
		return this.venFacturaCabecera;
	}

	public void setVenFacturaCabecera(VenFacturaCabecera venFacturaCabecera) {
		this.venFacturaCabecera = venFacturaCabecera;
	}

}