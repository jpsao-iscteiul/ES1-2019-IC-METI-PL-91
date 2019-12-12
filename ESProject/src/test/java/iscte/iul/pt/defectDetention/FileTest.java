package iscte.iul.pt.defectDetention;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileTest {
	private int sheetIndex;
	private double loc_threshold=0.2;
	private double cyclo_threshold=0.2;
	@Test
	public final void testReadExcel() {
		
	}


	@Test
	public final void testShowIsLongMethodsResults() {
		double loc=0.1;
		double cyclo=0.1;
		boolean isLongMethod=true;
		File f = new File();
		List<Rule> ruleList = new ArrayList<Rule>();
		 Rule r = new Rule("LAA", ">", 0.3, "defect");
		
		
		 ruleList.add(r);
		 f.showIsLongMethodsResults(ruleList, loc, cyclo, isLongMethod);
		 
		 
		 Rule r2 = new Rule("LAA", "<", 0.2, "defect");
		
		
		 f.setLoc_threshold(50);
		 f.setCyclo_threshold(50);
		 ruleList.add(r2);
		 f.showIsLongMethodsResults(ruleList, loc, cyclo, isLongMethod);
		 
		
		 assertEquals(2,f.getNumberOfHits());
		
	}

	
	
	
	@Test
	public final void testShowIsFeatureEnvyResults() {
		File f = new File();
		List<Rule> ruleList = new ArrayList<Rule>();
		Rule r = new Rule("LAA", ">", 0.3, "defect");
		Rule r1 = new Rule("LAA", "<", 0.3, "defect");
		
		ruleList.add(r);
		ruleList.add(r1);
		f.setAtfd_threshold(0.1);
		f.setLaa_threshold(0.1);
		f.showIsFeatureEnvyResults(ruleList, 5.0, 5.0, true);
		assertEquals(1, f.getNumberOfHits());
		
		f.showIsFeatureEnvyResults(ruleList, 0.0, 0.0, true);
		assertEquals(2, f.getNumberOfHits());
		
		
	}


	@Test
	public final void testIsLongMethod() {
		File f = new File();
		List<Rule> ruleList = new ArrayList<Rule>();
		Rule r = new Rule("LOC", ">", 0, "Long Method");
		Rule r1 = new Rule("CYCLO", ">", 0, "Long Method");
		ruleList.add(r);
		
		assertEquals(true, f.isLongMethod(ruleList));
		
		ruleList.add(r1);
		assertEquals(true, f.isLongMethod(ruleList));
	}
	
	
	@Test
	public final void testIsFeatureEnvy() {
		File f = new File();
		List<Rule> ruleList = new ArrayList<Rule>();
		Rule r = new Rule("ATFD", ">", 0, "Feature Envy");
		Rule r1 = new Rule("LAA", ">", 0, "Feature Envy");
		
		ruleList.add(r);
		
		assertEquals(true, f.isFeatureEnvy(ruleList));
		
		ruleList.add(r1);
		assertEquals(true, f.isFeatureEnvy(ruleList));
	}

	@Test
	public final void testDefectDetentionQuality() {
		
	}

	@Test
	public final void testShowDCIResults() {
		boolean pmd=true;
		boolean iPlasma=true;
		boolean isLongMethod=true;
		File f = new File();
		
		f.defectDetentionQuality(pmd, iPlasma, isLongMethod);
		assertEquals(1, f.getNumbersOfDCI());
	}

	@Test
	public final void testShowDIIResults() {
		boolean pmd=true;
		boolean iPlasma=true;
		boolean isLongMethod=false;
		File f = new File();

		f.defectDetentionQuality(pmd, iPlasma, isLongMethod);
		assertEquals(1, f.getNumbersOfDII());
	}

	@Test
	public final void testShowADCIResults() {
		boolean pmd=false;
		boolean iPlasma=false;
		boolean isLongMethod=false;
		File f = new File();

		f.defectDetentionQuality(pmd, iPlasma, isLongMethod);
		assertEquals(1, f.getNumbersOfADCI());
	}

	@Test
	public final void testShowADIIResults() {
		boolean pmd=false;
		boolean iPlasma=false;
		boolean isLongMethod=true;
		File f = new File();

		f.defectDetentionQuality(pmd, iPlasma, isLongMethod);
		assertEquals(1, f.getNumbersOfADII());
	}

	@Test
	public final void testGetSheetIndex() {
		File.setSheetIndex(1);
		assertEquals(1, File.getSheetIndex());
	}

	@Test
	public final void testSetSheetIndex() {
		
	}

	@Test
	public final void testGetLoc_threshold() {
		File f = new File();
		f.setLoc_threshold(0.1);
		assertEquals(0.1, f.getLoc_threshold(), 0.01);
	}

	@Test
	public final void testSetLoc_threshold() {
	
	}

	@Test
	public final void testGetCyclo_threshold() {
		File f = new File();
		f.setCyclo_threshold(0.2);
		assertEquals(0.2, f.getCyclo_threshold(), 0.01);
	}

	@Test
	public final void testSetCyclo_threshold() {
		
	}

	@Test
	public final void testGetNumberOfHits() {
		File f = new File();
		f.setNumberOfHits(1);
		assertEquals(1, f.getNumberOfHits());
	}

	@Test
	public final void testSetNumberOfHits() {

	}

	@Test
	public final void testGetNumberOfMethods() {
		File f = new File();
		f.setNumberOfMethods(2);
		assertEquals(2, f.getNumberOfMethods());
	}

	@Test
	public final void testSetNumberOfMethods() {

	}

	@Test
	public final void testGetAtfd_threshold() {
		File f = new File();
		f.setAtfd_threshold(0.5);
		assertEquals(0.5, f.getAtfd_threshold(), 0.01);
	}

	@Test
	public final void testSetAtfd_threshold() {

	}

	@Test
	public final void testGetLaa_threshold() {
		File f = new File();
		f.setLaa_threshold(0.9);
		assertEquals(0.9, f.getLaa_threshold(), 0.001);
	}

	@Test
	public final void testSetLaa_threshold() {
	
	}

	@Test
	public final void testGetNumbersOfDCI() {
		File f = new File();
		f.setNumbersOfDCI(5);
		assertEquals(5, f.getNumbersOfDCI());
	}

	@Test
	public final void testSetNumbersOfDCI() {
	
	}

	@Test
	public final void testGetNumbersOfDII() {
		File f = new File();
		f.setNumbersOfDII(4);
		assertEquals(4, f.getNumbersOfDII());
	}

	@Test
	public final void testSetNumbersOfDII() {
	
	}

	@Test
	public final void testGetNumbersOfADCI() {
	File f = new File();
	f.setNumbersOfADCI(10);
	assertEquals(10, f.getNumbersOfADCI());
	}

	@Test
	public final void testSetNumbersOfADCI() {
	
	}

	@Test
	public final void testGetNumbersOfADII() {
		File f = new File();
		f.setNumbersOfADII(8);
		assertEquals(8, f.getNumbersOfADII());
	}

	@Test
	public final void testSetNumbersOfADII() {
		
	}

	
}
