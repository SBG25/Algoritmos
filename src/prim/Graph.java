package prim;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
	private int nNodos;
	private Map<Integer, List<Edge>> adjList;
	
	public Graph(int n) {
		nNodos = n;
		adjList = new HashMap<>();
		for(int key=0; key<n; key++) {
			List<Edge> list = new LinkedList<>();
			adjList.put(key, list);
		}
	}
	
	public int getNNodos() {
		return nNodos;
	}
	
	public List<Edge> getAdjList(int key){
		return adjList.get(key);
	}
	
	public void insertarArista(int origen, int destino, int peso) {
		Edge e = new Edge(origen, destino, peso);
		adjList.get(origen).add(e);
	}
}
