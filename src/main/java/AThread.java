public class AThread {
	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("i am a thread , i am running...");
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		t.start();
	}
}
