package voraz_finalstory;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FinalStory {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int nCombates = teclado.nextInt();
		int[] resultados = new int[nCombates];
		
		for(int i=0; i<nCombates; i++) {
			int ataqueJugador = teclado.nextInt();
			int nEnemigos = teclado.nextInt();
	
			Queue<Enemigo> colaEnemigos = new PriorityQueue<>(new ComparadorValorEnemigos(ataqueJugador));
			int damagePorTurno = 0;
			
			int[] atEnemigos = new int[nEnemigos];
			
			for(int j=0; j<nEnemigos; j++) {
				int ataque = teclado.nextInt();
				damagePorTurno += ataque;
				
				atEnemigos[j] = ataque;
			}
			
			for(int j=0; j<nEnemigos; j++) {
				int vida = teclado.nextInt();	
				colaEnemigos.add(new Enemigo(atEnemigos[j], vida));
			}
			
			int damageTotal = 0;
			while(!colaEnemigos.isEmpty()) {
				Enemigo enemigo = colaEnemigos.remove();
				int nAtaques = (int) Math.ceil((double)enemigo.getVida()/ataqueJugador);
				
				damageTotal += damagePorTurno * nAtaques;
				damagePorTurno -= enemigo.getAtaque();
			}
			
			resultados[i] = damageTotal;
		}
		
		for(int res : resultados) {
			System.out.println(res);
		}

		teclado.close();
	}
	
	public static class Enemigo{
		private int vida;
		private int ataque;
		
		public Enemigo(int a, int v) {
			vida = v;
			ataque = a;
		}
		
		public int getVida() {
			return vida;
		}
		
		public void quitarVida(int v) {
			vida -= v;
		}
		
		public int getAtaque() {
			return ataque;
		}
	}
	
	public static class ComparadorValorEnemigos implements Comparator<Enemigo>{
		private int ataqueJugador;
		
		public ComparadorValorEnemigos(int aj) {
			ataqueJugador = aj;
		}
		
		
		public int compare(Enemigo e1, Enemigo e2) {
			double valueE1 = e1.getAtaque() / Math.ceil((double)e1.getVida()/ataqueJugador);
			double valueE2 = e2.getAtaque() / Math.ceil((double)e2.getVida()/ataqueJugador);
						
            if (valueE1 < valueE2) 
                return 1; 
            else if (valueE1 > valueE2) 
                return -1; 
            else
            	return 0; 
        } 
        
	}

}
