package Example;

public class ThreadExtends extends Thread {
	@Override
	public void run() {
		super.run();
		for(int i=0;i<5;i++)
			System.out.println("这是继承Thread的ThreadExtends类，重写的run方法第"+i+"句话");
	}

}
