package jasim.programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Pattern;

public class WordMachine {
	private static final int MAX = 0xFFFFF;//20 bit integer
	private static final int MIN = 0;
	private final Stack<Integer> stack = new Stack<>();
	private static final Pattern NUMERIC = Pattern.compile("\\d+");
	private static final Map<String, Runnable> OPERATIONS = new HashMap<>();
	{
		OPERATIONS.put("POP", this::pop);
		OPERATIONS.put("DUP", this::dup);
		OPERATIONS.put("+", this::add);
		OPERATIONS.put("-", this::sub);
	}

	public int machineOutput(String s) {
		try {
			Arrays.stream(s.split(" ")).forEach(this::applyCommand);
			return pop();
		} catch (IllegalArgumentException e) {
			return -1;
		}
	}

	private void applyCommand(String s) {
		if (NUMERIC.matcher(s).matches()) {
			push(Integer.valueOf(s));
		} else {
			OPERATIONS.get(s).run();
		}

	}

	private void push(int i) {
		withInRange(i);
		stack.push(i);
	}

	private int pop() {
		hasElements(1);
		return stack.pop();
	}

	private void dup() {
		hasElements(1);
		stack.push(stack.peek());
	}

	private void add() {
		hasElements(2);
		push(stack.pop() + stack.pop());
	}

	private void sub() {
		hasElements(2);
		push(stack.pop() - stack.pop());
	}

	private int hasElements(int i) {
		if (stack.size() < i) {
			throw new IllegalArgumentException("Too few elements in the stack");
		}
		return i;
	}

	private int withInRange(int i) {
		if (i < MIN || i > MAX) {
			throw new IllegalArgumentException("Input value is under/overflow");
		}
		return i;
	}
}
