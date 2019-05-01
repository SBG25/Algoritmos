package dyv_mergesort;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
	public static final  int UMBRAL = 3;

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		int size = teclado.nextInt();
		int[] miArray = new int[size];
		
		
		for(int i=0; i<size; i++) {
			miArray[i] = teclado.nextInt();
		}		
		
		solve(miArray, 0, miArray.length-1);
		for(int i : miArray) {
			System.out.print(i + " ");
		}
		teclado.close();
	}
	
	public static void solve(int[] array, int low, int high) {
		if((high - low) < UMBRAL) {
				Arrays.sort(array, low, high+1);
		}
		else {
			int mid = low + (high-low)/2;
			solve(array, low, mid);
			solve(array, mid, high);
			
			merge(mid, array, low, high);
		}
	}
	
	public static void merge(int mid, int[] array, int low, int high) {
		if (array[mid - 1] > (array[mid])) {
			int totalLength = high - low;
			int firstLength = mid - low;
			int[] copy = new int[totalLength];
			System.arraycopy(array, low, copy, 0, copy.length);
			int p = 0;
			int q = firstLength;
			for (int i = low; i < high; ++i) {
				if (q >= totalLength || (p < firstLength && copy[p] < (copy[q])))
					array[i] = copy[p++];
				else
					array[i] = copy[q++];
			}
		}
	}

}
