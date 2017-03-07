package tutorials;

import java.util.Deque;
import java.util.concurrent.BlockingDeque;

public class Consumer implements Runnable{

	private static int MAX_SIZE = 5;
	private static Deque<Integer> queue = null;
	private static BlockingDeque<Integer> bQueue = null;
	
	public Consumer(Deque<Integer> queue) {
		this.queue = queue;
	}
	
	public Consumer(BlockingDeque<Integer> bQueue) {
		this.bQueue = bQueue;
	}
	
	@Override
	public void run() {
		System.out.println("Entering Consumer..");
		consumerImplUsingWaitNotify();
		//consumerImplUsingBlockingQueue();
	}
	
	private void consumerImplUsingBlockingQueue(){
		int i = 0;
		while(true){
			try {
				System.out.println("Consuming " + bQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void consumerImplUsingWaitNotify() {
		synchronized (queue) {
			while (true) {
				if (queue.size() == 0) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Consuming " + queue.poll());
				queue.notifyAll();
			}
		}
	}

}
