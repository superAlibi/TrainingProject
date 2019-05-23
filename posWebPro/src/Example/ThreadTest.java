package Example;

public class ThreadTest {

	public static void main(String[] args) {
		Thread tht1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("在主线程之后还是之前？");
			}
		});

		System.out.println("我是主线程第一句话");
		tht1.start();
		ThreadExtends thrse = new ThreadExtends();
		thrse.start();
		RunnableExtends runex = new RunnableExtends();
		Thread thr = new Thread(runex);
		thr.start();
	}

}
