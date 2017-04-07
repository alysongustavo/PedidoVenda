package br.com.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.repository.Pedidos;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {
	
	@Inject
	private Pedidos pedidos;
	
	/*public PedidoConverter() {
		pedidos = CDIServiceLocator.getBean(Pedidos.class);
	}*/

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			retorno = pedidos.porId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

}
