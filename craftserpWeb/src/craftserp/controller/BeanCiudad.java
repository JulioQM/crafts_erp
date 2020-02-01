package craftserp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.SegCiudad;
import craftserp.model.manager.ManagerCiudad;
import java.io.Serializable;

@Named
@SessionScoped

public class BeanCiudad implements Serializable {
	
	
	
	@EJB
	private ManagerCiudad managerCiudad;
	private List<SegCiudad> listaCiudad;
	private SegCiudad ciudad;
	private int idtipolibro;
	private SegCiudad ciudadSelecionado;
	private String nombreCiudad;

	@PostConstruct
	public void inicializar() {
		listaCiudad = managerCiudad.findAllCiudad();
		ciudad = new SegCiudad();
	}

	public void actionListenerInsertarCiudad() {
		try {
			managerCiudad.insertarCiudad(nombreCiudad);
			listaCiudad = managerCiudad.findAllCiudad();
			ciudad = new SegCiudad();
			JSFUtil.crearMensajeInfo("Datos de ciudad Registrada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Esta ciudad ya se encuentra registrada");
		}
	}

       //sirve para eliminar
	
	public void actionListenerEliminarCiudad(Integer idciudad) {
		managerCiudad.eliminarCiudad(idciudad);
		listaCiudad = managerCiudad.findAllCiudad();
		JSFUtil.crearMensajeInfo("Ciudad eliminado");
	}

	public void actionListenerSeleccionarCiudad(SegCiudad ciudad) {
		ciudadSelecionado = ciudad;
	}

	/*public void actionListenerActualizarCiudad() {
		try {
			managerCiudad.actualizarCiudad(ciudadSelecionado);
			listaCiudad = managerCiudad.findAllCiudad();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}*/

	// set y get
	public List<SegCiudad> getListaCiudad() {
		return listaCiudad;
	}

	public void setListaCiudad(List<SegCiudad> listaCiudad) {
		this.listaCiudad = listaCiudad;
	}

	public SegCiudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(SegCiudad ciudad) {
		this.ciudad = ciudad;
	}

	public int getIdtipolibro() {
		return idtipolibro;
	}

	public void setIdtipolibro(int idtipolibro) {
		this.idtipolibro = idtipolibro;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public SegCiudad getCiudadSelecionado() {
		return ciudadSelecionado;
	}

	public void setCiudadSelecionado(SegCiudad ciudadSelecionado) {
		this.ciudadSelecionado = ciudadSelecionado;
	}

}
