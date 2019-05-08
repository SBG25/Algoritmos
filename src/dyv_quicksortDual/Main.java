package dyv_quicksortDual;

import java.util.Arrays;
import java.util.Random;

public class Main {
	public static final int N = 10000000;
	public static final int RANGE = 2*N;

	public static void main(String[] args) {
		Random random = new Random();
		int[] array = new int[N];
		
		for(int i=0; i<N; i++) {
			array[i] = random.nextInt(RANGE);
		}
		
		int[] array2 = array.clone();
		
		
		Quicksort.solve(array, 0, N-1);
		
		Arrays.sort(array2);
		
		boolean b = true;
		for(int i=0; i<N; i++) {			
			if(array[i] != array2[i]) {
				b = false;
				System.out.println("INCORRECTO EN "+i);
				break;
			}
		}
		
		if(b) System.out.println("CORRECTO");

	}

}
