package tutorials;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MultiThreading {
	
	private static ExecutorService executor;
	
	public static void main(String[] args) {
		//testRunnable();
		//testCallable();
		//producerConsumerUsingWaitNotify();
		//producerConsumerUsingBlockingQueue();
		testThreadLocal();
	}
	
	public static void printThreadLocalVariable(){
		System.out.println("THREAD "+ Thread.currentThread().getName()+" "+RunnableWorker.getRequestid().get());
	}
	
	public static void testThreadLocal(){
		RunnableWorker work = new RunnableWorker(0);
		Thread t1 = new Thread(work);
		Thread t2 = new Thread(work);
		t1.setName("t1");
		t2.setName("t2");
		t1.start();
		t2.start();
	}
	
	public static void producerConsumerUsingBlockingQueue(){
		BlockingDeque<Integer> bqueue = new LinkedBlockingDeque<Integer>(5);
		new Thread(new Producer(bqueue)).start();
		new Thread(new Consumer(bqueue)).start();
	}
	
	public static void producerConsumerUsingWaitNotify(){
		Deque<Integer> queue = new ArrayDeque<Integer>();
		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();
	}
	
	public static void testCallable(){
		executor = getFixedThreadPool();
		List<Future<String>> listOfFuture = new ArrayList<Future<String>>();
		for(int i=0;i<100;i++){
			Future<String> future = executor.submit(new CallableWorker(i));
			listOfFuture.add(future);
		}
		System.out.println("Parent Thread Name : "+Thread.currentThread().getName());
		for(Future<String> f : listOfFuture){
			try {
				System.out.println(new Date()+" "+f.get(90, TimeUnit.MILLISECONDS));
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				System.out.println("Exception-"+e.getCause());
			}
		}
		System.out.println("Parent Thread Name : "+Thread.currentThread().getName());
		executor.shutdown();
	}
	
	public static void testRunnable(){
		executor = getFixedThreadPool();
		for(int i=0;i<10;i++){
			executor.execute(new RunnableWorker(i));
		}
		System.out.println("Parent Thread Name : "+Thread.currentThread().getName());
		executor.shutdown();
	}
	
	public static ExecutorService getSingleThreadExecutor(){
		return Executors.newSingleThreadExecutor();
	}
	
	public static ExecutorService getFixedThreadPool(){
		return Executors.newFixedThreadPool(2);
	}
}
