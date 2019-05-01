package dijkstra;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {

	private Dijkstra() {
		
	}
	
	public static<E> void resolver(Graph<E> grafo, E nodoPartida){
		Map<E, VectorDistancias<E>> distancias = new HashMap<>();
		Queue<VectorDistancias<E>> cola = new PriorityQueue<>(new VectorComparador<E>());
		
		VectorDistancias<E> vectorDistancias = new VectorDistancias<>(nodoPartida, 0, false);
		cola.add(vectorDistancias);
		distancias.put(nodoPartida, vectorDistancias);
		
		while(!cola.isEmpty()) {
			VectorDistancias<E> vd = cola.remove();
			List<Edge<E>> adyacentes = grafo.getListaAdyacencia(vd.getNodo());
			for(Edge<E> edge : adyacentes) {
				E destino = edge.getDestino();
				if(distancias.containsKey(destino)) {
					VectorDistancias<E> vd2 = distancias.get(destino);
					int nuevaDistancia = vd.getDistancia()+1;
					if(!vd2.getVisitado() && nuevaDistancia < vd2.getDistancia()) {
						vd2.setDistancia(nuevaDistancia);
						cola.add(vd2);
					}
				}
				else {
					VectorDistancias<E> vd2 = new VectorDistancias<>(destino, vd.getDistancia()+1, false);
					distancias.put(destino, vd2);
					cola.add(vd2);
				}
			}
			vd.setVisitado(true);
		}
		
		distancias.forEach((k,v) -> System.out.println(k + " " + v.getDistancia()));		
	}
	
	private static class VectorDistancias<E>{
		E nodo;
		int distancia;
		boolean visitado;
		
		public VectorDistancias(E nodo, int distancia, boolean visitado) {
			this.nodo = nodo;
			this.distancia = distancia;
			this.visitado = visitado;
		}
		
		public E getNodo() {
			return this.nodo;
		}
		
		public int getDistancia() {
			return this.distancia;
		}
		
		public boolean getVisitado() {
			return this.visitado;
		}
		
		public void setDistancia(int n) {
			this.distancia = n;
		}
		
		public void setVisitado(boolean b) {
			this.visitado = b;
		}
	}
	
	private static class VectorComparador<E> implements Comparator<VectorDistancias<E>>{

		@Override
		public int compare(VectorDistancias<E> v1, VectorDistancias<E> v2) {
			if(v1.getDistancia() > v2.getDistancia()) return 1;
			if(v1.getDistancia() < v2.getDistancia()) return -1;
			else return 0;
		}
		
	}
}
