/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unime.math.functioneasy.util;

import br.com.unime.math.functioneasy.model.Coordinate;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JPanel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Mauricio Barbosa
 */
public class GraphGenerator {
    
    private String graphName;
    private JFreeChart graph;
    private DefaultCategoryDataset data;
    private Map<Integer, List<Coordinate>> coordinatesMap;
    
    public GraphGenerator(String graphName, List<Coordinate> coordinates) {
        this.graphName = graphName;
        coordinatesMap = new HashMap<Integer, List<Coordinate>>();
        coordinatesMap.put(1, coordinates);
    }
    
    public GraphGenerator(String graphName, Map<Integer, List<Coordinate>> coordinatesMap) {
		this.graphName = graphName;
		this.coordinatesMap = coordinatesMap;
	}

	private void generateGraph() {
        
        data = new DefaultCategoryDataset();
        
        for (int i = 0; i < coordinatesMap.size(); i++) {
			List<Coordinate> coordinates = coordinatesMap.get(i);
			for (Coordinate coordinate : coordinates) {
				data.addValue(coordinate.getY(), "Equação " + (i+1), coordinate.getX());
//				data.addValue(0, LINE_X, coordinate.getX()); eixo x
//            data.addValue(coordinate.getY(), LINE_Y, 0); eixo i
			}
		}
        
        graph = ChartFactory.createLineChart(graphName, "X", "Y", data, PlotOrientation.VERTICAL, true, true, false);
    }
    
    public JPanel generateGraphOnPanel() {
        if (graph == null) {
            generateGraph();
        }
        return new ChartPanel(graph);
    }
    
    public ImageIcon generateGraphOnImage(String path, int width, int height) throws IOException {
        if (graph == null) {
            generateGraph();
        }
        ChartUtilities.writeChartAsPNG(new FileOutputStream(path), graph, width, height);
        return new ImageIcon(path);
    }
    
}
