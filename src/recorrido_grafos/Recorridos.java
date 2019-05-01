package recorrido_grafos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Recorridos {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		int nNodos = reader.nextInt();
		int nAristas = reader.nextInt();
		int nodoInicio = reader.nextInt() -1;
		
		
		boolean[][] grafo = new boolean[nNodos][nNodos];
		
		for(int i=0; i<nAristas; i++) {
			int n1 = reader.nextInt()-1;
			int n2 = reader.nextInt()-1;
			
			grafo[n1][n2] = true;
			grafo[n2][n1] = true;
		}
		
		String resAnchura = recorridoAnchura(grafo, new boolean[nNodos], new LinkedList<Integer>(), nodoInicio);
		String resProfundidad = recorridoProfundidad(grafo, new boolean[nNodos], nodoInicio);
		System.out.println("Anchura: "+resAnchura);
		System.out.println("Profundidad: "+resProfundidad);
		
		reader.close();
	}
	
	public static String recorridoAnchura(boolean[][] grafo, boolean[] visitados, Queue<Integer> siguiente, int nodoActual) {
		String resultado = Integer.toString(nodoActual+1);
		visitados[nodoActual] = true;
		
		for(int i=0; i<visitados.length; i++) {
			if(!visitados[i] && grafo[nodoActual][i] && !siguiente.contains(i))
				siguiente.add(i);
		}
		if(!siguiente.isEmpty()) {
			int sig = siguiente.remove();
			resultado += " " + recorridoAnchura(grafo, visitados, siguiente, sig);
		}
		return resultado;
	}
	
	
	
	public static String recorridoProfundidad(boolean[][] grafo, boolean[] visitados, int nodoActual) {
		String resultado = Integer.toString(nodoActual+1);
		visitados[nodoActual] = true;
		
		for(int i=0; i<visitados.length; i++) {
			if(!visitados[i] && grafo[nodoActual][i]) {
				resultado += " " +recorridoProfundidad(grafo, visitados, i);
			}
		}
		return resultado;
	}
	
	

}
