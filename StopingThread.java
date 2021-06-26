import java.util.concurrent.TimeUnit;

/* 
 * 
 * The original stop() method would not provide any guarantees about in what state the thread was
 *  stopped. Instead of calling the stop() method you will have to implement your thread code 
 *  so it can be stopped
 *  
 *  
 *  */
public class StopingThread {

	public static void main(String[] args) {
		StopingThread1 st = new StopingThread1();
		Thread t = new Thread(st);
		t.start();
		try {
			TimeUnit.MILLISECONDS.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		st.doStop();
	}
}

class StopingThread1 implements Runnable{

	private boolean stop = false;
	
	public synchronized void doStop() {
		this.stop = true;
	}
	
	private synchronized boolean keepRunning() {
		return this.stop == false;
	}
	
	@Override
	public void run() {
		System.out.print("Running ...");
		while(keepRunning()) {
			System.out.println("...");
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.print("stopping ...");
	}
}