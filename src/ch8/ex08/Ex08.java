package ch8.ex08;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Ex08 {
	private Queue checkedQ;
	private Queue q;

	Ex08() {
		q = new LinkedList<Integer>();
		checkedQ = Collections.checkedQueue(new LinkedList<Integer>(),
				Integer.class);
	}

	public void sample() {
		q.add("a");
	}

	public void sampleCheck() {
		checkedQ.add("a");
	}

	public static void main(String[] args) {
		Ex08 tmp = new Ex08();
		tmp.sample();

		try {
			tmp.sampleCheck();
		} catch (ClassCastException e) {
			System.out.println("error checked!");
		}
	}
}
