package dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {
	private int nNodos;
	private Map<Integer, List<Edge>> adjacencyList;
	
	public Graph(int nNodos) {
		this.nNodos = 0;
		adjacencyList = new HashMap<>();
		for(int i=0; i < nNodos; i++) {
			addNode();
		}
	}
	
	public int getNNodos() {
		return nNodos;
	}
	
	public void addNode() {
		adjacencyList.put(nNodos, new LinkedList<>());
		this.nNodos += 1;
	}
	
	public void addEdge(int origen, int destino, int peso) {
		Edge edge = new Edge(origen, destino, peso);
		adjacencyList.get(origen).add(edge);
	}
	
	public List<Edge> getAdjacency(int index){
		return adjacencyList.get(index);
	}
	
	public boolean areAdjacents(int origen, int destino) {
		List<Edge> adjL = getAdjacency(origen);
		for(Edge e : adjL) {
			if(e.getDestino() == destino) return true;
		}
		return false;
	}
	
	
}
