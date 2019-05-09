package executiontime;

public class ExecutionTime {
	private static long startTime;
	
	private ExecutionTime() {
		
	}

	public static void startTime() {
		startTime = System.nanoTime();
	}
	
	public static void endTime() {
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000 + " ms");
	}
}
