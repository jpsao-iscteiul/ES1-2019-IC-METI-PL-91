package iscte.iul.pt.defectDetention;

import static org.junit.Assert.*;

import org.junit.Test;

public class RuleTest {
	
	private String metric;
	private String symbol;
	private double threshold;
	private String ruleDescription="Rule1";
	private String defects;
	Rule r = new Rule("LAA", ">", 0.3, "defect");

	@Test
	public final void testRule() {
	
	}

	@Test
	public final void testGetMetric() {
		
		r.setMetric("LAA");
		assertEquals("LAA",r.getMetric());
	}

	@Test
	public final void testSetMetric() {
	
	}

	@Test
	public final void testGetSymbol() {
		r.setSymbol(">");
		assertEquals(">", r.getSymbol());
	}

	@Test
	public final void testSetSymbol() {
	
	}

	@Test
	public final void testGetThreshold() {
		r.setThreshold(0.3);
		assertEquals(0.3, r.getThreshold(), 0.01);
	
	}

	@Test
	public final void testSetThreshold() {

	}

	@Test
	public final void testGetRuleDescription() {
		r.setRuleDescription("Rule1");
		assertEquals("Rule1", r.getRuleDescription());
	}

	@Test
	public final void testSetRuleDescription() {
		
	}

	@Test
	public final void testGetDefects() {
		r.setDefects("defect");
		assertEquals("defect", r.getDefects());
	}

	@Test
	public final void testSetDefects() {
	
	}

}
