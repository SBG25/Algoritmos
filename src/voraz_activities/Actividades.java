package voraz_activities;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Actividades {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int t;
		
		t = reader.nextInt();
		int[] res = new int[t];
		
		for(int i=0; i<t; i++) {
			int n;
			n = reader.nextInt();
			
			Queue<Actividad> colaActividades = new PriorityQueue<>(new ComparadorActividad());
			
			for(int j=0; j<n; j++) {
				int inicio = reader.nextInt();
				int fin = reader.nextInt();
				
				Actividad ac = new Actividad(inicio, fin);
				
				colaActividades.add(ac);
			}
			
			Actividad a1 = colaActividades.remove();
			int actRealizadas = 1;
			int prevFin = a1.getFin();
			while(!colaActividades.isEmpty()){
				Actividad act = colaActividades.remove();
				if(act.getInicio() >= prevFin) {
					actRealizadas++;
					prevFin = act.getFin();
				}
			}
			
			res[i] = actRealizadas;
		}
		
		for(int r : res) {
			System.out.println(r);
		}
		
		reader.close();
	}
	
	public static class Actividad{
		private int inicio;
		private int fin;
		
		public Actividad(int i, int f) {
			inicio = i;
			fin = f;
		}
		
		public int getInicio() {
			return inicio;
		}
		
		public int getFin() {
			return fin;
		}
	}
	
	public static class ComparadorActividad implements Comparator<Actividad>{

		@Override
		public int compare(Actividad a1, Actividad a2) {
			if(a1.getFin() > a2.getFin())
				return 1;
			else if(a1.getFin() < a2.getFin())
				return -1;
			return 0;
		}
		
	}
}
