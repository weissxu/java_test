
class SuperClass {
	private void interestingMethod(){
		System.out.println("super interestingMethod invoked!!");
	}
	void exampleMethod(){
		interestingMethod();
	}
}

class SubClass extends SuperClass{
	void interestingMethod(){
		System.out.println("sub interestingMethod invoked!!");
	}
	
	public static void main(String[] args) {
		SubClass sub=new SubClass();
		sub.exampleMethod();
	}
}
