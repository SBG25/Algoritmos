package bt_colores;

import java.util.Scanner;

public class Colores {
	//SE QUIERE COLOREAR UN GRAFO MINIMIZANDO LOS COLORES UTILIZADOS DE TAL FORMA QUE DOS NODOS ADYACENTES NO PUEDAN TENER EL MISMO COLOR.
	// LA ENTRADA AL PROGRAMA SERÁ DE LA FORMA:
	// nNodos
	// nCaminos
	// nodo1 nodo2
	// ...
	// nodoN nodoN

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int nNodos = reader.nextInt();
		int nAristas = reader.nextInt();
		
		boolean[][] matriz = new boolean[nNodos][nNodos];
		
		for(int i=0; i<nAristas; i++) {
			int x = reader.nextInt();
			int y = reader.nextInt();
			
			matriz[x][y] = true;
			matriz[y][x] = true;
		}
		
		int[] res = solve(matriz, 0);
		
		for(int i=0; i<res.length; i++) {
			System.out.println("NODO "+i + " -> COLOR "+res[i]);
		}
		
		reader.close();

	}
	
	public static int[] solve(boolean[][] matriz, int nodoActual) {
		int[] colores = new int[matriz.length];
		solveAux(matriz, nodoActual, colores);
		return colores;
	}
	
	public static boolean solveAux(boolean[][] matriz, int nodoActual, int[] colores) {
		//CASO BASE
		if(nodoActual == colores.length) {
			return true;
		}
		//CASO RECURSIVO
		else {
			for(int i=1; i<=colores.length; i++) {
				if(isFeasible(matriz, nodoActual, colores, i)) {
					colores[nodoActual] = i;
					if(solveAux(matriz,nodoActual+1,colores)) return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isFeasible(boolean[][] matriz, int nodoActual, int[] colores, int i) {
		
		for(int j=0; j<matriz.length; j++) {
			if(matriz[nodoActual][j] && colores[j] == i) return false;
		}
		return true;
	}
	
	

}
