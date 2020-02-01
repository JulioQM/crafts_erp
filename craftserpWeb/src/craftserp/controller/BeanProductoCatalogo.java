package craftserp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import craftserp.model.entities.ProProductoCatalogo;

import craftserp.model.manager.ManagerProductoCatalogo;

@Named
@SessionScoped

public class BeanProductoCatalogo  implements Serializable{

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerProductoCatalogo managerproductocatalogo;
	private List<ProProductoCatalogo> listaProProductoCatalogos;
	private ProProductoCatalogo productocatalogo;
	private ProProductoCatalogo productocatalogoSeleccionado;
	
	@PostConstruct
	public void inicializar() {
		listaProProductoCatalogos = managerproductocatalogo.findAllProductoCatalogo();
		productocatalogo= new ProProductoCatalogo();
	}

	public void actionListenerInsertar() {
		try {
			managerproductocatalogo.InsertarProductoCat(productocatalogo);;
		listaProProductoCatalogos=managerproductocatalogo.findAllProductoCatalogo();
		productocatalogo= new ProProductoCatalogo();
		JSFUtil.crearMensajeInfo("Datos de catalogo insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public void actionLIstenerEliminarCatalogo(Integer id) {
		managerproductocatalogo.eliminarProductoCatalogo(id);
		listaProProductoCatalogos= managerproductocatalogo.findAllProductoCatalogo();
		JSFUtil.crearMensajeInfo("Catalogo Eliminado");
	}
	
	public void actionListenerSeleccionarCatalogo(ProProductoCatalogo productocatalogo) {
		
		productocatalogoSeleccionado=productocatalogo;
	}
	
	public void actionListenerActualizarCatalogo() {
		
		try {
			managerproductocatalogo.ActualizarProductoCatalogo(productocatalogoSeleccionado);
			listaProProductoCatalogos= managerproductocatalogo.findAllProductoCatalogo();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}

	public List<ProProductoCatalogo> getListaProProductoCatalogos() {
		return listaProProductoCatalogos;
	}

	public void setListaProProductoCatalogos(List<ProProductoCatalogo> listaProProductoCatalogos) {
		this.listaProProductoCatalogos = listaProProductoCatalogos;
	}

	public ProProductoCatalogo getProductocatalogo() {
		return productocatalogo;
	}

	public void setProductocatalogo(ProProductoCatalogo productocatalogo) {
		this.productocatalogo = productocatalogo;
	}

	public ProProductoCatalogo getProductocatalogoSeleccionado() {
		return productocatalogoSeleccionado;
	}

	public void setProductocatalogoSeleccionado(ProProductoCatalogo productocatalogoSeleccionado) {
		this.productocatalogoSeleccionado = productocatalogoSeleccionado;
	}

	

}
