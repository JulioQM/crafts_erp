package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the prd_pedido_detalle database table.
 * 
 */
@Entity
@Table(name="prd_pedido_detalle")
@NamedQuery(name="PrdPedidoDetalle.findAll", query="SELECT p FROM PrdPedidoDetalle p")
public class PrdPedidoDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido_detalle")
	private Integer idPedidoDetalle;

	private Integer cantidad;

	private BigDecimal descuento;

	@Column(name="precio_unitario_compra")
	private BigDecimal precioUnitarioCompra;

	//bi-directional many-to-one association to PrdPedidoCabecera
	@ManyToOne
	@JoinColumn(name="id_pedido_cabecera")
	private PrdPedidoCabecera prdPedidoCabecera;

	//bi-directional many-to-one association to ProProducto
	@ManyToOne
	@JoinColumn(name="id_producto")
	private ProProducto proProducto;

	public PrdPedidoDetalle() {
	}

	public Integer getIdPedidoDetalle() {
		return this.idPedidoDetalle;
	}

	public void setIdPedidoDetalle(Integer idPedidoDetalle) {
		this.idPedidoDetalle = idPedidoDetalle;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getPrecioUnitarioCompra() {
		return this.precioUnitarioCompra;
	}

	public void setPrecioUnitarioCompra(BigDecimal precioUnitarioCompra) {
		this.precioUnitarioCompra = precioUnitarioCompra;
	}

	public PrdPedidoCabecera getPrdPedidoCabecera() {
		return this.prdPedidoCabecera;
	}

	public void setPrdPedidoCabecera(PrdPedidoCabecera prdPedidoCabecera) {
		this.prdPedidoCabecera = prdPedidoCabecera;
	}

	public ProProducto getProProducto() {
		return this.proProducto;
	}

	public void setProProducto(ProProducto proProducto) {
		this.proProducto = proProducto;
	}

}