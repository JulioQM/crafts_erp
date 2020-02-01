package craftserp.model.manager;

import java.util.List; 

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import craftserp.model.entities.SegCiudad;
import craftserp.model.entities.SegCompania;

/**
 * Session Bean implementation class ManagerCompania
 */
@Stateless
@LocalBean
public class ManagerCompania {

    @PersistenceContext
	private EntityManager em;
    public ManagerCompania() {
        // TODO Auto-generated constructor stub
    }

    public List<SegCompania> findAllCompania(){
    	String consulta="select o from SegCompania o";
    	Query q=em.createQuery(consulta,SegCompania.class);
    	return q.getResultList();
    	
    }
    
    public SegCompania findCompaniaById(Integer id) {
    	return em.find(SegCompania.class, id);
    	
    }
    
    public void insertarCompania(SegCompania compania, Integer id) {
    	SegCiudad ciudad = (SegCiudad)em.find(SegCiudad.class, id);
    	compania.setSegCiudad(ciudad);
    	em.persist(compania);
    }
    
    public void eliminarCompania(Integer id) {
		SegCompania compania = findCompaniaById(id);
		if(compania != null) {
			em.remove(compania);
		}
	}
    
    public void ActualizarCompania(SegCompania compania) throws Exception{
    	SegCompania com = findCompaniaById(compania.getIdCompania());
    	if (com==null) {
			throw new Exception("No existe la ia ccompania con el id indicado");
		}
    	com.setIdCompania(compania.getIdCompania());
    	com.setNombre(compania.getNombre());
    	com.setDireccion(compania.getDireccion());
    	com.setMail(compania.getMail());
    	com.setTelefono(compania.getTelefono());
    	com.setIdRuc(compania.getIdRuc());
    	com.setSegCiudad(compania.getSegCiudad());
    	em.merge(com);
    }
    

}
