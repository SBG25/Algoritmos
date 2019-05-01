package voraz_activities2;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Activities2 {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		
		int nCasos = reader.nextInt();
		int[] resultados = new int[nCasos];
		
		for(int i=0; i<nCasos; i++){
			int nActividades = reader.nextInt();
			Queue<Actividad> cola = new PriorityQueue<>(new ComparadorActividad());
			
			for(int j=0; j<nActividades; j++){
				int inicio = reader.nextInt();
				int fin = reader.nextInt();
				int descanso = reader.nextInt();
				
				Actividad act = new Actividad(inicio, fin+descanso);
				cola.add(act);
			}
			
			int actividadesAsistidas = 1;
			Actividad actividadAnterior = cola.remove();
			while(!cola.isEmpty()){
				Actividad ac = cola.remove();
				if(ac.getInicio() >= actividadAnterior.getFin()){
					actividadesAsistidas++;
					actividadAnterior = ac;
				}
			}
			resultados[i] = actividadesAsistidas;
		}
		
		for(int i=0; i<nCasos; i++){
			System.out.println(resultados[i]);
		}
		
		reader.close();
	}
	
	public static class Actividad{
		private int inicio;
		private int fin;
		
		public Actividad(int i, int f){
			inicio = i;
			fin = f;
		}
		
		public int getInicio(){
			return inicio;
		}
		
		public int getFin(){
			return fin;
		}
	}
	
	public static class ComparadorActividad implements Comparator<Actividad>{

		@Override
		public int compare(Actividad a1, Actividad a2) {
			if(a1.getFin() > a2.getFin()) return 1;
			if(a1.getFin() < a2.getFin()) return -1;
			else return 0;
		}
		
	}

}
