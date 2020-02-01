package craftserp.model.manager;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import craftserp.model.entities.PrvProveedor;
import craftserp.model.entities.SegCiudad;

/**
 * Session Bean implementation class ManagerProveedor
 */
@Stateless
@LocalBean
public class ManagerProveedor {

	@PersistenceContext
	private EntityManager em;
    public ManagerProveedor() {
        
    }
    
    public List<PrvProveedor> findAllProveedores(){
    	String consulta="select o from PrvProveedor o";
    	Query q=em.createQuery(consulta,PrvProveedor.class);
    	return q.getResultList();
    	
    }
    
   /* public PrvProveedor findProveedorByRuc(String ruc) {
    	
    	return em.find(PrvProveedor.class, ruc);
    	
    }*/
   /* public void insertarProveedor(PrvProveedor proveedor)throws Exception{
    	if(findProveedorByRuc(proveedor.getIdRuc())!=null)
    		throw new Exception("Ya existe un ruc indicado");
    	em.persist(proveedor);
    
    }
    	
    }*/
    
    public void insertarProveedor(PrvProveedor proveedor) throws Exception{
    	if(findProveedorById(proveedor.getIdProveedor())!=null)
    		throw new Exception("Ya existe un id idicado");
    	em.persist(proveedor);
    	
    	
    }
    public void insertarPro(PrvProveedor proveedor,Integer idciudad) {    	
    	SegCiudad ciudad=(SegCiudad)em.find(SegCiudad.class, idciudad);    	
    	proveedor.setSegCiudad(ciudad);
    	em.persist(proveedor);	
    }
    
    public PrvProveedor findProveedorById(Integer id) {
    	return em.find(PrvProveedor.class, id);
    	
    }
    public void eliminarProveedor(Integer id) {
    	PrvProveedor proveedor=findProveedorById(id);
    	if(proveedor!=null)
    		em.remove(proveedor);
    	
    }
    public void ActualizarProveedor(PrvProveedor provedor)throws Exception{
    	PrvProveedor p= findProveedorById(provedor.getIdProveedor());
    	if(p==null)
    		throw new Exception("No existe el proveedor con el id especificado");
    	p.setIdProveedor(provedor.getIdProveedor());
    	p.setIdRuc(provedor.getIdRuc());
    	p.setNombre(provedor.getNombre());
    	p.setApellido(provedor.getApellido());
    	p.setNombreEmpresa(provedor.getNombreEmpresa());
    	p.setTelefono(provedor.getTelefono());
    	p.setMail(provedor.getMail());
    	p.setDireccion(provedor.getDireccion());
    	p.setSegCiudad(provedor.getSegCiudad());
    	em.merge(p);
    	
    }
   /*public Estudiante findEstudianteByCedula(String cedula) {
    	return em.find(Estudiante.class,cedula);
    }
    
    public void eliminarEstudiante(String cedula) {
    	Estudiante estudiante=findEstudianteByCedula(cedula);
    	if(estudiante!=null)
    		em.remove(estudiante);
    }
    public void actualizarEstudiante(Estudiante estudiante) throws Exception{
    	Estudiante e=findEstudianteByCedula(estudiante.getCedula());
    	if(e==null)
    		throw new Exception("No existe el estudiante con la cedula especificada");
    	e.setApellidos(estudiante.getApellidos());
    	e.setEdad(estudiante.getEdad());
    	e.setNombres(estudiante.getNombres());
    	em.merge(e);
    }*/
 

}
