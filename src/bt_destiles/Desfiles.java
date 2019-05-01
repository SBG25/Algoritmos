package bt_destiles;

import java.util.Scanner;

public class Desfiles {
	//TRUE SI EL GRAFO CONTIENE ALGÚN CICLO, FALSE EN CASO CONTRARIO.

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		int nNodos = reader.nextInt();
		int nAristas = reader.nextInt();
		
		boolean[][] matriz = new boolean[nNodos][nNodos];
		
		for(int i=0; i<nAristas; i++) {
			int x = reader.nextInt() -1;
			int y = reader.nextInt() -1;
			
			matriz[x][y] = true;
			matriz[y][x] = true;
		}
		
		boolean resultado = false;
		for(int i=0; i<nAristas; i++) {
			if(carroza(matriz, new boolean[nNodos], i, i, i)) {
				resultado = true;
				break;
			}
		}
		
		System.out.println(resultado);
		reader.close();
	}
	
	public static boolean carroza(boolean[][] matriz, boolean[] visitados, int nodoActual, int nodoAnterior, int nodoInicio) {
		visitados[nodoActual] = true;
		
		if(matriz[nodoActual][nodoInicio] && nodoAnterior != nodoInicio) {
			return true;
		}
		
		for(int i=0; i<matriz.length; i++) {
			if(matriz[nodoActual][i] && !visitados[i] && carroza(matriz, visitados, i, nodoActual, nodoInicio)) {
				return true;
			}
		}
		return false;
	}
}
