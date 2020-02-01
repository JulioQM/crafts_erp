package craftserp.model.manager;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import craftserp.model.entities.SegPersona;
import craftserp.molde.dto.LoginDTO;

/**
 * Session Bean implementation class ManagerSeguridad
 */
@Stateless
@LocalBean
public class ManagerSeguridad {
	@EJB
	private ManagerDAO managerDAO;

	public ManagerSeguridad() {
	}

	/**
	 * Metodo que le permite a un usuario acceder al sistema.
	 * 
	 * @param idCedula Identificador del usuario.
	 * @param clave    Clave de acceso.
	 * @return Retorna el tipo de usuario. Puede tener dos valores: SP (supervisor)
	 *         o VD (vendedor).
	 * @throws Exception Cuando no coincide la clave proporcionada o si ocurrio un
	 *                   error con la consulta a la base de datos.
	 */
//ves de codigoUsuario voy a cambiarle a cedula
	public LoginDTO accederSistema(String idCedula, String clave) throws Exception {
		// SegPersona usuario = (SegPersona) managerDAO.findById(SegPersona.class,
		// idCedula);
		SegPersona usuario = new SegPersona();
		usuario=findUsuarioById(idCedula);
		if (usuario == null)
			throw new Exception("Error en usuario y/o clave.");

		if (!usuario.getClave().contains(clave))
			throw new Exception("Error en usuario y/o clave.");

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setUsuario(usuario.getIdCedula());
		loginDTO.setTipoUsuario(usuario.getSegRole().getNombre());
		loginDTO.setCodigoUsuario(usuario.getClave());

		// dependiendo del tipo de usuario, configuramos la ruta de acceso a las pags
		// web:
		if (usuario.getSegRole().getNombre().equals("Cliente"))
			loginDTO.setRutaAcceso("/prueba/cliente.xhtml");
		else if (usuario.getSegRole().getNombre().equals("Administrador"))
			// loginDTO.setRutaAcceso("/supervisor/index.xhtml");
			loginDTO.setRutaAcceso("/prueba/administrador.xhtml");

		return loginDTO;
	}

	public SegPersona findUsuarioById(String idCedula) throws Exception {
		SegPersona usuario = (SegPersona) managerDAO.findById(SegPersona.class, idCedula);
		return usuario;
	}

	public SegPersona finPersona(String cedula) throws Exception {
		return (SegPersona) managerDAO.findById(SegPersona.class, cedula);
	}

}
