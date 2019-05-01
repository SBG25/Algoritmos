package bt_caballo;

public class Main {
	//DADO UN TABLERO DE NFILAS Y NCOLUMNAS SE QUIERE QUE UN CABALLO RECORRA EL TABLERO SIN PASAR DOS VECES POR LA MISMA CASILLA.
	//EN LA MODALIDAD CERRADO ADEMÁS DEBERÁ TERMINAR EN LA CASILLA DE COMIENZO.
	
	public static final int NFILAS = 6;
	public static final int NCOLUMNAS = 6;
	
	public static void main(String[] args) {
		Caballo pc = new Caballo(NFILAS, NCOLUMNAS);
		
		int[][] lista = pc.solve(0, 0, "cerrado");
		
		if(lista == null) {
			System.out.println("NO SE PUDO RESOLVER");
		}
		else {
			System.out.println("EMPIEZA: ");
			for(int i=0; i<NFILAS; i++) {
				for(int j=0; j<NCOLUMNAS; j++) {
					System.out.print(lista[i][j] + "\t");
				}
				System.out.println("");
			}
		}
	}

}
