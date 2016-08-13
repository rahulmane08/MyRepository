package synchronizers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Task implements Callable<String>
{

	@Override
	public String call() throws Exception {
		String currentThread = Thread.currentThread().getName();
		System.out.println(currentThread+" starts");
		Thread.sleep(20*1000);		
		return "task by "+currentThread;
	}
	
}
public class FutureTaskTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Task t = new Task();
		FutureTask<String> future1 = new FutureTask<String>(t);
		FutureTask<String> future2 = new FutureTask<String>(t);
		FutureTask<String> future3 = new FutureTask<String>(t);
		
		Thread t1 = new Thread(future1);
		Thread t2 = new Thread(future2);
		Thread t3 = new Thread(future3);
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println(future1.get());
		System.out.println(future2.get());
		System.out.println(future3.get());
	}
}
