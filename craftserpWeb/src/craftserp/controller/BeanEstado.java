package craftserp.controller;

import java.io.Serializable; 
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.PrdEstado;

import craftserp.model.manager.ManagerEstado;

@Named
@SessionScoped

public class BeanEstado implements Serializable {
	//private static final long serialVersionUID = 1L;

	@EJB
	private ManagerEstado managerestado;
	private List<PrdEstado> listaestado;
	private PrdEstado estado;
	private PrdEstado estadoseleccionado;

	@PostConstruct
	public void inicializar() {
		listaestado = managerestado.findAllEstados();
		estado = new PrdEstado();
	}

	public void actionListenerInsertarEstado() {
		try {
			managerestado.insertarEstado(estado);
			listaestado = managerestado.findAllEstados();
			estado = new PrdEstado();
			JSFUtil.crearMensajeInfo("Datos de estado insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionEliminarEstado(Integer id) {
		managerestado.eliminarEstado(id);
		listaestado = managerestado.findAllEstados();
		JSFUtil.crearMensajeInfo("Estado eliminado");

	}

	public void actionEstadoSeleccionado(PrdEstado estado) {
		estadoseleccionado = estado;
	}

	public void actionListenerActualizarEstado() {

		try {
			managerestado.actualizarEstado(estadoseleccionado);
			listaestado = managerestado.findAllEstados();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<PrdEstado> getListaestado() {
		return listaestado;
	}

	public void setListaestado(List<PrdEstado> listaestado) {
		this.listaestado = listaestado;
	}

	public PrdEstado getEstado() {
		return estado;
	}

	public void setEstado(PrdEstado estado) {
		this.estado = estado;
	}

	public PrdEstado getEstadoseleccionado() {
		return estadoseleccionado;
	}

	public void setEstadoseleccionado(PrdEstado estadoseleccionado) {
		this.estadoseleccionado = estadoseleccionado;
	}

	/*
	 * public void actionListenerInsertarProveedor() {
	 * 
	 * try { managerproveedor.insertarProveedor(proveedor);
	 * listaPrvProveedores=managerproveedor.findAllProveedores(); proveedor=new
	 * PrvProveedor(); JSFUtil.crearMensajeInfo("Datos de proveedor insertados"); }
	 * catch (Exception e) { JSFUtil.crearMensajeError(e.getMessage());
	 * e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */
	/*
	 * public void actionListenerEliminarEstudiante(String cedula) {
	 * 
	 * managerEstudiante.eliminarEstudiante(cedula); listaEstudiante=
	 * managerEstudiante.findAllEstudiantes();
	 * JSFUtil.crearMensajeInfo("Estudiante eliminado");
	 * 
	 * 
	 * } public void actionListenerSeleccionarEstudiante(Estudiante estudiante) {
	 * 
	 * estudianteSeleccionado=estudiante;
	 * 
	 * } public void actionListenerActualizarEstudiante() { try {
	 * managerEstudiante.actualizarEstudiante(estudianteSeleccionado);
	 * listaEstudiante=managerEstudiante.findAllEstudiantes();
	 * JSFUtil.crearMensajeInfo("Datos actualizados"); }catch(Exception e) {
	 * JSFUtil.crearMensajeError(e.getMessage()); e.printStackTrace();
	 * 
	 * } }
	 */

}
