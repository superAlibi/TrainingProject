package Example;

public class ThreadTest {

	public static void main(String[] args) {
		Thread tht1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("�����߳�֮����֮ǰ��");
			}
		});

		System.out.println("�������̵߳�һ�仰");
		tht1.start();
		ThreadExtends thrse = new ThreadExtends();
		thrse.start();
		RunnableExtends runex = new RunnableExtends();
		Thread thr = new Thread(runex);
		thr.start();
	}

}
