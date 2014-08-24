package ch2.ex1;

class Counter {
	private int sum;

	public Counter() {
		this.sum = 0;
	}

	public synchronized void add(int add) {
		this.sum += add;
	}

	public synchronized int get() {
		return this.sum;
	}

}
