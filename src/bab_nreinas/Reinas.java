package bab_nreinas;

public class Reinas {
	//QUEREMOS COLOCAR N REINAS EN UN TABLERO DE NxN PONDERADO POR PESO, DE TAL FORMA QUE OBTENGAMOS LA SOLUCIÓN CON EL MENOR VALOR POSIBLE.
	
	public static final int N = 15;
	public static final int MENORVALOR = 1;
	public static final int MAYORVALOR = 1000;
	
	
	
	private static int COTA_INFERIOR = 0;
	
	private static int[] TABLERO_MEJOR = new int[N];
	private static int[] TABLERO_ENCURSO = new int[N];
	
	private static boolean[] FILAS_OCUPADAS = new boolean[N];
	
	private static boolean[] DIAG_DESC = new boolean[N*2-1];
	private static boolean[] DIAG_ASC = new boolean[N*2-1];
	private static int[] MEJOR_SOLUCION = new int[N];
		

	// SE RECIBE POR ENTRADA UNA MATRIZ NxN CON ENTEROS, QUE DETERMINARÁN EL PESO DE CADA CASILLA.
	// SE QUIEREN COLOCAR N REINAS DE TAL FORMA QUE OBTENGAMOS LA SOLUCIÓN CON EL MENOR PESO POSIBLE.	
	public static void main(String[] args){
		int[][] tablero = GeneradorPrueba.generarArray(N, MENORVALOR, MAYORVALOR);
		COTA_INFERIOR = primeraSolucion(tablero);
		
		for(int i=0; i<N; i++) {
			MEJOR_SOLUCION[i] = Integer.MAX_VALUE;
		}
		actualizarVectorMejores(tablero, 0);
		resolver(tablero, 0, 0, 0);
		
		System.out.println("Cota: "+COTA_INFERIOR);
		for(int i=0; i<N; i++) {
			System.out.println("REINA "+i+" ("+TABLERO_MEJOR[i]+","+i+")");
		}

	}
	
	public static void resolver(int[][] tablero, int columna, int cotaActual, int nReinas) {
		if(nReinas == N) {
			if(cotaActual < COTA_INFERIOR){
				COTA_INFERIOR = cotaActual;
				TABLERO_MEJOR = TABLERO_ENCURSO.clone();
			}
		}
		else {
			for(int i=0; i<N; i++) {
				//VEMOS SI PODEMOS COLOCAR LA REINA EN FILA x I
				
				int diagDesc = i - columna;
				int diagAsc = i + columna;
				
				if(diagDesc < 0) diagDesc = (N*2-1) + diagDesc;
				if(esFactible(i, diagDesc, diagAsc)) {
					FILAS_OCUPADAS[i] = true;
					DIAG_DESC[diagDesc] = true;
					DIAG_ASC[diagAsc] = true;
					actualizarVectorMejores(tablero, columna+1);
					
					int miCota = tablero[i][columna] + cotaActual;
					int mejorCotaPosible = miCota + mejorCotaPosible(columna+1);
					
					if(mejorCotaPosible < COTA_INFERIOR) {
						TABLERO_ENCURSO[columna] = i;
						resolver(tablero, columna+1, miCota, nReinas+1);
					}
					FILAS_OCUPADAS[i] = false;
					DIAG_DESC[diagDesc] = false;
					DIAG_ASC[diagAsc] = false;
				}
			}
		}
	}
	
	public static int mejorCotaPosible(int columna) {
		int mejorCota = 0;
		
		for(int i=columna; i<N; i++) {
			mejorCota += MEJOR_SOLUCION[i];
		}
		
		return mejorCota;
	}
	
	public static boolean esFactible(int fila, int diagDesc, int diagAsc) {
		return !FILAS_OCUPADAS[fila] && !DIAG_DESC[diagDesc] && !DIAG_ASC[diagAsc];
	}
	
	public static void actualizarVectorMejores(int[][] tablero, int colum) {
		for(int columna = colum; columna < N; columna++) {
			for(int i=0; i<N; i++) {
				int diagDesc = i - columna;
				int diagAsc = i + columna;
							
				if(diagDesc < 0) {
					diagDesc = (N*2-1) + diagDesc;
				}
				
				if(tablero[i][columna] < MEJOR_SOLUCION[columna] && esFactible(i, diagDesc, diagAsc)) {
					MEJOR_SOLUCION[columna] = tablero[i][columna];
				}
			}
		}
	}
	
	public static int primeraSolucion(int[][] tablero) {
		int cota = 0;
		int fila = 0;
		
		for(int i=0; i<tablero.length; i++) {
			cota += tablero[fila][i];
			fila += 2;
			if(fila >= tablero.length) fila = 1;
		}
		
		return cota;
		
	}
}
