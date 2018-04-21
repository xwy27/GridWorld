public class HelloWorld {
	public String Hello() {
		return "Hello World!\n";
	}

	public static void main(String[] args) {
		HelloWorld temp = new HelloWorld();
		System.out.println(temp.Hello());
	}
}
