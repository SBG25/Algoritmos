package bab_trabajos;

import java.util.Random;

public class Main {
	//SE QUIERE ASIGNAR N TRABAJADORES A M TRABAJOS, CON M >= N, DE TAL FORMA QUE MAXIMICEMOS EL VALOR OBTENIDO.
	public static final int NTRABAJADORES = 25;
	public static final int NTRABAJOS = 25;
	
	public static final int MINVALUE = 1;
	public static final int MAXVALUE = 1000;
	public static final Random random = new Random();

	public static void main(String[] args) {
		long startTime;
		long endTime;
		
		int[][] matriz = new int[NTRABAJADORES][NTRABAJOS];
		
		for(int i=0; i<NTRABAJADORES; i++) {
			for(int j=0; j<NTRABAJOS; j++) {
				matriz[i][j] = random.nextInt(MAXVALUE) + MINVALUE;
			}
		}
		
		Trabajos tr = new Trabajos(NTRABAJADORES, NTRABAJOS, matriz);
		startTime = System.nanoTime();
		tr.solucionBaBLIFO();
		endTime = System.nanoTime();
		tr.imprimirSolucion();
		System.out.println("EN "+(endTime - startTime)/1000000+" ms");
	}
}
