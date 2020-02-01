package craftserp.model.manager;

import java.util.List; 

import javax.ejb.LocalBean; 
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import craftserp.model.entities.ProCatalogo;
import craftserp.model.entities.ProCategoria;


@Stateless
@LocalBean
public class ManagerProcategoria {
	
	@PersistenceContext
    private EntityManager em;
    public ManagerProcategoria() {
     
    }
    
    public List<ProCategoria> findAllCategoriaPro(){
    	String consulta = "select o from ProCategoria o";
		Query q = em.createQuery(consulta, ProCategoria.class);
		return q.getResultList(); 
    }
    
	/*public ProCategoria findCategoriaProByNombre(String nombre){
		return em.find(ProCategoria.class, nombre);
	}*/
	
	public ProCategoria findCategoriaProById(Integer id) {
		return em.find(ProCategoria.class, id);
	}
	
	public void InsertarCategoriaPro(ProCategoria categoria) {
		ProCategoria pro = new ProCategoria();
		pro.setIdCategoria(categoria.getIdCategoria());
		pro.setNombre(categoria.getNombre());
		pro.setDescripcion(categoria.getDescripcion());
		pro.setImagen(categoria.getImagen());
		em.persist(pro);
	}

	public void eliminarCategoriaPro(Integer id) {
		ProCategoria procategoria = findCategoriaProById(id);
		if(procategoria != null) {
			em.remove(procategoria);
		}
	}
	
	public void ActualizarCategoriaPro(ProCategoria categoriapro) throws Exception {
		ProCategoria pro = findCategoriaProById(categoriapro.getIdCategoria());
		if (pro == null)
			throw new Exception("No existe la categoría del producto con el id especificado");
		pro.setIdCategoria(categoriapro.getIdCategoria());
		pro.setNombre(categoriapro.getNombre());
		pro.setDescripcion(categoriapro.getDescripcion());
		pro.setImagen(categoriapro.getImagen());

		em.merge(pro);

	}

}
