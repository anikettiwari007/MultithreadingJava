import java.util.Map;

public class RaceCondition {

	public static void main(String[] args) {
		
	}
}

class RaceCondition1{
	/* 
	 * read-modify-write condition*/
	/*
	 * Read this.count from memory into register. 
	 * Add value to register. Write
	 * register to memory.
	 * 
	 * 
	 */
	protected long count = 0;
	
	public void add(long value) {
		this.count = this.count+value;
	}
	
	
	/* 
	 * check-then-act 
	 * 
	 * */
	public void checkThenAct(Map<String, String> sharedMap) {
		if(sharedMap.containsKey("key")) {
			String val = sharedMap.remove("key");
			if(val==null) {
				System.out.println("value for 'key' was null.");
			}
		}
		else {
			sharedMap.put("key", "value");
		}
	}
}

/* 
 * now how to achieve synchronization between threads 
 * 
 * we can change the code like below
 * 
 * */
class RaceCondition2{
	protected long count;
	
	public void add(long value) {
		synchronized (this) {
			this.count = this.count+value;
		}		
	}
}

/*
 * the two sum variables are independent of each other, you could split their
 * summing up into two separate synchronized blocks.
 * 
 * two different threads can execute the two blocks independently. 
 * This way threads will have to wait less for each other to execute the add() method.
 */
class TwoSum{
	protected long val1=0;
	protected long val2=0;
	
	private Long lockForVal1 = Long.valueOf(1);
	private Long lockForVal2 = Long.valueOf(2);
	
	public void add(long value) {
		synchronized (lockForVal1) {
			this.val1 = this.val1+value;
		}
		synchronized (lockForVal2) {
			this.val2 = this.val2+value;
		}
	}
}