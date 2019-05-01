package dyv_dsscore;

import java.util.Scanner;

public class DSScore {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int nEnemigos = teclado.nextInt();
		int[] arrayEnemigos = new int[nEnemigos];
		long[] puntuacionAcumulada = new long[nEnemigos+1];
		puntuacionAcumulada[0] = 0;
		
		long sumaAcumulada = 0;
		for(int i=0; i<nEnemigos; i++) {
			int enemigo = teclado.nextInt();
			arrayEnemigos[i] = enemigo;
			sumaAcumulada += enemigo;
			puntuacionAcumulada[i+1] = sumaAcumulada;
		}
		
		int nCasos = teclado.nextInt();
		int[] resultados = new int[nCasos];
		
		for(int i=0; i<nCasos; i++) {
			int nivelCaballero = teclado.nextInt();
			resultados[i] = solve(arrayEnemigos, nivelCaballero, 0, nEnemigos-1);
		}
		
		for(int i=0; i<nCasos; i++) {
			int res = resultados[i];
			
			System.out.print(res);
			System.out.println(" " + puntuacionAcumulada[res]);
			
		}
		teclado.close();

	}
	
	private static int solve(int[] enemigos, int nivelBuscado, int ini, int fin) {
		if(fin - ini <= 0) {
			if(enemigos[ini] <= nivelBuscado) return ini+1;
			else return ini;
		}
		else {
			int medio = (ini + fin) / 2;
			if(enemigos[medio] == nivelBuscado) return medio +1;
			
			if(enemigos[medio] > nivelBuscado) {
				return solve(enemigos, nivelBuscado, ini, medio-1);
			}
			else {
				return solve(enemigos, nivelBuscado, medio+1, fin);
			}
		}
	}

}
