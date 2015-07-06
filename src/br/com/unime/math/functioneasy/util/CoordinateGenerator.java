/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.unime.math.functioneasy.util;

import static br.com.unime.math.functioneasy.util.PropertiesBR.SEPARATOR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.unime.math.functioneasy.model.Coordinate;
import br.com.unime.math.functioneasy.model.Function;
import expr.Parser;
import expr.SyntaxException;

/**
 *
 * @author Mauricio Barbosa
 */
public class CoordinateGenerator {

	private final List<String> irregularExpressions = new ArrayList<String>() {
		{
			add("/0"); // Não é possível dividir por zero
		}
	};

	public Map<Integer, List<Coordinate>> generate(String strFunction, String xMin, String xMax) throws SyntaxException {
		List<Function> functions = splitFunctions(strFunction, xMin, xMax);

		Map<Integer, List<Coordinate>> coordinatesMap = new HashMap<Integer, List<Coordinate>>();

		for (int i = 0; i < functions.size(); i++) {
			List<Coordinate> coordinates = generateCoordinates(functions.get(i));
			coordinatesMap.put(i, coordinates);
		}

		return coordinatesMap;
	}

	private List<Coordinate> generateCoordinates(Function function)
			throws SyntaxException {

		List<Coordinate> coordinates = new ArrayList<Coordinate>();

		for (double x = function.getxMin(); x <= function.getxMax(); x+=0.5) {
			String fX = function.getDescription().replace("x", String.valueOf(x));

			if (validateFunction(fX)) {
				Coordinate coordinate = new Coordinate();
				coordinate.setX(x);
				coordinate.setY(Parser.parse(fX).value());
				coordinates.add(coordinate);
			}
		}

		return coordinates;
	}

	private boolean validateFunction(String fX) {
		for (String expression : irregularExpressions) {
			if (fX.contains(expression)) {
				return false;
			}
		}
		return true;
	}

	private List<Function> splitFunctions(String function, String xMin,
			String xMax) {

		String[] listEquation = function.split(SEPARATOR);
		String[] listXMin = xMin.split(SEPARATOR);
		String[] listXMax = xMax.split(SEPARATOR);

		List<Function> functions = new ArrayList<Function>();

		for (int i = 0; i < listEquation.length; i++) {
			Function fX = new Function();
			fX.setDescription(listEquation[i]);
			fX.setxMin(Double.valueOf(listXMin[i]));
			fX.setxMax(Double.valueOf(listXMax[i]));
			functions.add(fX);
		}

		return functions;
	}

}
