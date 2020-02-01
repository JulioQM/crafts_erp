package craftserp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import craftserp.model.entities.PrvProveedor;
import craftserp.model.manager.ManagerProveedor;


@Named
@SessionScoped
public class BeanProveedor implements Serializable{
	
	
	@EJB
	private ManagerProveedor managerproveedor;
	private List<PrvProveedor> listaPrvProveedores;
	private PrvProveedor proveedor;
	private PrvProveedor proveedorSeleccionado;
	private int idciudad;
	
	@PostConstruct
	public void inicializar() {
		listaPrvProveedores=managerproveedor.findAllProveedores();
		proveedor= new PrvProveedor();
	}
	
	/*public void actionListenerInsertarProveedor() {
		
		try {
			managerproveedor.insertarProveedor(proveedor);
			listaPrvProveedores=managerproveedor.findAllProveedores();
			proveedor=new PrvProveedor();
			JSFUtil.crearMensajeInfo("Datos de proveedor insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
		
	}*/

	public void actionListenerInsertar() {
		try {
		managerproveedor.insertarPro(proveedor,idciudad);
		listaPrvProveedores=managerproveedor.findAllProveedores();
		proveedor=new PrvProveedor();
		//JSFUtil.crearMensajeInfo("Datos de proveedor insertados");
		} catch (Exception e) {
			///JSFUtil.crearMensajeError(e.getMessage());
			//e.printStackTrace();
		}
		
	}
	public void actionLIstenerEliminarProveedor(Integer id) {
		managerproveedor.eliminarProveedor(id);
		listaPrvProveedores= managerproveedor.findAllProveedores();
		JSFUtil.crearMensajeInfo("Proveedor Eliminado");
	}
/*public void actionListenerEliminarEstudiante(String cedula) {
		
		managerEstudiante.eliminarEstudiante(cedula);
		listaEstudiante= managerEstudiante.findAllEstudiantes();
		JSFUtil.crearMensajeInfo("Estudiante eliminado");
		
		
	}
	public void actionListenerSeleccionarEstudiante(Estudiante estudiante) {
		
		estudianteSeleccionado=estudiante;
		
	}
	public void actionListenerActualizarEstudiante() {
		try {
		managerEstudiante.actualizarEstudiante(estudianteSeleccionado);
		listaEstudiante=managerEstudiante.findAllEstudiantes();
		JSFUtil.crearMensajeInfo("Datos actualizados");
		}catch(Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			
		}
	}*/
	
	public void actionListenerSeleccionarProveedor(PrvProveedor proveedor) {
		
		proveedorSeleccionado=proveedor;
	}
	public void actionListenerActualizarProveedor() {
		
		try {
			managerproveedor.ActualizarProveedor(proveedorSeleccionado);
			listaPrvProveedores= managerproveedor.findAllProveedores();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		
	}
	public List<PrvProveedor> getListaPrvProveedores() {
		return listaPrvProveedores;
	}

	public void setListaPrvProveedores(List<PrvProveedor> listaPrvProveedores) {
		this.listaPrvProveedores = listaPrvProveedores;
	}

	public PrvProveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(PrvProveedor proveedor) {
		this.proveedor = proveedor;
	}

	public PrvProveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(PrvProveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}

	public int getIdciudad() {
		return idciudad;
	}

	public void setIdciudad(int idciudad) {
		this.idciudad = idciudad;
	}

	
	

	
	
	

	
}
