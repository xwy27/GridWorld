import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class MyTest {
	HelloWorld hello;

	@Before
	public void initial() {
		hello = new HelloWorld();
	}

	@After
	public void release() {}

	@Test
	public void testHello() {
		assertEquals("Hello World!\n", hello.Hello());
	}
}
