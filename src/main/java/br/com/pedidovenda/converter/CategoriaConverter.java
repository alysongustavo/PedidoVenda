package br.com.pedidovenda.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.convert.ClientConverter;

import br.com.pedidovenda.model.Categoria;
import br.com.pedidovenda.repository.Categorias;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter, ClientConverter {
	
	private static final String CATEGORIA_ID = "com.algaworks.Categoria";
	
	@Inject
	private Categorias categorias;
	
	/*public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(Categorias.class);
	}*/

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			
			retorno = categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Categoria) value).getId().toString();
		}
		
		return "";
	}

	// Validando client-side
	@Override
	public String getConverterId() {
		return CATEGORIA_ID;
	}

	// Validando client-side
	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

}
