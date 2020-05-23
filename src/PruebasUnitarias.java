import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class PruebasUnitarias {

	private Controller c = new Controller();
	
	@Test
	public void test1() {
		c.grafoTXT();
		c.crearGrafo();
		assertEquals("[santoDomingo, sumpango, jocotenango, antigua, ciudadVieja]", Arrays.toString(c.getRuta(0, 10)));
	}
	
	@Test
	public void test2() {
		c.grafoTXT();
		c.crearGrafo();
		assertEquals("santiago", c.getCenter());
	}
	
	@Test
	public void test3() {
		c.grafoTXT();
		c.crearGrafo();
		assertEquals("[santoDomingo, sumpango, jocotenango, antigua, ciudadVieja]", Arrays.toString(c.getRuta(0, 10)));
		assertEquals("34km",c.getDistance(c.getRuta(0, 10)));
	}
	
	

}
