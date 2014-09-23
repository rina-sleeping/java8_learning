// from ex14_08

package ch1.ex05;

class FriendlyNoLock {
	private FriendlyNoLock partner;
	private String name;

	public FriendlyNoLock(String name) {
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

	public void becomeFriend(FriendlyNoLock partner) {
		this.partner = partner;
	}

	public static void main(String[] args) {
		final FriendlyNoLock jareth = new FriendlyNoLock("jareth");
		final FriendlyNoLock cory = new FriendlyNoLock("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		new Thread(new Runnable() {
			public void run() {
				jareth.hug();
			}
		}, "Thread1").start();

		new Thread(new Runnable() {
			public void run() {
				cory.hug();
			}
		}, "Thread2").start();

	}
}
