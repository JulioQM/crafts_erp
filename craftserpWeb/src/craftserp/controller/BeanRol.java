package craftserp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.SegRole;
import craftserp.model.manager.ManagerRol;

@Named
@SessionScoped
public class BeanRol implements Serializable {
	private String nombreRol;

	@EJB
	private ManagerRol managerRol;
	private List<SegRole> listaRol;
	private SegRole rol;
	private int id;
	private SegRole rolSelecionado;

	@PostConstruct
	public void inicializar() {
		listaRol = managerRol.findAllRol();
		rol = new SegRole();
		rolSelecionado = new SegRole();
	}

	public void actionListenerInsertarRol() {
		try {
			managerRol.insertarRol(nombreRol);
			listaRol = managerRol.findAllRol();
			rol = new SegRole();
			JSFUtil.crearMensajeInfo("Datos de tipo de usuario registrada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError("Este tipo de usuario ya se encuentra registrada");
		}
	}

	// eliminar tipo de usuario
	public String eliminarRol(Integer id) {
		try {
			System.out.println("id: " + id);
			managerRol.eliminarRol(id);
			listaRol = managerRol.findAllRol();
			System.out.println("eliminado");

		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
		return "registro_tipo_usuario.xhtml";
	}

	//
	public void actionListenerSeleccionarRol(SegRole rol) {
		rolSelecionado = rol;
	}

	public void actionListenerActualizarRol() {
		try {
			managerRol.actualizarCiudad(rolSelecionado);
			listaRol = managerRol.findAllRol();
			JSFUtil.crearMensajeInfo("Datos actualizados.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	// get y set

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<SegRole> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<SegRole> listaRol) {
		this.listaRol = listaRol;
	}

	public SegRole getRol() {
		return rol;
	}

	public void setRol(SegRole rol) {
		this.rol = rol;
	}

	public SegRole getRolSelecionado() {
		return rolSelecionado;
	}

	public void setRolSelecionado(SegRole rolSelecionado) {
		this.rolSelecionado = rolSelecionado;
	}

}
