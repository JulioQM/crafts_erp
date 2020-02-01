package craftserp.model.manager;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import craftserp.model.entities.ProProducto;
import craftserp.model.entities.PrvProveedor;
import craftserp.model.entities.ProCategoria;
import craftserp.model.entities.SegCiudad;

/**
 * Session Bean implementation class ManagerProducto
 */
@Stateless
@LocalBean
public class ManagerProducto {

	@PersistenceContext
	private EntityManager em;

	public ManagerProducto() {
		// TODO Auto-generated constructor stub
	}

    
    public List<ProProducto> findAllProducto() {
		String consulta = "select o from ProProducto o";
		Query q = em.createQuery(consulta, ProProducto.class);
		return q.getResultList();
	}

    public void insertarProducto(ProProducto producto, Integer idcategoria, Integer idprov) {
    	Date date = new Date();
    	ProCategoria categoria = (ProCategoria)em.find(ProCategoria.class, idcategoria);
       	producto.setProCategoria(categoria);
    	PrvProveedor proveedor = (PrvProveedor)em.find(PrvProveedor.class, idprov);
    	producto.setPrvProveedor(proveedor);
    	producto.setFechaRegistro(new Timestamp(date.getTime()));
       	producto.setFechaActualizacion(new Timestamp(date.getTime()));
    	em.persist(producto);
    }
    
	public ProProducto findProductoById(Integer id) {
		return em.find(ProProducto.class, id);
	}

	public void eliminarProducto(Integer id) {
		ProProducto producto = findProductoById(id);
		if (producto!=null) {
			em.remove(producto);
		}
    }
	
	public void ActualizarPrducto(ProProducto producto) throws Exception{
		ProProducto pro = findProductoById(producto.getIdProducto());
		if (pro==null) {
			throw new Exception("No existe el producto con el id especificado");
		}
		Date date = new Date();
		pro.setCodigo(producto.getCodigo());
		pro.setNombre(producto.getNombre());
		pro.setDescripcion(producto.getDescripcion());
		pro.setCosto(producto.getCosto());
		pro.setPrecioSinIva(producto.getPrecioSinIva());
		pro.setPrecioFinal(producto.getPrecioFinal());
		pro.setFechaActualizacion(new Timestamp(date.getTime()));
		pro.setStockMinimo(producto.getStockMinimo());
		pro.setStockTotal(producto.getStockTotal());
		pro.setImagen(producto.getImagen());
		pro.setProCategoria(producto.getProCategoria());
		pro.setPrvProveedor(producto.getPrvProveedor());
    	em.merge(pro);
    }
	/*
	public void insertarProducto(ProProducto producto) throws Exception {
		ProProducto pro = new ProProducto();
		pro.setIdProducto(producto.getIdProducto());
		pro.setCodigo(producto.getCodigo());
		pro.setNombre(producto.getNombre());
		pro.setDescripcion(producto.getDescripcion());
		pro.setCosto(producto.getCosto());
		pro.setPrecioSinIva(producto.getPrecioSinIva());
		pro.setPrecioFinal(producto.getPrecioFinal());
		pro.setFechaRegistro(producto.getFechaRegistro());
		pro.setFechaActualizacion(producto.getFechaActualizacion());
		pro.setStockMinimo(producto.getStockMinimo());
		pro.setStockTotal(producto.getStockTotal());
		pro.setImagen(producto.getImagen());
		em.persist(pro);
	}
*/
	public int obtenerExistencia(Integer codigoProducto) throws Exception {
		ProProducto p;
		p = findProductoById(codigoProducto);
		// ves de id existencia idproducto
		return p.getIdProducto().intValue();
	}

//	/**
//	 * Metodo finder para consulta de productos. Hace uso del componente
//	 * {@link marketdemo.model.manager.ManagerDAO ManagerDAO} de la capa model.
//	 * 
//	 * @return listado de Productos ordenados por nombre.
//	 */
//	@SuppressWarnings("unchecked")
//	public List<ProProducto> findAllProductos() {
//		return managerDAO.findAll(ProProducto.class, "nombre");
//	}
//
//	/**
//	 * Metodo finder para consulta de productos. Hace uso del componente
//	 * {@link marketdemo.model.manager.ManagerDAO ManagerDAO} de la capa model.
//	 * 
//	 * @param codigoProducto codigo del producto que se desea buscar.
//	 * @return el producto encontrado.
//	 * @throws Exception
//	 */
//	public ProProducto findProductoById(Integer codigoProducto) throws Exception {
//		return (ProProducto) managerDAO.findById(ProProducto.class, codigoProducto);
//	}
//
//	/// codigo de la categoria
//	public ProCategoria findCategoria(Integer codigoProducto) throws Exception {
//		return (ProCategoria) managerDAO.findById(ProCategoria.class, codigoProducto);
//	}
//
//	/**
//	 * Guarda un nuevo producto en la base de datos. Hace uso del componente
//	 * {@link marketdemo.model.manager.ManagerDAO ManagerDAO} de la capa model.
//	 * 
//	 * @param p El nuevo producto.
//	 * @throws Exception
//	 */
//	public void insertarProducto(ProProducto p) throws Exception {
//		managerDAO.insertar(p);
//	}
//
//	/**
//	 * Borra de la base de datos un producto especifico. Hace uso del componente
//	 * {@link marketdemo.model.manager.ManagerDAO ManagerDAO} de la capa model.
//	 * 
//	 * @param codigoProducto el codigo del producto que se desea eliminar.
//	 * @throws Exception
//	 */
//	public void eliminarProducto(Integer codigoProducto) throws Exception {
//		managerDAO.eliminar(ProProducto.class, codigoProducto);
//	}
//
//	/**
//	 * Actualiza la informacion de un producto en la base de datos. Hace uso del
//	 * componente {@link marketdemo.model.manager.ManagerDAO ManagerDAO} de la capa
//	 * model.
//	 * 
//	 * @param producto Los datos del producto que se desea actualizar.
//	 * @throws Exception
//	 */
//	public void actualizarProducto(ProProducto producto) throws Exception {
//		ProProducto pro = null;
//		try {
//			// buscamos el producto a modificar desde la bdd:
//			pro = findProductoById(producto.getIdProducto());
//			// actualizamos las propiedades:
////  			p.setDescripcion(producto.getDescripcion());
////  			p.setExistencia(producto.getExistencia());
////  			p.setNombre(producto.getNombre());
////  			p.setPrecioUnitario(producto.getPrecioUnitario());
////  			p.setTieneImpuesto(producto.getTieneImpuesto());
//			Date date = new Date();
//			pro.setCodigo(producto.getCodigo());
//			pro.setNombre(producto.getNombre());
//			pro.setDescripcion(producto.getDescripcion());
//			pro.setCosto(producto.getCosto());
//			pro.setPrecioSinIva(producto.getPrecioSinIva());
//			pro.setPrecioFinal(producto.getPrecioFinal());
//			pro.setFechaActualizacion(new Timestamp(date.getTime()));
//			pro.setStockMinimo(producto.getStockMinimo());
//			pro.setStockTotal(producto.getStockTotal());
//			pro.setImagen(producto.getImagen());
//			pro.setProCategoria(producto.getProCategoria());
//			pro.setPrvProveedor(producto.getPrvProveedor());
//			// em.merge(pro);
//			// actualizamos:
//			managerDAO.actualizar(pro);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new Exception(e.getMessage());
//		}
//	}

}
