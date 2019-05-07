package kruskal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import prim.Edge;

public class Kruskal {
	
	private Kruskal() {
		
	}
	
	public static List<Edge> solve(Queue<Edge> cola, int nNodos) {
		List<Edge> resultado = new LinkedList<>();
		int[] leader = new int[nNodos];
		int[] ranking = new int[nNodos];
		int contador = 0;
		
		for(int i=0; i<nNodos; i++) {
			leader[i] = i;
		}
		
		while(!cola.isEmpty() && contador < nNodos) {
			Edge edge = cola.remove();
			int oLeader = find(leader, edge.getOrigen());
			int dLeader = find(leader, edge.getDestino());
			
			if(oLeader != dLeader) {
				union(leader, ranking, oLeader, dLeader);
				contador++;
				resultado.add(edge);
			}
		}
		return resultado;
	}
	
	public static int find(int[] array, int x) {
		if(array[x] != x) {
			return find(array, array[x]);
		}
		else {
			return array[x];
		}
	}
	
	public static void union(int[] leader, int[] ranking, int setLeaderX, int setLeaderY) {
		if (ranking[setLeaderX] == ranking[setLeaderY]) {
			leader[setLeaderY] = setLeaderX;
	        ranking[setLeaderX]++;
		}
		else {
			if (ranking[setLeaderX] > ranking[setLeaderY]) leader[setLeaderY] = setLeaderX;
	        else leader[setLeaderX] = setLeaderY;
		}
	}

}
