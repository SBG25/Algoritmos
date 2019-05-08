package dyv_quicksortDual;

import java.util.Arrays;

public class Quicksort {
	private Quicksort() {
		
	}
	
	public static void solve(int[] array, int ini, int fin) {		
		if(array[ini] > array[fin]) {
			swap(array, ini, fin);
		}
		
		if(fin - ini < 27) {
			Arrays.sort(array, ini, fin+1);
		}
		else {
				int pivoteMenor = array[ini];
				int pivoteMayor = array[fin];
				
				int indexMenor = ini;
				int indexMayor = fin;
				int indexRecorrer = ini+1;
				
				while(indexRecorrer != indexMayor) {
					if(array[indexRecorrer] < pivoteMenor) {
						indexMenor++;
						swap(array, indexMenor, indexRecorrer);
						indexRecorrer++;
					}
					else if(array[indexRecorrer] > pivoteMayor){
						indexMayor--;
						swap(array, indexMayor, indexRecorrer);
					}
					else {
						indexRecorrer++;
					}
				}
				
				swap(array, indexMenor, ini);
				swap(array, indexMayor, fin);
				
				if(indexMenor > ini) solve(array, ini, indexMenor-1);
				if(indexMenor+1 < indexMayor)solve(array, indexMenor+1, indexMayor-1);
				if(indexMayor < fin) solve(array, indexMayor+1, fin);
		}
	}
	
	private static void swap(int[] array, int i1, int i2) {
		int aux = array[i1];
		array[i1] = array[i2];
		array[i2] = aux;
	}
}
