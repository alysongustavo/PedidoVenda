package br.com.pedido.teste;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import br.com.pedidovenda.model.Pedido;
import br.com.pedidovenda.model.Usuario;
import br.com.pedidovenda.model.vo.DataValor;

public class TesteConsulta {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PedidoPU");
		EntityManager manager = factory.createEntityManager();
		Session session = manager.unwrap(Session.class);
		
		// Não precisa ir no banco... ;)
		Usuario usuario = new Usuario();
		usuario.setId(2L);
		
		Map<Date, BigDecimal> valores = valoresTotaisPorData(15, usuario, session);
		
		for (Date data : valores.keySet()) {
			System.out.println(data + " = " + valores.get(data));
		}
		
		manager.close();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Date, BigDecimal> valoresTotaisPorData(Integer numeroDeDias, Usuario criadoPor, Session session) {
		numeroDeDias -= 1;
		
		Calendar dataInicial = Calendar.getInstance(); // Data atual
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH); // Sem informação de horas... ;)
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1); // Subtrai a data, diminuindo pelo numeroDeDias
		
		Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias, dataInicial);
		
		Criteria criteria = session.createCriteria(Pedido.class);
		
		/* SQL + ou - : SELECT DATE(data_criacao) AS data, SUM(valor_total) as valor FROM pedido
		  					WHERE data_criacao >= :dataInicial AND vendedor_id = :criadoPor
		  						GROUP BY DATE(data_criacao) */
		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlGroupProjection("DATE(data_criacao) AS data",
						"DATE(data_criacao)", new String[] { "data" }, 
						new Type[] { StandardBasicTypes.DATE } ))
				.add(Projections.sum("valorTotal").as("valor"))
			)
			.add(Restrictions.ge("dataCriacao", dataInicial.getTime()));
		
		if (criadoPor != null) {
			criteria.add(Restrictions.eq("vendedor", criadoPor));
		}
		
		List<DataValor> valoresPorData = criteria
				.setResultTransformer(Transformers.aliasToBean(DataValor.class))
				.list();
		
		for (DataValor dataValor : valoresPorData) {
			resultado.put(dataValor.getData(), dataValor.getValor());
		}
		
		return resultado;
	}

	private static Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias, Calendar dataInicial) {
		dataInicial = (Calendar) dataInicial.clone(); // Garante que não altera valor do objeto original
		
		Map<Date, BigDecimal> mapaInicial = new TreeMap<>();
		
		for (int i = 0; i <= numeroDeDias; i++) {
			mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return mapaInicial;
	}

}
