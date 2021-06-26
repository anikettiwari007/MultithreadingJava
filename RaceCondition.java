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