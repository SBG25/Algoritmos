package voraz_connectedstages;

import java.util.Scanner;

public class ConnectedStages {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		int nNodos = reader.nextInt();
		int nAristas = reader.nextInt();
		
		boolean[][] matriz = new boolean[nNodos][nNodos];
		
		for(int i=0; i<nAristas; i++){
			int x = reader.nextInt();
			int y = reader.nextInt();
			
			matriz[x][y] = true;
			matriz[y][x] = true;
		}
		
		int numeroIslas = 0;
		boolean[] visitados = new boolean[nNodos];
		
		for(int i=0; i<nNodos; i++){
			if(!visitados[i]){
				numeroIslas++;
				dfs(matriz, visitados, i);
			}
		}
		
		System.out.println(numeroIslas);
		
		reader.close();
		
		

	}
	
	public static void dfs(boolean[][] matriz, boolean[] visitados, int nodoActual){
		visitados[nodoActual] = true;
		
		for(int i=0; i<matriz.length; i++){
			if(matriz[nodoActual][i] && !visitados[i]){
				dfs(matriz, visitados, i);
			}
		}		
	}
}
