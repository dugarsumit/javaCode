package tutorials;

import java.util.concurrent.Callable;

public class CallableWorker implements Callable<String>{
	
	private int id;

	CallableWorker(int id){
		this.id=id;
	}
	
	@Override
	public String call() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(id).append("-").append(Thread.currentThread().getName());
		Thread.currentThread().sleep(100);
		return sb.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
