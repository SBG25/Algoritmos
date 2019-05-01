package bt_laberinto;

import java.util.Scanner;

public class Laberinto {
	protected static final int[][] MOVIMIENTOS = {{1,0},{-1,0},{0,-1},{0,1}};
	public static final int LIBRE = 0;
	public static final int BLOQUEADA = -1;
	
	public static final int FILA_INICIO = 0;
	public static final int COLUMNA_INICIO = 0;
	
	protected static int mejorSolucion = -1;

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int nCasillas = teclado.nextInt();
		
		int[][] matriz = new int[nCasillas][nCasillas];

		for(int i=0; i<nCasillas; i++) {
			for(int j=0; j<nCasillas; j++) {
				matriz[i][j] = teclado.nextInt();
			}
		}
		
		resolver(0,0,matriz,1);
		if(mejorSolucion == -1) System.out.println("SIN SOLUCION");
		else System.out.println(mejorSolucion);
		
		teclado.close();
	}
	
	public static void resolver(int fila, int columna, int[][] matriz, int nCasilla) {		
		matriz[fila][columna] = BLOQUEADA;
		//CASO BASE
		if(fila == matriz.length-1 && columna == matriz.length-1) {
			if(nCasilla < mejorSolucion || mejorSolucion == -1) mejorSolucion = nCasilla;
		}
		
		//CASO RECURSIVO
		else {
			for(int[] mov : MOVIMIENTOS) {
				int sigFila = fila + mov[0];
				int sigColumna = columna + mov[1];
				
				if(esValido(sigFila, sigColumna, matriz)) {
					resolver(sigFila, sigColumna, matriz, nCasilla+1);
				}
			}
		}
		matriz[fila][columna] = LIBRE;
	}
	
	public static boolean esValido(int fila, int columna, int[][] tablero) {
		return (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length && tablero[fila][columna] == LIBRE);
	}

}
