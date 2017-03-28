package br.com.pedidovenda.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.validate.bean.BeanValidationMetadataMapper;

import br.com.pedidovenda.validation.NotBlankClientValidationConstraint;

/**
 * Classe responsável por não deixar atribuir zero aos valores numéricos não inicializados.
 * @author gilsonsilvati
 */
@WebListener
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
		
		/* Configurando validação client-side com anotações terceiras (Vinculando...) */
		BeanValidationMetadataMapper.registerConstraintMapping(NotBlank.class, 
				new NotBlankClientValidationConstraint());
	}

}
