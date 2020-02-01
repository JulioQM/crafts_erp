package craftserp.model.manager;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import craftserp.model.entities.SegRole;


/**
 * Session Bean implementation class ManagerTipoUsuario
 */
@Stateless
@LocalBean
public class ManagerRol {
	@PersistenceContext
	private EntityManager em;

	public ManagerRol() {
		// TODO Auto-generated constructor stub
	}

	// me sirve para consultar el tipo de usuario
	public List<SegRole> findAllRol() {
		String consulta = "SELECT s FROM SegRole s";
		Query q = em.createQuery(consulta, SegRole.class);
		return q.getResultList();
	}

	public SegRole findRolByNombre(String nombre) {
		return em.find(SegRole.class, nombre);
	}

	public void insertarRol(String nombre){
		SegRole rol = new SegRole();
		//if (findTipoUsuarioByNombre(tipousuario.getNombre()) == null) 
		 //throw new Exception("Ya existe la ciudad indicada");			
		rol.setNombre(nombre);
		
		em.persist(rol);
		
	}

	// metodo para buscar por el id
	public SegRole buscarRol(Integer id) {
		SegRole u = new SegRole();
		u = em.find(SegRole.class, id);
		return u;

	}
//metodo para eliminar
	public void eliminarRol(Integer id) {
		SegRole u = new SegRole();
		u = em.find(SegRole.class, id);
		em.remove(u);
	}

	// actualizar
	public void actualizarCiudad(SegRole usuario) throws Exception {
		SegRole u = buscarRol(usuario.getIdRol());
		if (u == null)
			throw new Exception("No existe la ciudad con el nombre especifica");
		u.setNombre(usuario.getNombre());
		em.merge(u);
	}
}
