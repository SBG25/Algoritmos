package bt_casisudoku;

import java.util.Scanner;

public class CasiSudoku {
	private static boolean haySolucion;
	private static int[][] solucion = new int[9][9];

	public static void main(String[] args) {
		int[][] matriz = new int [9][9];
		Scanner teclado = new Scanner(System.in);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				matriz[i][j] = teclado.nextInt();
			}
		}
		
		boolean resultado = resolver(matriz, 0, 0);
		if(haySolucion) {
			if(resultado) System.out.println("casi sudoku");
			else {
				for(int i=0; i<9; i++) {
					for(int j=0; j<9; j++) {
						System.out.print(solucion[i][j] + " ");
					}
					System.out.println("");
				}
			}
		}
		else {
			System.out.println("imposible");
		}
		
		teclado.close();

	}
	
	public static boolean resolver(int[][] matriz, int fila, int columna) {
		if(fila == 0 && columna == 9) {
			if(solucion[0][0] != 0) {
				return true;
			}
			else {
				haySolucion = true;
				for(int i=0; i<9; i++) {
					for(int j=0; j<9; j++) {
						solucion[i][j] = matriz[i][j];
					}
				}
				return false;
			}
		}
		else {
			int[] siguiente = siguienteCoord(fila, columna);
			if(matriz[fila][columna] != 0) {
				return resolver(matriz, siguiente[0], siguiente[1]);
			}
			else {
				for(int i=1; i<=9;i++) {
					if(esValido(matriz, fila, columna, i)) {
						matriz[fila][columna] = i;
						if(resolver(matriz, siguiente[0], siguiente[1])) return true;
					}
				}
				matriz[fila][columna] = 0;
				return false;
			}
		}
	}
	
	private static int[] siguienteCoord(int fila, int columna) {
		if(fila+1 == 9) {
			return new int[] {0, columna+1};
		}
		else {
			return new int[] {fila+1, columna};
		}
	}
	
	private static boolean esValido(int[][] matriz, int fila, int columna, int buscado) {
		return cumpleColumna(columna, matriz, buscado) && cumpleFila(fila, matriz, buscado) && checkSquare(matriz, fila, columna, buscado);
	}
	
	private static boolean cumpleColumna(int columna, int[][] matriz, int buscado) {
		for(int i=0; i<9; i++) {
			if(matriz[i][columna] == buscado) return false;
		}
		return true;
	}
	
	private static boolean cumpleFila(int fila, int[][] matriz, int buscado) {
		for(int i=0; i<9; i++) {
			if(matriz[fila][i] == buscado) return false;
		}
		return true;
	}
	
	private static boolean checkSquare(int[][] matriz, int fila, int columna, int buscado) {
		int rowIni = (fila / 3) * 3;
		int columnIni = (columna / 3) * 3;

		for (int i = rowIni; i < rowIni + 3; i++) {
			for (int j = columnIni; j < columnIni + 3; j++) {
				if (matriz[i][j] == buscado)
					return false;
			}
		}
		return true;
	}

}
