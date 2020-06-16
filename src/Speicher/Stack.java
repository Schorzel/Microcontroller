package Speicher;

public class Stack {
	
	private static int[] stack = new int[8];
	private static int stackPointer;
	
	
	//Füllt den Stack mit Nullen
	public Stack() {
		for (int i = 0; i < stack.length; i++) {
			stack[i] = 0;
		}
		stackPointer = 0;
	}
	
	
	public static int getStackValue() {
		return stack[stackPointer];
	}
	
	
	public static int[] getStack() {
		return stack;
	}
	
	
	public static int getStackPointer() {
		return stackPointer;
	}
	
	
	//Da der Stack nur 8 Stellen hat fängt dieser von vorne an wenn das Maximum von 8 erreicht ist
	public static void setStack(int data) {
		stack[stackPointer] = data;
		if (stackPointer == 7) {
			System.out.println("Stack Overflow: Zähler reset auf 0");
			stackPointer = 0;
		} else {
			stackPointer++;
		}
	}
	
	
	public static void setStackPointer(int sp) {
		stackPointer = sp;
	}

	
	
	
}
