package interview;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

public class ObjectsMethods {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	@Test
	public void ObjectTest() {
		log.debug(Objects.equals("a", "a"));
		log.debug(Objects.equals(null, "a"));
		log.debug(Objects.equals("a", null));
		log.debug(Objects.equals(null, null));
		
		log.debug(Objects.hashCode(Objects.equals(null, null)));
		
		String[] a = {"h","e","h","a","n","g"};
		
		Ordering order = null;
		Ordering.natural();
		
		Ordering<String> byLengthOrdering = new Ordering<String>() {
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}
		};

		
		
	}
}
