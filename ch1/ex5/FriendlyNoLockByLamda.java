// from ex14_08

package ex5;

class FriendlyNoLockByLamda {
	private FriendlyNoLockByLamda partner;
	private String name;

	public FriendlyNoLockByLamda(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		synchronized (partner) {
			System.out.println(Thread.currentThread().getName() + " in " + name
					+ ".hug() trying to invoke " + partner.name + ".hugBack()");
			partner.hugBack();
		}
	}

	public synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name
				+ ".hugBack()");
	}

	public void becomeFriend(FriendlyNoLockByLamda partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		final FriendlyNoLockByLamda jareth = new FriendlyNoLockByLamda("jareth");
		final FriendlyNoLockByLamda cory = new FriendlyNoLockByLamda("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		new Thread(() -> jareth.hug(), "Thread1").start();

		new Thread(() -> cory.hug(), "Thread2").start();
		// 5行が1行の見た目に（実質は3行が1行に減った）

	}
}
