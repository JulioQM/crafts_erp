package craftserp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import craftserp.model.entities.ProProducto;
import craftserp.model.entities.SegPersona;
import craftserp.model.entities.VenFacturaCabecera;
import craftserp.model.manager.ManagerFacturacion;
import craftserp.model.manager.ManagerPersona;
import craftserp.model.manager.ManagerProducto;

@Named
@SessionScoped
public class BeanFactura implements Serializable {

	private static final long serialVersionUID = 1L;
	private String cedulaCliente;
	@EJB
	private ManagerFacturacion managerFacturacion;
	@EJB
	private ManagerProducto managerProductos;
	@EJB
	private ManagerPersona managerClientes;
	
	private Integer codigoProducto;
	private Integer cantidadProducto;
	private VenFacturaCabecera facturaCabTmp;
	private boolean facturaCabTmpGuardada;
	
	//Inyeccion de beans manejados:
	@Inject
	private BeanLogin beanLogin;
	
	public BeanFactura() {
		
	}

	/**
	 * Action para la creacion de una factura temporal en memoria.
	 * Hace uso del componente {@link facturacion.model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return outcome para la navegacion.
	 */
	public String crearNuevaFactura(){
		facturaCabTmp=managerFacturacion.crearFacturaTmp();
		cedulaCliente=null;
		codigoProducto=0;
		cantidadProducto=0;
		facturaCabTmpGuardada=false;
		return "";
	}
	/**
	 * Action para asignar un cliente a la factura temporal actual.
	 * Hace uso del componente {@link facturacion.model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return outcome para la navegacion.
	 */
	public void asignarCliente(){
		if(facturaCabTmpGuardada==true){
			JSFUtil.crearMensajeWarning("La factura ya fue guardada.");
		}
		try {
			managerFacturacion.asignarClienteFacturaTmp(facturaCabTmp,cedulaCliente);
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
	}
	
	public void verificarExistencia(){
		try {
			if(managerProductos.obtenerExistencia(codigoProducto)==0)
				JSFUtil.crearMensajeError("No hay existencia");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Action que adiciona un item a una factura temporal.
	 * Hace uso del componente {@link model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return
	 */
	public String insertarDetalle(){
		if(facturaCabTmpGuardada==true){
			JSFUtil.crearMensajeWarning("La factura ya fue guardada.");
			return "";
		}
		try {
			managerFacturacion.agregarDetalleFacturaTmp(facturaCabTmp,codigoProducto, cantidadProducto);
			codigoProducto=0;
			cantidadProducto=0;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}		
		return "";
	}
	
	/**
	 * Action que almacena en la base de datos una factura temporal creada en memoria.
	 * Hace uso del componente {@link facturacion.model.manager.ManagerFacturacion ManagerFacturacion} de la capa model.
	 * @return outcome para la navegacion.
	 */
	public String guardarFactura(){
		if(facturaCabTmpGuardada==true){
			JSFUtil.crearMensajeWarning("La factura ya fue guardada.");
			return "";
		}
		try {
			managerFacturacion.guardarFacturaTemporal(beanLogin.getCodigoUsuario(),facturaCabTmp);
			facturaCabTmpGuardada=true;
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}
		
		return "";
	}
	
	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public Integer getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	/**
	 * Devuelve un listado de componentes SelectItem a partir
	 * de un listado de {@link facturacion.model.dao.entities.Cliente Cliente}.
	 * @return listado de SelectItems de clientes.
	 */
	public List<SelectItem> getListaClientesSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<SegPersona> listadoClientes=managerClientes.findAllPersona();
		
		for(SegPersona c:listadoClientes){
			SelectItem item=new SelectItem(c.getIdCedula(), 
					                   c.getApellido()+" "+c.getNombre());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	/**
	 * Devuelve un listado de componentes SelectItem a partir
	 * de un listado de {@link facturacion.model.dao.entities.Producto Producto}.
	 * 
	 * @return listado de SelectItems de productos.
	 */
	public List<SelectItem> getListaProductosSI(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<ProProducto> listadoProductos=managerProductos.findAllProducto();
		
		for(ProProducto p:listadoProductos){
			SelectItem item=new SelectItem(p.getCodigo(), 
					                   p.getNombre());
			listadoSI.add(item);
		}
		return listadoSI;
	}

	public VenFacturaCabecera getFacturaCabTmp() {
		return facturaCabTmp;
	}

	public void setFacturaCabTmp(VenFacturaCabecera facturaCabTmp) {
		this.facturaCabTmp = facturaCabTmp;
	}
	
	public List<VenFacturaCabecera> getListaFacturasCab(){
		List<VenFacturaCabecera> listadoFacturas=managerFacturacion.findAllFacturaCab();
		return listadoFacturas;
	}

	public boolean isFacturaCabTmpGuardada() {
		return facturaCabTmpGuardada;
	}

	public void setFacturaCabTmpGuardada(boolean facturaCabTmpGuardada) {
		this.facturaCabTmpGuardada = facturaCabTmpGuardada;
	}

	public BeanLogin getBeanLogin() {
		return beanLogin;
	}

	public void setBeanLogin(BeanLogin beanLogin) {
		this.beanLogin = beanLogin;
	}
	

}
