package Example;

public class RunnableExtends implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 5; i++)
			System.out.println("这是继承Runnable接口的线程输出"+i);

	}

}
