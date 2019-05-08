package bab_mochila;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		int[] valor = {10,10,12,18};
		int[] peso = {2,4,6,9};
		
		Mochila m = new Mochila(valor, peso, 15);
		List<Objeto> l = m.solve();
		
		for(Objeto o : l) {
			System.out.println(o.getValor() + " "+o.getPeso());
		}

	}

}
