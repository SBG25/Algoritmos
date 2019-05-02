package bt_nreinas;

public class NReinas {
	public static final int N = 50;
	public static int[] RESULTADO = new int[N];
	
	public static boolean[] FILAS_OCUPADAS = new boolean[N];
	public static boolean[] DIAG_ASCENDENTE = new boolean[N*2-1];
	public static boolean[] DIAG_DESCENDENTE = new boolean[N*2-1];

	public static void main(String[] args) {
		boolean b = resolver(0, 0);
		System.out.println(b);
		
		for(int i=0; i<N; i++) {
			System.out.println("REINA "+i+":   ("+RESULTADO[i]+","+i+")");
		}

	}
	
	public static boolean resolver(int reinasColocadas, int columna) {
		//CASO BASE
		if(reinasColocadas == N) {
			return true;
		}
		
		//CASO RECURSIVO
		else {
			for(int i=0; i<N; i++) {
				if(esValido(i, columna)){
					cambiarTablero(i, columna, true);
					RESULTADO[columna] = i;
					if(resolver(reinasColocadas+1, columna+1)) return true;
					cambiarTablero(i, columna, false);
				}
			}
		}
		return false;
	}
	
	public static boolean esValido(int fila, int columna) {
		int diagAsc = fila + columna;
		
		int diagDesc = columna-fila+N-1;
		
		return !FILAS_OCUPADAS[fila] && !DIAG_ASCENDENTE[diagAsc] && !DIAG_DESCENDENTE[diagDesc];
	}
	
	public static void cambiarTablero(int fila, int columna, boolean b) {
		int diagAsc = fila + columna;
		int diagDesc = columna-fila+N-1;
		
		FILAS_OCUPADAS[fila] = b;
		DIAG_ASCENDENTE[diagAsc] = b;
		DIAG_DESCENDENTE[diagDesc] = b;
	}
	
	

}
