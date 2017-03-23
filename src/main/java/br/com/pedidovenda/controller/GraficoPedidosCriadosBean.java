package br.com.pedidovenda.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean {
	
	private LineChartModel model;
	
	public void preRender() {
		model = new LineChartModel();
		model.setTitle("Pedidos criados");
		model.setLegendPosition("e");
		model.setAnimate(true);
		
		model.getAxes().put(AxisType.X, new CategoryAxis());
		
		adicionarSerie("Todos os pedidos");
		adicionarSerie("Meus pedidos");
	}

	private void adicionarSerie(String rotulo) {
		ChartSeries series = new ChartSeries(rotulo);
		
		series.set("1", Math.random() * 1000);
		series.set("2", Math.random() * 1000);
		series.set("3", Math.random() * 1000);
		series.set("4", Math.random() * 1000);
		series.set("5", Math.random() * 1000);
		
		model.addSeries(series);
	}

	public LineChartModel getModel() {
		return model;
	}

}
