package craftserp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import craftserp.model.entities.SegEstadoCivil;
import craftserp.model.manager.ManagerEstadoCivil;

@Named
@SessionScoped
public class BeanEstadoCivil implements Serializable {
	private String nombreEstadoCivil;

	@EJB
	private ManagerEstadoCivil managerEstadoCivil;
	private List<SegEstadoCivil> listaEstadoCivil;
	private SegEstadoCivil estadocivil;
	private int id;
	private SegEstadoCivil estadoCivilSelecionada;

	@PostConstruct
	public void inicializar() {
		listaEstadoCivil = managerEstadoCivil.findAllEstadoCivil();
		estadocivil = new SegEstadoCivil();
		estadoCivilSelecionada = new SegEstadoCivil();
	}

	public void actionListenerInsertarEstadoCivil() {
		try {
			managerEstadoCivil.insertarEstadoCivil(nombreEstadoCivil);
			listaEstadoCivil = managerEstadoCivil.findAllEstadoCivil();
			estadocivil = new SegEstadoCivil();
			JSFUtil.crearMensajeInfo("Datos de estado civil Registrada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Este estado civil ya se encuentra registrada");
		}
	}

	////
	// eliminar tipo de usuario
	public String eliminarEstadoCivil(Integer id) {
		try {
			managerEstadoCivil.eliminarEstadoCivil(id);
			listaEstadoCivil = managerEstadoCivil.findAllEstadoCivil();
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
		return "registro_tipo_usuario.xhtml";
	}

	//
	public void actionListenerSeleccionarEstadoCivil(SegEstadoCivil estadocivil) {
		estadoCivilSelecionada= estadocivil;
	}

	public void actionListenerActualizarEstadoCivil() {
		try {
			managerEstadoCivil.actualizarEstadoCivil(estadoCivilSelecionada);
			listaEstadoCivil= managerEstadoCivil.findAllEstadoCivil();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	// set y get
	public List<SegEstadoCivil> getListaEstadoCivil() {
		return listaEstadoCivil;
	}

	public void setListaEstadoCivil(List<SegEstadoCivil> listaEstadoCivil) {
		this.listaEstadoCivil = listaEstadoCivil;
	}

	public SegEstadoCivil getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(SegEstadoCivil estadocivil) {
		this.estadocivil = estadocivil;
	}

	public String getNombreEstadoCivil() {
		return nombreEstadoCivil;
	}

	public void setNombreEstadoCivil(String nombreEstadoCivil) {
		this.nombreEstadoCivil = nombreEstadoCivil;
	}

	public SegEstadoCivil getEstadoCivilSelecionada() {
		return estadoCivilSelecionada;
	}

	public void setEstadoCivilSelecionada(SegEstadoCivil estadoCivilSelecionada) {
		this.estadoCivilSelecionada = estadoCivilSelecionada;
	}
	

}
