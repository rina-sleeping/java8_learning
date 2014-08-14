package ex6;

import java.util.concurrent.Callable;

public class Unchecker {

	public static Runnable uncheck(RunnableEx runner) {
		return () -> {
			try {
				runner.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	public static Runnable uncheck2(Callable<Void> caller) {
		return () -> {
			try {
				caller.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	public static void main(String[] args) {
		new Thread(uncheck(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
		})).start();

		new Thread(uncheck2(() -> {
			System.out.println("Zzz");
			Thread.sleep(1000);
			return null;// call�֐��ɂ��邽�߂ɖ߂�l���K���K�v�ɂȂ�Bp9�̓��e�B
			})).start();

		new Thread(() -> {
			System.out.println("Zzz");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}
}
