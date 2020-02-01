package craftserp.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.PrvProveedor;
import craftserp.model.entities.SegCompania;
import craftserp.model.manager.ManagerCompania;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BeanCompania implements Serializable {

	@EJB
	private ManagerCompania managerCompania;
	private List<SegCompania> listacompania;
	private SegCompania compania;
	private SegCompania companiaseleccionada;
	private int ciudad;

	@PostConstruct
	public void inicializar() {
		listacompania = managerCompania.findAllCompania();
		compania = new SegCompania();
	}

	public void actionListenerInsertarCompania() {
		try {
			managerCompania.insertarCompania(compania, ciudad);
			listacompania = managerCompania.findAllCompania();
			compania = new SegCompania();
			JSFUtil.crearMensajeInfo("Datos de la compania insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerEliminarCompania(Integer id) {
		managerCompania.eliminarCompania(id);
		listacompania = managerCompania.findAllCompania();
		JSFUtil.crearMensajeInfo("Dato de compania eliminada");
	}

	public void actionListenerSeleccionarCompania(SegCompania compania) {

		companiaseleccionada = compania;
	}

	public void actionListenerActualizarCompania() {

		try {
			managerCompania.ActualizarCompania(companiaseleccionada);
			listacompania = managerCompania.findAllCompania();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<SegCompania> getListacompania() {
		return listacompania;
	}

	public void setListacompania(List<SegCompania> listacompania) {
		this.listacompania = listacompania;
	}

	public SegCompania getCompania() {
		return compania;
	}

	public void setCompania(SegCompania compania) {
		this.compania = compania;
	}

	public SegCompania getCompaniaseleccionada() {
		return companiaseleccionada;
	}

	public void setCompaniaseleccionada(SegCompania companiaseleccionada) {
		this.companiaseleccionada = companiaseleccionada;
	}

	public int getCiudad() {
		return ciudad;
	}

	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}

	
}
