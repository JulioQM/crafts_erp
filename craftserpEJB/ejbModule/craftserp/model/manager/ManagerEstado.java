package craftserp.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import craftserp.model.entities.PrdEstado;
import craftserp.model.entities.PrvProveedor;


/**
 * Session Bean implementation class ManagerEstado
 */
@Stateless
@LocalBean
public class ManagerEstado {

	@PersistenceContext
	private EntityManager em;

	public ManagerEstado() {
		// TODO Auto-generated constructor stub
	}

	public List<PrdEstado> findAllEstados() {
		String consulta = "select o from PrdEstado o";
		Query q = em.createQuery(consulta, PrdEstado.class);
		return q.getResultList();

	}

	public PrdEstado findEstadoById(Integer id) {
		return em.find(PrdEstado.class, id);
	}

	public void eliminarEstado(Integer id) {
		PrdEstado estado = findEstadoById(id);
		if (estado != null)
			em.remove(estado);

	}

	public void actualizarEstado(PrdEstado id) throws Exception {
		PrdEstado e = findEstadoById(id.getIdEstado());
		if (e == null)
			throw new Exception("No existe el estado con el id especificada");
		e.setIdEstado(id.getIdEstado());
		e.setNombre(id.getNombre());
		em.merge(e);

	}

	/*public void InsertarEstado(PrdEstado estado) throws Exception {
		if (findEstadoById(estado.getIdEstado()) != null)
			throw new Exception("Ya existe un estado indicado");
		em.persist(estado);
	}*/
	
	public void insertarEstado(PrdEstado estado) {
		PrdEstado e= new PrdEstado();
		e.setNombre(estado.getNombre());
		em.persist(e);
		
	}
	
	
	


	/*
	 * public void insertarProveedor(PrvProveedor proveedor)throws Exception{
	 * if(findProveedorByRuc(proveedor.getIdRuc())!=null) throw new
	 * Exception("Ya existe un ruc indicado"); em.persist(proveedor);
	 * 
	 * }
	 * 
	 * }
	 */
	/*
	 * public List<PrvProveedor> findAllProveedores(){ String
	 * consulta="select o from PrvProveedor o"; Query
	 * q=em.createQuery(consulta,PrvProveedor.class); return q.getResultList();
	 * 
	 * } public Estudiante findEstudianteByCedula(String cedula) { return
	 * em.find(Estudiante.class,cedula); }
	 * 
	 * public void eliminarEstudiante(String cedula) { Estudiante
	 * estudiante=findEstudianteByCedula(cedula); if(estudiante!=null)
	 * em.remove(estudiante); } public void actualizarEstudiante(Estudiante
	 * estudiante) throws Exception{ Estudiante
	 * e=findEstudianteByCedula(estudiante.getCedula()); if(e==null) throw new
	 * Exception("No existe el estudiante con la cedula especificada");
	 * e.setApellidos(estudiante.getApellidos()); e.setEdad(estudiante.getEdad());
	 * e.setNombres(estudiante.getNombres()); em.merge(e); }
	 */

}
