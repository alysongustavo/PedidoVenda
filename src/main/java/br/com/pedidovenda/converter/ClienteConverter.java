package br.com.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.pedidovenda.model.Cliente;
import br.com.pedidovenda.repository.Clientes;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	@Inject
	private Clientes clientes;
	
	/*public ClienteConverter() {
		this.clientes = CDIServiceLocator.getBean(Clientes.class);
	}*/
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			retorno = this.clientes.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Cliente) value).getId().toString();
		}
		
		return "";
	}

}