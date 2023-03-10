import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class TestBinarySearchLibrary {

	List<String> myList;
	
	@BeforeEach
	public void setup () {
		String[] ss = {
				"apple","apple","apple","apple",
				"cherry","cherry","cherry","cherry","cherry","cherry",
				"orange",
				"zoo","zoo","zoo","zoo","zoo","zoo","zoo",
				"zoo","zoo","zoo","zoo","zoo"};
		
		myList = Arrays.asList(ss);
	}

	@Test
	public void testFirstIndex() {
		String[] keys = {"apple","cherry","lemon","orange","zoo"};
		int[] results = {0,4,-1,10,11};
		for(int k=0; k < keys.length; k++) {
			String target = keys[k];
			int index = BinarySearchLibrary.firstIndex(myList, target, Comparator.naturalOrder());
			if (index < 0) {
			}
			else {
				assertEquals(results[k], index, "wrong first index for " + target);
			}				assertEquals(Math.signum(results[k]), Math.signum(index), "wrong first index for " + target);

		}
	}
	
	@Test
	public void testLastIndex() {
		String[] keys = {"apple","cherry","lemon","orange","zoo"};
		int[] results = {3,9,-1,10,22};
		for(int k=0; k < keys.length; k++) {
			String target = keys[k];
			int index = BinarySearchLibrary.lastIndex(myList, target, Comparator.naturalOrder());
			assertEquals(results[k],index,"wrong first index for "+target);
		}
	}

}
