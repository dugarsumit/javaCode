package tutorials;

import java.util.Deque;
import java.util.concurrent.BlockingDeque;

public class Producer implements Runnable {

	private static int MAX_SIZE = 5;
	private static Deque<Integer> queue = null;
	private static BlockingDeque<Integer> bQueue = null;
	
	public Producer(Deque<Integer> queue) {
		this.queue = queue;
	}
	
	public Producer(BlockingDeque<Integer> bQueue) {
		this.bQueue = bQueue;
	}
	
	@Override
	public void run() {
		System.out.println("Entering Producer..");
		producerImplUsingWaitNotify();
		//producerImplUsingBlockingQueue();
	}

	private void producerImplUsingBlockingQueue(){
		int i = 0;
		while(true){
			i++;
			System.out.println("Producing " + i);
			bQueue.offer(i);
		}
	}
	
	private void producerImplUsingWaitNotify() {
		int i = 0;
		synchronized (queue) {
			while (true) {
				if (queue.size() == MAX_SIZE) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i++;
				System.out.println("Producing " + i);
				queue.offer(i);
				queue.notifyAll();
			}
		}
	}
}
