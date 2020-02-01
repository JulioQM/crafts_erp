package craftserp.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import craftserp.model.entities.SegCiudad;
import craftserp.model.entities.SegEstadoCivil;
import craftserp.model.entities.SegPersona;
import craftserp.model.entities.SegRole;

/**
 * Session Bean implementation class ManagerPersona
 */
@Stateless
@LocalBean
public class ManagerPersona {
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerPersona() {
		// TODO Auto-generated constructor stub
	}

	// me sirve para consultar las las personas
	public List<SegPersona> findAllPersona() {
		String consulta = "SELECT s FROM SegPersona s";
		Query q = em.createQuery(consulta, SegPersona.class);
		return q.getResultList();
	}

	// me sirve para buscar la cedula
	public SegPersona findPersonaByCedula(String cedula) {
		return em.find(SegPersona.class, cedula);
	}

	// me sirve para registrar las personas

	public void insertarPersona(SegPersona persona, int idciudad, int idestadocivil, int idrol) {
		SegRole rol = (SegRole) em.find(SegRole.class, idrol);
		SegCiudad ciudad = (SegCiudad) em.find(SegCiudad.class, idciudad);
		SegEstadoCivil estadocivil = (SegEstadoCivil) em.find(SegEstadoCivil.class, idestadocivil);
		Date date = new Date();
		persona.setSegRole(rol);
		persona.setSegCiudad(ciudad);
		persona.setSegEstadoCivil(estadocivil);
		persona.setFechaRegistro(new Timestamp(date.getTime()));
		em.persist(persona);
	}

	public List<SegRole> findAllRol() {
		String consulta = "SELECT s FROM SegAsignacionRole s";
		Query q = em.createQuery(consulta, SegRole.class);
		return q.getResultList();
	}

	// metodo para eliminar la persona
	public void eliminarPersona(String cedula) {
		SegPersona persona = findPersonaByCedula(cedula);
		if (persona != null)
			em.remove(persona);
	}

	// metodo para actualizar la persona
	public void actualizarPersona(SegPersona p, int idciudad, int idestadocivil,int idrol) throws Exception {
		SegCiudad ciudad = (SegCiudad) em.find(SegCiudad.class, idciudad);
		SegEstadoCivil estadocivil = (SegEstadoCivil) em.find(SegEstadoCivil.class, idestadocivil);
		SegRole rol=(SegRole) em.find(SegRole.class, idrol);
		SegPersona persona = findPersonaByCedula(p.getIdCedula());
		if (persona == null)
			throw new Exception("No existe la persona con el nombre especifica");
		Date date = new Date();
		persona.setNombre(p.getNombre());
		persona.setApellido(p.getApellido());
		persona.setTelefono(p.getTelefono());
		persona.setMail(p.getMail());
		persona.setDireccion(p.getDireccion());
		persona.setGenero(p.getGenero());		
		//persona.setFechaRegistro(p.getFechaRegistro());
		persona.setClave(p.getClave());
		persona.setSegCiudad(ciudad);
		persona.setSegEstadoCivil(estadocivil);
		persona.setSegRole(rol);
		em.merge(persona);
	}

}
