package adp.shoping.counter;

import adp.shoping.counter.model.Shop;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CounterApplicationTests {
	@Test
	public void makeShopeReadyTest() {
		Shop s = new Shop("", "");
		Assert.assertNotNull(s);
		Assert.assertEquals("Jeans", s.searchItem("A001").getName());
	}
}
