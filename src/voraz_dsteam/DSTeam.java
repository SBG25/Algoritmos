package voraz_dsteam;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class DSTeam {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		int nPiezas = teclado.nextInt();
		double maxPeso = teclado.nextDouble();
		String estilo = teclado.next();
		double pesoEstilo = maxPeso;
		
		if("ligero".equals(estilo)) pesoEstilo = maxPeso * 0.5;
		else if("medio".equals(estilo)) pesoEstilo = maxPeso * 0.75;
				
		Queue<Equipo> cola = new PriorityQueue<>(new ComparadorEquipoValor());
		Queue<String> nombresEquipo = new PriorityQueue<>();
		int contador = 0;
		
		while(contador < nPiezas) {
			String nombre = teclado.next();
			double peso = teclado.nextInt();
			double defensa = teclado.nextDouble();
			
			Equipo e = new Equipo(nombre, peso, defensa);
			cola.add(e);
			contador++;
		}
		
		
		double defensaFinal = 0;
		double pesoFinal = 0;
		while(!cola.isEmpty() && pesoFinal < pesoEstilo) {
			Equipo equipo = cola.remove();
			if(equipo.getPeso() <= pesoEstilo - pesoFinal) {
				defensaFinal += equipo.getDefensa();
				pesoFinal += equipo.getPeso();
			}
			else {
				double div = equipo.getPeso() / (pesoEstilo - pesoFinal);
				defensaFinal += equipo.getDefensa() / div;
				pesoFinal = pesoEstilo;
			}
			nombresEquipo.add(equipo.nombre);
		}
				
		DecimalFormat df = new DecimalFormat("#.00");
		
		System.out.println(df.format(defensaFinal));
		while(!nombresEquipo.isEmpty()) {
			System.out.println(nombresEquipo.remove());
		}
		
		teclado.close();

	}
	
	public static class Equipo{
		private String nombre;
		private double peso;
		private double defensa;
		private double valor;
		
		public Equipo(String n, double p, double d) {
			nombre = n;
			peso = p;
			defensa = d;
			valor = defensa / peso;
		}

		public String getNombre() {
			return nombre;
		}

		public double getPeso() {
			return peso;
		}

		public double getDefensa() {
			return defensa;
		}
		
		public double getValor() {
			return valor;
		}
	}
	
	public static class ComparadorEquipoValor implements Comparator<Equipo>{
		public int compare(Equipo e1, Equipo e2) { 
            if (e1.getValor() < e2.getValor()) 
                return 1; 
            else if (e1.getValor() > e2.getValor()) 
                return -1; 
            else
            	return 0; 
        } 
	}

}
