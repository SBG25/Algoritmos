package bab_nreinas;

import java.util.Random;

public class GeneradorPrueba {
	public static final Random random = new Random();
	
	public static int[][] generarArray(int size, int menorN, int mayorN){
		int[][] tablero = new int[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				int randomInt = random.nextInt(mayorN) + menorN;
				tablero[i][j] = randomInt;
			}
		}
		
		return tablero;
	}

}
