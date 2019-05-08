package bab_mochila;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Mochila {
	int[] valoresObjetos;
	int[] pesosObjetos;
	int nObjetos;
	int pesoMaximo;
	
	public Mochila(int[] vo, int[] po, int pm) {
		valoresObjetos = vo;
		pesosObjetos = po;
		pesoMaximo = pm;
		nObjetos = vo.length;
	}
	
	public List<Objeto> solve() {
		Objeto[] objetosOrdenados = arrayObjetos(valoresObjetos, pesosObjetos);
		
		List<Objeto> resultado = primeraSolucion(objetosOrdenados);
		double lowerBound = calculaValor(resultado);
		
		Queue<NodoVivo> cola = new PriorityQueue<>();
		
		LinkedList<Objeto> l = new LinkedList<>();
		double mc = calcularCota(l);
		cola.add(new NodoVivo(l, 0, 0, pesoMaximo, mc));
		
		while(!cola.isEmpty()) {
			NodoVivo nodoVivo = cola.remove();
			
			//LLEGAMOS A SOLUCIÓN
			if(nodoVivo.siguienteObjeto == nObjetos) {
				if(nodoVivo.valorAcumulado > lowerBound) {
					lowerBound = nodoVivo.valorAcumulado;
					resultado = nodoVivo.resultadoParcial;
				}
			}
			else {
				if(nodoVivo.cota > lowerBound) {
					double nuevaCota = mejorCota(objetosOrdenados, nodoVivo.siguienteObjeto+1, nodoVivo.pesoRestante);
					if(nodoVivo.pesoRestante - objetosOrdenados[nodoVivo.siguienteObjeto].getPeso() >= 0) {
						LinkedList<Objeto> listaAdd = (LinkedList<Objeto>) nodoVivo.resultadoParcial.clone();
						int valorAc = nodoVivo.valorAcumulado + objetosOrdenados[nodoVivo.siguienteObjeto].getValor();
						int nuevoPeso = nodoVivo.pesoRestante - objetosOrdenados[nodoVivo.siguienteObjeto].getPeso();
						
						NodoVivo nv1 = new NodoVivo(listaAdd, valorAc, nodoVivo.siguienteObjeto+1, nuevoPeso, nuevaCota+valorAc);
						cola.add(nv1);
					}
					cola.add(new NodoVivo(nodoVivo.resultadoParcial, nodoVivo.valorAcumulado, nodoVivo.siguienteObjeto+1, nodoVivo.pesoRestante, nodoVivo.valorAcumulado+nuevaCota));
				}
				
			}
		}
		
		return resultado;
	}
	
	private Objeto[] arrayObjetos(int[] vo, int[] po) {
		Objeto[] res = new Objeto[vo.length];
		
		for(int i=0; i<vo.length; i++) {
			res[i] = new Objeto(i, vo[i], po[i]);
		}
		
		Arrays.sort(res, new Objeto.ComparadorObjeto());
		return res;
	}
	
	private static class NodoVivo{
		LinkedList<Objeto> resultadoParcial;
		int valorAcumulado;
		int siguienteObjeto;
		int pesoRestante;
		double cota;
		
		public NodoVivo(LinkedList<Objeto> rp, int va, int s, int pr, double c) {
			resultadoParcial = rp;
			valorAcumulado = va;
			siguienteObjeto = s;
			pesoRestante = pr;
			cota = c;
		}
	}
	
	private int calculaValor(List<Objeto> parcial) {
		int valorAcum = 0;
		
		for(Objeto obj : parcial) {
			valorAcum += obj.getValor();
		}
		
		return valorAcum;
	}
	
	private double mejorCota(Objeto[] parcial, int index, int pesoRestante) {
		if(index >= nObjetos) return 0;
		else return parcial[index].getRatio()*pesoRestante;
	}
	
	private int calcularCota(LinkedList<Objeto> parcial) {
		int valorAc = 0;
		for(Objeto ob : parcial) {
			valorAc += ob.getValor();
		}
		return valorAc;
	}
	
	private LinkedList<Objeto> primeraSolucion(Objeto[] objetosOrdenados){
		LinkedList<Objeto> res = new LinkedList<>();
		int pesoRestante = pesoMaximo;
		for(Objeto obj : objetosOrdenados) {
			if(pesoRestante - obj.getPeso() >= 0) {
				pesoRestante -= obj.getPeso();
				res.add(obj);
			}
		}
		return res;
	}
}
