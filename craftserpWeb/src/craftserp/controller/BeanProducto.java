package craftserp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.ProProducto;
import craftserp.model.manager.ManagerProducto;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanProducto implements Serializable {

	
	@EJB
	private ManagerProducto managerProducto;
	private List<ProProducto> listaproducto;
	private ProProducto producto;
	private ProProducto productoseleccionado;
	private int idcategoria;
	private int idproveedor;

	@PostConstruct
	public void inicializar() {

		listaproducto = managerProducto.findAllProducto();
		producto = new ProProducto();
	}

	public void actionListenerInsertarProducto() {
		try {
			managerProducto.insertarProducto(producto, idcategoria, idproveedor);
			listaproducto = managerProducto.findAllProducto();
			producto = new ProProducto();
			JSFUtil.crearMensajeInfo("Datos del producto insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionEliminarProducto(Integer id) {
		managerProducto.eliminarProducto(id);
		listaproducto = managerProducto.findAllProducto();
		JSFUtil.crearMensajeInfo("Producto Eliminado");
	}

	public void actionListenerSeleccionarProducto(ProProducto producto) {
		productoseleccionado = producto;
	}

	public void actionListenerActualizarProducto() {

		try {
			managerProducto.ActualizarPrducto(productoseleccionado);
			listaproducto = managerProducto.findAllProducto();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			//e.printStackTrace();
		}

	}

	public List<ProProducto> getListaproducto() {
		return listaproducto;
	}

	public void setListaproducto(List<ProProducto> listaproducto) {
		this.listaproducto = listaproducto;
	}

	public ProProducto getProducto() {
		return producto;
	}

	public void setProducto(ProProducto producto) {
		this.producto = producto;
	}

	public ProProducto getProductoseleccionado() {
		return productoseleccionado;
	}

	public void setProductoseleccionado(ProProducto productoseleccionado) {
		this.productoseleccionado = productoseleccionado;
	}



	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public int getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}
	
	

}
