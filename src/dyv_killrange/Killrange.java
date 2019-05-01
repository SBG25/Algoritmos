package dyv_killrange;

import java.util.Scanner;

public class Killrange {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		int nJugadores = reader.nextInt();
		int[] nivelesJugadores = new int[nJugadores];
		
		for(int i=0; i<nJugadores; i++){
			int nivel = reader.nextInt();
			nivelesJugadores[i] = nivel;
		}
		
		int nConsultas = reader.nextInt();
		int[][] resultados = new int[nConsultas][2];
		
		for(int i=0; i<nConsultas; i++){
			int nivelMin = reader.nextInt();
			int nivelMax = reader.nextInt();
			
			int inMin = buscarN(nivelesJugadores, 0, nJugadores, nivelMin);
			int inMax = buscarN(nivelesJugadores, 0, nJugadores, nivelMax);
			
			resultados[i][0] = inMin;
			resultados[i][1] = inMax;
		}
		
		for(int i=0; i<nConsultas; i++){
			System.out.println(resultados[i][0] + " " +resultados[i][1]);
		}
		
		reader.close();

	}
	
	public static int buscarN(int[] array, int ini, int fin, int nBuscado){
		if(fin-ini == 0){
			if(array[fin] == nBuscado) return fin;
		}
		else{
			int mid = ini + (fin - ini)/2;
			if(nBuscado == array[mid]) return mid;
			
			if(array[mid] > nBuscado){
				return buscarN(array, ini, mid-1, nBuscado);
			}
			else{
				return buscarN(array, mid+1, fin, nBuscado);
			}
		}
		return -1;
	}

}
