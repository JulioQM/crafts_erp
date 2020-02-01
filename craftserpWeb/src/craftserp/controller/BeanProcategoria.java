package craftserp.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import craftserp.model.entities.ProCategoria;
import craftserp.model.manager.ManagerProcategoria;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanProcategoria implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerProcategoria managercategoriapro;
	private List<ProCategoria> listacategoriapro;
	private ProCategoria categoriapro;
	private ProCategoria categoriaproseleccionado;

	@PostConstruct
	public void inicializar() {
		listacategoriapro = managercategoriapro.findAllCategoriaPro();
		categoriapro = new ProCategoria();
	}

	public void actionListenerInsertarCategoriaPro() {
		try {
			managercategoriapro.InsertarCategoriaPro(categoriapro);
			listacategoriapro = managercategoriapro.findAllCategoriaPro();
			categoriapro = new ProCategoria();
			JSFUtil.crearMensajeInfo("Datos de categoría de producto insertados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionEliminarCategoriaPro(Integer id) {
		managercategoriapro.eliminarCategoriaPro(id);
		listacategoriapro = managercategoriapro.findAllCategoriaPro();
		JSFUtil.crearMensajeInfo("Categoría de Producto Eliminado");
	}

	public void actionListenerSeleccionarCategoriaProducto(ProCategoria categoriapro) {
		categoriaproseleccionado = categoriapro;
	}

	public void actionListenerActualizarCategoriaPro() {

		try {
			managercategoriapro.ActualizarCategoriaPro(categoriaproseleccionado);
			listacategoriapro = managercategoriapro.findAllCategoriaPro();
			JSFUtil.crearMensajeInfo("Datos Actualizados");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public List<ProCategoria> getListacategoriapro() {
		return listacategoriapro;
	}

	public void setListacategoriapro(List<ProCategoria> listacategoriapro) {
		this.listacategoriapro = listacategoriapro;
	}

	public ProCategoria getCategoriapro() {
		return categoriapro;
	}

	public void setCategoriapro(ProCategoria categoriapro) {
		this.categoriapro = categoriapro;
	}

	public ProCategoria getCategoriaproseleccionado() {
		return categoriaproseleccionado;
	}

	public void setCategoriaproseleccionado(ProCategoria categoriaproseleccionado) {
		this.categoriaproseleccionado = categoriaproseleccionado;
	}
	
	

}
