package craftserp.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import craftserp.model.manager.ManagerAuditoria;
import craftserp.model.manager.ManagerSeguridad;
import craftserp.model.util.ModelUtil;
import craftserp.molde.dto.LoginDTO;

@Named
//@javax.enterprise.context.SessionScoped
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigoUsuario;
	private int idusuario;
	private String clave;
	private String tipoUsuario;
	private boolean acceso;
	private LoginDTO loginDTO;
	@EJB
	private ManagerSeguridad managerSeguridad;
	private ManagerAuditoria managerAud;
	// @EJB
	// private ManagerAuditoria managerAuditoria;
	// private LoginDTO loginDTO;

	// voy a comentarle porque le estoy probando el accesorS

//	@PostConstruct
//	public void inicializar() {
//		loginDTO = new LoginDTO();
//	}

	/**
	 * Action que permite el acceso al sistema.
	 * 
	 * @return
	 */
	public String accederSistema() {
		acceso = false;
		try {
			loginDTO = managerSeguridad.accederSistema(codigoUsuario, clave);
			// verificamos el acceso del usuario:
			tipoUsuario = loginDTO.getTipoUsuario();
			// redireccion dependiendo del tipo de usuario:
			// managerAuditoria.crearEvento(codigoUsuario, this.getClass(),
			// "accederSistema", "Acceso a login");
			return loginDTO.getRutaAcceso() + "?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return "";
	}

//	<!-- 
//	<f:metadata>
//	
//	<f:event listener="#{beanLogin.comprobarlogin()}" type="preRenderView"></f:event>
//	
//	</f:metadata> -->	

	/**
	 * Finaliza la sesion web del usuario.
	 * 
	 * @return
	 */
	public String salirSistema() throws Exception {
		System.out.println("salirSistema");
		try {
			// managerAuditoria.crearEvento(loginDTO.getCodigoUsuario(), this.getClass(),
			// "salisSistema", "Cerrar sesion");
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		managerAud.crearEvento(idusuario, this.getClass(), "Salir", "Se acaba de salir");
		return "/landing.html?faces-redirect=true";
	}

	public void actionVerificarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String requestPath = ec.getRequestPathInfo();
		try {
			// si no paso por login:
			if (loginDTO == null || ModelUtil.isEmpty(loginDTO.getRutaAcceso())) {
				ec.redirect(ec.getRequestContextPath() + "/landing.html");
			} else {
				// validar las rutas de acceso:
				if (requestPath.contains("/Administrador") && loginDTO.getRutaAcceso().startsWith("/Administrador"))
					return;
				if (requestPath.contains("/Cliente") && loginDTO.getRutaAcceso().startsWith("/Cliente"))
					return;
				// caso contrario significa que hizo login pero intenta acceder a ruta no
				// permitida:
				ec.redirect(ec.getRequestContextPath() + "/landing.html");

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
