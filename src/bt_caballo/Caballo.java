package bt_caballo;

public class Caballo {
	private static final int NMOVIMIENTOS = 8;
	private static final int[][] listaMovimientos = { { -2, -1 }, { -1, -2 }, { 1, -2 }, { 2, -1 }, { -2, 1 },
			{ -1, 2 }, { 1, 2 }, { 2, 1 } };

	private int filas;
	private int columnas;
	private boolean[][] tablero;

	public Caballo(int fila, int columna) {
		filas = fila;
		columnas = columna;
		tablero = new boolean[fila][columna];
	}

	public int[][] solve(int fila, int columna, String opcion) {
		int[][] pasos = new int[filas][columnas];
		boolean resultado = false;
		if (opcion.equals("abierto")) {
			resultado = solveAux(fila, columna, 0, pasos);
		}
		if (opcion.equals("cerrado")) {
			resultado = solveAux2(fila, columna, 0, pasos);
		}
		if (resultado)
			return pasos;
		else
			return null;
	}

	private boolean solveAux(int fila, int columna, int iteracion, int[][] pasos) {
		tablero[fila][columna] = true;

		// SE HAN RECORRIDO TODAS LAS CASILLAS
		if (iteracion == filas * columnas - 1) {
			return true;
		} else {
			for (int i = 0; i < NMOVIMIENTOS; i++) {
				int nuevaFila = fila + listaMovimientos[i][0];
				int nuevaColumna = columna + listaMovimientos[i][1];

				if (nuevaFila < filas && nuevaFila >= 0 && nuevaColumna < columnas && nuevaColumna >= 0
						&& !tablero[nuevaFila][nuevaColumna]) {
					pasos[nuevaFila][nuevaColumna] = iteracion + 1;
					boolean b = solveAux(nuevaFila, nuevaColumna, iteracion + 1, pasos);
					if (b)
						return true;
				}
			}
			tablero[fila][columna] = false;
			return false;
		}
	}

	private boolean solveAux2(int fila, int columna, int iteracion, int[][] pasos) {
		tablero[fila][columna] = true;

		// SE HAN RECORRIDO TODAS LAS CASILLAS
		if (iteracion == filas * columnas - 1) {
			for (int i = 0; i < NMOVIMIENTOS; i++) {
				int nuevaFila = fila + listaMovimientos[i][0];
				int nuevaColumna = columna + listaMovimientos[i][1];

				if (nuevaFila == 0 && nuevaColumna == 0) {
					pasos[0][0] = iteracion + 1;
					return true;
				}
			}
		} else {
			for (int i = 0; i < NMOVIMIENTOS; i++) {
				int nuevaFila = fila + listaMovimientos[i][0];
				int nuevaColumna = columna + listaMovimientos[i][1];

				if (nuevaFila < filas && nuevaFila >= 0 && nuevaColumna < columnas && nuevaColumna >= 0
						&& !tablero[nuevaFila][nuevaColumna]) {
					pasos[nuevaFila][nuevaColumna] = iteracion + 1;
					boolean b = solveAux2(nuevaFila, nuevaColumna, iteracion + 1, pasos);
					if (b)
						return true;
				}
			}
		}
		tablero[fila][columna] = false;
		return false;
	}
}
