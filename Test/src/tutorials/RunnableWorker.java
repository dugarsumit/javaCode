package tutorials;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnableWorker implements Runnable{
	
	private int id;
	private static final AtomicInteger nextId = new AtomicInteger(0);
	private static final ThreadLocal<String> requestId = new ThreadLocal<String>() {
		@Override
		protected String initialValue() {
			return Integer.toString(nextId.get());
		}
	};

	
	
	public static AtomicInteger getNextid() {
		return nextId;
	}

	public static ThreadLocal<String> getRequestid() {
		return requestId;
	}

	RunnableWorker(int id){
		this.id=id;
	}

	@Override
	public void run() {
		if(Thread.currentThread().getName().equals("t2")){
			requestId.set(Integer.toString(2));
		}else{
			requestId.set(Integer.toString(1));
		}
		MultiThreading.printThreadLocalVariable();
		System.out.println("Request id-"+requestId.get()+" Thread Name : "+Thread.currentThread().getName());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
