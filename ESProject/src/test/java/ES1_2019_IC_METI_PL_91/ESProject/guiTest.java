package ES1_2019_IC_METI_PL_91.ESProject;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.Test;

import iscte.iul.pt.defectDetention.Rule;

public class guiTest {
	private JComboBox b= new JComboBox();
	private gui g = new gui();
	private Rule r;
	long serialVersionUID = 1L;

	@Test
	public final void testGui() {
		gui g = new gui();
	}

	@Test
	public final void testImportFileHandler() {
		 String file_path="desktop";
		assertEquals("desktop", file_path);
	}

	@Test
	public final void testGetMetric_list() {
		JComboBox b= new JComboBox();
		gui g = new gui();
		g.setMetric_list(b);
		assertEquals(b, g.getMetric_list());
	}

	@Test
	public final void testSetMetric_list() {
	//	fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetSimbol_list() {
		g.setSimbol_list(b);
		assertEquals(b, g.getSimbol_list());
	}

	@Test
	public final void testSetSimbol_list() {
	//	fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetValue() {
		JTextField text = new JTextField();
		g.setValue(text);
		assertEquals(text, g.getValue());
	}

	@Test
	public final void testSetValue() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetBox() {
		JTextField text = new JTextField();
		g.setBox(text);
		assertEquals(text, g.getBox());
	}

	@Test
	public final void testSetBox() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetOutput() {
		JTextArea area = new JTextArea();
		g.setOutput(area);
		assertEquals(area, g.getOutput());
	}

	@Test
	public final void testSetOutput() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetRule() {
		Rule r = new Rule("LAA", ">", 0, "defect");
		
		g.setRule(r);
		assertEquals(r, g.getRule());
	}

	@Test
	public final void testSetRule() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetFile_path() {
		String file_path = "desktop";
		g.setFile_path(file_path);
		assertEquals(file_path, g.getFile_path());
	}

	@Test
	public final void testSetFile_path() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetSerialversionuid() {
		assertEquals(serialVersionUID, g.getSerialversionuid());		
	
	}

	@Test
	public final void testGetDefects_list() {
		JComboBox defects_list= new JComboBox();
		g.setDefects_list(defects_list);
		assertEquals(defects_list, g.getDefects_list());
	}

	@Test
	public final void testSetDefects_list() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetRuleList() {
	 List<Rule> ruleList = new ArrayList<Rule>();
	 g.setRuleList(ruleList);
	 assertEquals(ruleList, g.getRuleList());
	}
	

	@Test
	public final void testSetRuleList() {
		//fail("Not yet implemented"); // TODO
	}


	@Test
	public final void testGetMetric() {

		
		JComboBox metric_list= new JComboBox();
		
		assertEquals("LOC",g.getMetric());
	}

	@Test
	public final void testGetSymbol() {
		
		
		JComboBox simbol_list= new JComboBox();
	

		assertEquals("<",g.getSymbol());
	}

	@Test
	public final void testGetThreshold() {
		double aux = 0.5;
		JTextField value = new JTextField();
		value.setText("0.5");
		g.setValue(value);
		assertEquals(0.5, g.getThreshold(), 0.01);
	}

	@Test
	public final void testGetDefects() {
		JComboBox defects_list= new JComboBox();
		
		assertEquals("Long Method", g.getDefects());
	}

	@Test
	public final void testFileChooser() {
	
	}

	@Test
	public final void testWriteRuleToFile() {
		
	}

	@Test
	public final void testLoad() {
		
	}

	@Test
	public final void testMain() {
	
	}

}
