package Speicher;

public class Stack {
	
	private static int[] stack = new int[8];
	private static int stackPointer;
	
	

	
	
	public static int getStackValue() {
		return stack[stackPointer];
	}
	
	
	public static int[] getStack() {
		return stack;
	}
	
	
	public static int getStackPointer() {
		return stackPointer;
	}
	
	
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
