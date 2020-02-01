package craftserp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.VenFormaPago;
import craftserp.model.manager.ManagerFormapago;

@Named
@SessionScoped

public class BeanFormapago implements Serializable {

	
	@EJB
	private ManagerFormapago managerformapago;
	private List<VenFormaPago> listaformapago;
	private VenFormaPago formapago;
	private VenFormaPago pagoseleccionado;
	
	@PostConstruct
	public void inicializar() {
		listaformapago = managerformapago.findAllFormapago();
		formapago = new VenFormaPago();
	}
	
	public void actionListenerInsertarFormapago() {
			try {
				managerformapago.InsertarFormaPago(formapago);
				listaformapago = managerformapago.findAllFormapago();
				formapago = new VenFormaPago();
				JSFUtil.crearMensajeInfo("Datos Ingreados");
			} catch (Exception e) {
				e.printStackTrace();
			}		
	}
	
	public void actionELiminarFormaPago(Integer id) {
		managerformapago.eliminarFormapago(id);
		listaformapago = managerformapago.findAllFormapago();
		JSFUtil.crearMensajeInfo("Forma de pago eliminado");
	}
	
	public void actionListenerSeleccionarFormapago(VenFormaPago pago) {
		pagoseleccionado = pago;
	}
	
	public void actionListenerActualizarFormapago() {
		try {
			managerformapago.actualizarFormapago(pagoseleccionado);
			listaformapago = managerformapago.findAllFormapago();
			JSFUtil.crearMensajeInfo("Datos actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public VenFormaPago getPagoseleccionado() {
		return pagoseleccionado;
	}

	public void setPagoseleccionado(VenFormaPago pagoseleccionado) {
		this.pagoseleccionado = pagoseleccionado;
	}

	public List<VenFormaPago> getListaformapago() {
		return listaformapago;
	}

	public void setListaformapago(List<VenFormaPago> listaformapago) {
		this.listaformapago = listaformapago;
	}

	public VenFormaPago getFormapago() {
		return formapago;
	}

	public void setFormapago(VenFormaPago formapago) {
		this.formapago = formapago;
	}
	
	
}