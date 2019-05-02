package bab_trabajos;

public class Trabajos {	
	private int nTrabajadores;
	private int nTrabajos;
	
	private int[][] matrizTrabajos;
	private int[] mejorTrabajo;
	private boolean[] trabajoEnUso;
	
	private int[] solucionCompleta;
	
	private int mejorValor;
	
	public Trabajos(int trabajadores, int trabajos, int[][] matriz) {
		nTrabajadores = trabajadores;
		nTrabajos = trabajos;
		
		matrizTrabajos = matriz;
		mejorTrabajo = new int[nTrabajadores];
		trabajoEnUso = new boolean[nTrabajos];
		actualizarMejorTrabajo(0);
		
		solucionCompleta = new int[nTrabajadores];
	}
	
	private void actualizarMejorTrabajo(int indice) {
		for(int i=indice; i<nTrabajadores; i++) {
			mejorTrabajo[i] = 0;
			for(int j=0; j<nTrabajos; j++) {
				if(!trabajoEnUso[j] && matrizTrabajos[i][j] > mejorTrabajo[i]) {
					mejorTrabajo[i] = matrizTrabajos[i][j];
				}
			}
		}
	}
	
	private int mejorCota(int indice) {
		int mejorCota = 0;
		for(int i=indice; i<nTrabajadores; i++) {
			mejorCota += mejorTrabajo[i];
		}
		
		return mejorCota;
		
	}
	
	private int solucionVoraz() {
		int sumaAcumulada = 0;
		for(int i=0; i<nTrabajadores;i++) {
			sumaAcumulada += matrizTrabajos[i][i];
		}
		return sumaAcumulada;
	}
	
	public void solucionBaBLIFO() {
		mejorValor = solucionVoraz();
		solucionBaBAux(0, 0, new int[nTrabajadores]);
	}
	
	private void solucionBaBAux(int trabajadorActual, int cotaAcumulada, int[] solucionParcial) {
		//CASO 
		if(trabajadorActual == nTrabajadores) {
			if(cotaAcumulada > mejorValor) {
				mejorValor = cotaAcumulada;
				solucionCompleta = solucionParcial.clone();
			}
		}
		
		//CASO RECURSIVO
		else {
			for(int j=0; j<nTrabajos; j++) {
				if(!trabajoEnUso[j]) {
					trabajoEnUso[j] = true;
					actualizarMejorTrabajo(trabajadorActual+1);
					int miCota = cotaAcumulada + matrizTrabajos[trabajadorActual][j];
					if(miCota + mejorCota(trabajadorActual+1)> mejorValor) {
						solucionParcial[trabajadorActual] = j;
						solucionBaBAux(trabajadorActual+1, miCota, solucionParcial);
					}
					trabajoEnUso[j] = false;
				}
			}
		}
	}

	
	public void imprimirSolucion() {
		for (int i=0; i<nTrabajadores; i++) {
			int trabajo = solucionCompleta[i];
			
			System.out.println("TRABAJADOR: "+i+"    TRABAJO: "+trabajo+"    VALOR: "+matrizTrabajos[i][trabajo]);
		}
		System.out.println("");
		System.out.println("SUMA TOTAL: "+mejorValor);
	}

}
