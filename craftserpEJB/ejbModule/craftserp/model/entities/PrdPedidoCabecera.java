package craftserp.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the prd_pedido_cabecera database table.
 * 
 */
@Entity
@Table(name="prd_pedido_cabecera")
@NamedQuery(name="PrdPedidoCabecera.findAll", query="SELECT p FROM PrdPedidoCabecera p")
public class PrdPedidoCabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido_cabecera")
	private Integer idPedidoCabecera;

	@Column(name="fecha_entrega")
	private Timestamp fechaEntrega;

	@Column(name="fecha_pedido")
	private Timestamp fechaPedido;

	@Column(name="numero_pedido")
	private String numeroPedido;

	@Column(name="valor_iva")
	private BigDecimal valorIva;

	@Column(name="valor_total")
	private BigDecimal valorTotal;

	//bi-directional many-to-one association to PrdEstado
	@ManyToOne
	@JoinColumn(name="id_estado")
	private PrdEstado prdEstado;

	//bi-directional many-to-one association to PrvProveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor")
	private PrvProveedor prvProveedor;

	//bi-directional many-to-one association to PrdPedidoDetalle
	@OneToMany(mappedBy="prdPedidoCabecera")
	private List<PrdPedidoDetalle> prdPedidoDetalles;

	public PrdPedidoCabecera() {
	}

	public Integer getIdPedidoCabecera() {
		return this.idPedidoCabecera;
	}

	public void setIdPedidoCabecera(Integer idPedidoCabecera) {
		this.idPedidoCabecera = idPedidoCabecera;
	}

	public Timestamp getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Timestamp getFechaPedido() {
		return this.fechaPedido;
	}

	public void setFechaPedido(Timestamp fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getNumeroPedido() {
		return this.numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public BigDecimal getValorIva() {
		return this.valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public PrdEstado getPrdEstado() {
		return this.prdEstado;
	}

	public void setPrdEstado(PrdEstado prdEstado) {
		this.prdEstado = prdEstado;
	}

	public PrvProveedor getPrvProveedor() {
		return this.prvProveedor;
	}

	public void setPrvProveedor(PrvProveedor prvProveedor) {
		this.prvProveedor = prvProveedor;
	}

	public List<PrdPedidoDetalle> getPrdPedidoDetalles() {
		return this.prdPedidoDetalles;
	}

	public void setPrdPedidoDetalles(List<PrdPedidoDetalle> prdPedidoDetalles) {
		this.prdPedidoDetalles = prdPedidoDetalles;
	}

	public PrdPedidoDetalle addPrdPedidoDetalle(PrdPedidoDetalle prdPedidoDetalle) {
		getPrdPedidoDetalles().add(prdPedidoDetalle);
		prdPedidoDetalle.setPrdPedidoCabecera(this);

		return prdPedidoDetalle;
	}

	public PrdPedidoDetalle removePrdPedidoDetalle(PrdPedidoDetalle prdPedidoDetalle) {
		getPrdPedidoDetalles().remove(prdPedidoDetalle);
		prdPedidoDetalle.setPrdPedidoCabecera(null);

		return prdPedidoDetalle;
	}

}