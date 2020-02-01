package craftserp.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import craftserp.model.entities.SegCiudad;
import craftserp.model.entities.SegPersona;

import craftserp.model.manager.ManagerPersona;

@Named
@SessionScoped
public class BeanPersona implements Serializable {
	private static final long SerialVersionUID = 1L;

	@EJB
	private ManagerPersona managerPersona;
	private List<SegPersona> listaPersona;
	private SegPersona persona;
	private int idCiudad;
	private int idEstadoCivil;
	private int idRol;
	private String cedula;
	private SegPersona cedulaSelecionada;

	@PostConstruct
	public void inicializar() {
		listaPersona = managerPersona.findAllPersona();
		persona = new SegPersona();

	}
	
	// este metodo me permite agregar el cliente que la misma se registre en el rol

	public void actionListenerInsertarPersona() {
		try {

			managerPersona.insertarPersona(persona, idCiudad, idEstadoCivil, idRol);
			listaPersona = managerPersona.findAllPersona();
			persona = new SegPersona();
			JSFUtil.crearMensajeInfo("Datos de persona registrada");

		} catch (Exception e) {
			//JSFUtil.crearMensajeError("Esta persona ya se encuentra registrada");
		}
	}

	// sirve para eliminar
	public void actionListenerEliminarPersona(String cedula) {
		managerPersona.eliminarPersona(cedula);
		listaPersona = managerPersona.findAllPersona();
		JSFUtil.crearMensajeInfo(" eliminado");
	}

//selecionar a una persona
	public void actionListenerSeleccionarPersona(SegPersona persona) {
		cedulaSelecionada = persona;
	}

//sirve para actualizar
	public void actionListenerActualizarPersona() {
		try {
			managerPersona.actualizarPersona(cedulaSelecionada, idCiudad, idEstadoCivil,idRol);
			listaPersona = managerPersona.findAllPersona();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public String salirSistema() {
		System.out.println("salirSistema");

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/faces/landing.html?faces-redirect=true";
	}
	
//Get y set
	public List<SegPersona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<SegPersona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public SegPersona getPersona() {
		return persona;
	}

	public void setPersona(SegPersona persona) {
		this.persona = persona;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public int getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(int idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public SegPersona getCedulaSelecionada() {
		return cedulaSelecionada;
	}

	public void setCedulaSelecionada(SegPersona cedulaSelecionada) {
		this.cedulaSelecionada = cedulaSelecionada;
	}
	
}

	