package Example;

public class ThreadExtends extends Thread {
	@Override
	public void run() {
		super.run();
		for(int i=0;i<5;i++)
			System.out.println("���Ǽ̳�Thread��ThreadExtends�࣬��д��run������"+i+"�仰");
	}

}
