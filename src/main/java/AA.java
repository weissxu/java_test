//a test comment!
public class AA {
	private int id;
	public String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int incrementId(){
		try {
			return id;
		} finally{
			System.out.println("hello,world!!");
		}
	}
	
	@SuppressWarnings("finally")
	public static void main(String[] args) {
		while(true){
			try {
				Thread.sleep(1000);
				System.out.println("hello,world!!");
				return;
			} finally {
				System.out.println("finally statement!!");
				break;
//				continue;
			}
//			System.out.println("after statement!!");
		}
	}
}
