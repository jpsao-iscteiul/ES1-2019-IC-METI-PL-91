package iscte.iul.pt.defectDetention;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class File {



	private static int sheetIndex;
	private double loc_threshold = 0;
	private double cyclo_threshold = 0;
	private double atfd_threshold = 0;
	private double laa_threshold = 0;
	private int numberOfHits = 0;
	private int numberOfMethods = 0;
	private int numbersOfDCI = 0;
	private int numbersOfDII = 0;
	private int numbersOfADCI = 0;
	private int numbersOfADII = 0;
	private String outPutResult = new String();



	public File() {

	}
	public String readExcel(String fileLocation, List<Rule> ruleList, String defString) throws IOException {

		FileInputStream excelFilePath = new FileInputStream(fileLocation);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFilePath);

		Sheet sheet = workbook.getSheetAt(sheetIndex);


		for(Row row : sheet) {	

			if(row.getRowNum()>sheet.getFirstRowNum() && row.getRowNum() <= sheet.getLastRowNum()) {
				int rowStartIndex = 2; //Porque as colunas do sheet iniciam em C
				String className = row.getCell(rowStartIndex).getStringCellValue();
				rowStartIndex++;

				String methodName = row.getCell(rowStartIndex).getStringCellValue();
				rowStartIndex++;

				double loc =  row.getCell(rowStartIndex).getNumericCellValue();
				rowStartIndex++;

				double cyclo =  row.getCell(rowStartIndex).getNumericCellValue();
				rowStartIndex++;

				double atfd =  row.getCell(rowStartIndex).getNumericCellValue();
				rowStartIndex++;

				double laa = 0; 
				//TODO : Tenho erro nesta Linha

				//				double laa = row.getCell(rowStartIndex).getNumericCellValue(); 
				rowStartIndex++;

				boolean isLongMethod = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				boolean iPlasma = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				boolean pmd = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				boolean isFeatureEnvy = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				showIsLongMethodsResults(ruleList, loc, cyclo, isLongMethod, defString);
				showIsFeatureEnvyResults(ruleList, atfd, laa, isFeatureEnvy, defString);

				this.numberOfMethods ++;
				defectDetentionQuality(pmd, iPlasma, isLongMethod, isFeatureEnvy, defString);
			}
		}
		showIsFeatureEnvyMethodResults(defString,ruleList);
		showIsLongMethodResults(defString,ruleList);
		return this.outPutResult;
	}




	public void showIsFeatureEnvyMethodResults(String defect, List<Rule> ruleList) {

		if(defect.equals("Feature Envy")) {

			/*
			System.out.println("\n");
			System.out.println("Aplicação para avaliação daqualidade de deteção de defeitos IS FEATURE ENVY em projetos de software" + "\n");
			System.out.println("Para um ATFD thresold de " + this.atfd_threshold + " e" + " LAA thresold de " + this.laa_threshold  + " o número de acertos para is_feature_envy é de " + this.numberOfHits/2 + " num total de " + this.numberOfMethods  + " Métodos.");


			System.out.println("Defeitos Corretamente Identificados: " + this.numbersOfDCI);

			System.out.println("Defeitos Icorretamente Identificados: " + this.numbersOfDII);

			System.out.println("Ausências Defeitos Corretamente Identificados: " + this.numbersOfADCI);

			System.out.println("Ausências Defeitos Incorretamente Identificados: " + this.numbersOfADII);
			 */

			this.outPutResult = "Aplicação para avaliação daqualidade de deteção de defeitos IS FEATURE ENVY em projetos de software" + "\n" + "Para um ATFD thresold de " + this.atfd_threshold + " e" + " LAA thresold de " + this.laa_threshold  + " o número de acertos para is_feature_envy é de " + this.numberOfHits/2 + " num total de " + this.numberOfMethods  + " Métodos." + "\n"
					+ "Defeitos Corretamente Identificados: " + this.numbersOfDCI + "\n" + "Defeitos Icorretamente Identificados: " + this.numbersOfDII + "\n"
					+ "Ausências Defeitos Corretamente Identificados: " + this.numbersOfADCI + "\n" + "Ausências Defeitos Incorretamente Identificados: " + this.numbersOfADII + "\n";			
		}
	}


	public void showIsLongMethodResults(String defect, List<Rule> ruleList) {
		if(defect.equals("Long Method")) {
			/*
			System.out.println("\n");
			System.out.println("Aplicação para avaliação daqualidade de deteção de defeitos IS LONG METHOD em projetos de software" + "\n");
			System.out.println("Para um LOC thresold de " + this.loc_threshold + " e" + " CYCLO thresold de " + this.cyclo_threshold  + " o número de acertos para is_long_method é de " + this.numberOfHits/2 + " num total de " + this.numberOfMethods  + " Métodos.");

			System.out.println("Defeitos Corretamente Identificados: " + this.numbersOfDCI);

			System.out.println("Defeitos Icorretamente Identificados: " + this.numbersOfDII);

			System.out.println("Ausências Defeitos Corretamente Identificados: " + this.numbersOfADCI);

			System.out.println("Ausências Defeitos Incorretamente Identificados: " + this.numbersOfADII);
			 */
			this.outPutResult = "Aplicação para avaliação daqualidade de deteção de defeitos IS LONG METHOD em projetos de software" + "\n" + "Para um LOC thresold de " + this.loc_threshold + " e" + " CYCLO thresold de " + this.cyclo_threshold  + " o número de acertos para is_long_method é de " + this.numberOfHits/2 + " num total de " + this.numberOfMethods  + " Métodos." + "\n"
					+ "Defeitos Corretamente Identificados: " + this.numbersOfDCI + "\n" + "Defeitos Icorretamente Identificados: " + this.numbersOfDII + "\n"
					+ "Ausências Defeitos Corretamente Identificados: " + this.numbersOfADCI + "\n" + "Ausências Defeitos Incorretamente Identificados: " + this.numbersOfADII + "\n";

		}
	}



	public void showIsLongMethodsResults(List<Rule> ruleList, double loc, double cyclo, boolean isLongMethod, String defect) {

		if(defect.equals("Long Method")) {
			for(Rule rule : ruleList) {
				if(rule.getSymbol().equals(">")) {
					//				if((loc > this.loc_threshold || cyclo > this.cyclo_threshold) && isLongMethod == true) {
					if((loc > this.loc_threshold && cyclo > this.cyclo_threshold)) {
						this.numberOfHits ++;
					}
				}

				if(rule.getSymbol().equals("<")) {
					//				if((loc < this.loc_threshold || cyclo < this.cyclo_threshold) && isLongMethod == true) {
					if((loc < this.loc_threshold && cyclo < this.cyclo_threshold)) {
						this.numberOfHits ++;
					}
				}
			}
		}
	}



	public void showIsFeatureEnvyResults(List<Rule> ruleList, double atfd, double laa, boolean isFeatureEnvy, String defect) {

		if(defect.equals("Feature Envy")) {
			for(Rule rule : ruleList) {
				if(rule.getSymbol().equals(">")) {
					if((atfd > this.atfd_threshold || laa > this.laa_threshold) && isFeatureEnvy == true) {
						this.numberOfHits ++;
					}
				}

				if(rule.getSymbol().equals("<")) {
					if((atfd < this.atfd_threshold || laa < this.laa_threshold) && isFeatureEnvy == true) {
						this.numberOfHits ++;
					}
				}
			}
		}
	}



	public boolean isLongMethod(List<Rule> ruleList) {

		boolean result = false;

		if(ruleList.size() > 0 && ruleList != null) {
			for(Rule rule : ruleList) {

				if(rule.getMetric().equals("LOC") && rule.getDefects().equals("Long Method")) {
					this.loc_threshold = rule.getThreshold();
					result = true;
				}

				if(rule.getMetric().equals("CYCLO") && rule.getDefects().equals("Long Method")) {
					this.cyclo_threshold = rule.getThreshold();
					result = true;
				}
			}
		}

		return result;
	}

	public boolean isFeatureEnvy(List<Rule> ruleList) {

		boolean result = false;

		if(ruleList.size() > 0 && ruleList != null) {

			for(Rule rule : ruleList) {

				if(rule.getMetric().equals("ATFD") && rule.getDefects().equals("Feature Envy")) {
					this.atfd_threshold = rule.getThreshold();
					result = true;
				}

				if(rule.getMetric().equals("LAA") && rule.getDefects().equals("Feature Envy")) {
					this.laa_threshold = rule.getThreshold();
					result = true;
				}
			}
		}


		return result;
	}

	public void defectDetentionQuality(boolean pmd, boolean iPlasma, boolean isLongMethod, boolean isFeatureEnvy, String defect) {

		if(defect.equals("Long Method")) {
			showDCIResults(pmd, iPlasma, isLongMethod);
			showDIIResults(pmd, iPlasma, isLongMethod);
			showADCIResults(pmd, iPlasma, isLongMethod);
			showADIIResults(pmd, iPlasma, isLongMethod);

		}else if(defect.equals("Feature Envy")) {
			showDCIResultsFeatureEnvy(pmd, iPlasma, isFeatureEnvy);
			showDIIResultsFeatureEnvy(pmd, iPlasma, isFeatureEnvy);
			showADCIResultsFeatureEnvy(pmd, iPlasma, isFeatureEnvy);
			showADIIResultsFeatureEnvy(pmd, iPlasma, isFeatureEnvy);
		}
	}




	public void showDCIResults(boolean pmd, boolean iPlasma, boolean isLongMethod) {

		if((pmd==true || iPlasma ==true) && isLongMethod==true) {
			this.numbersOfDCI++;
		}
	}


	public void showDCIResultsFeatureEnvy(boolean pmd, boolean iPlasma, boolean isFeatureEnvy) {

		if((pmd==true || iPlasma ==true) && isFeatureEnvy==true) {
			this.numbersOfDCI++;
		}
	}




	public void showDIIResults(boolean pmd, boolean iPlasma, boolean isLongMethod) {

		if((pmd==true || iPlasma ==true) && isLongMethod==false) {
			this.numbersOfDII++;
		}
	}


	public void showDIIResultsFeatureEnvy(boolean pmd, boolean iPlasma, boolean isFeatureEnvy) {

		if((pmd==true || iPlasma ==true) && isFeatureEnvy==false) {
			this.numbersOfDII++;
		}
	}

	public void showADCIResults(boolean pmd, boolean iPlasma, boolean isLongMethod) {

		if((pmd==false || iPlasma ==false) && isLongMethod==false) {
			this.numbersOfADCI++;
		}
	}

	public void showADCIResultsFeatureEnvy(boolean pmd, boolean iPlasma, boolean isFeatureEnvy) {

		if((pmd==false || iPlasma ==false) && isFeatureEnvy==false) {
			this.numbersOfADCI++;
		}
	}

	public void showADIIResults(boolean pmd, boolean iPlasma, boolean isLongMethod) {

		if((pmd==false || iPlasma ==false) && isLongMethod==true) {
			this.numbersOfADII++;
		}
	}

	public void showADIIResultsFeatureEnvy(boolean pmd, boolean iPlasma, boolean isFeatureEnvy) {

		if((pmd==false || iPlasma ==false) && isFeatureEnvy==true) {
			this.numbersOfADII++;
		}
	}



	public static int getSheetIndex() {
		return sheetIndex;
	}




	public static void setSheetIndex(int sheetIndex) {
		File.sheetIndex = sheetIndex;
	}




	public double getLoc_threshold() {
		return loc_threshold;
	}




	public void setLoc_threshold(double loc_threshold) {
		this.loc_threshold = loc_threshold;
	}




	public double getCyclo_threshold() {
		return cyclo_threshold;
	}




	public void setCyclo_threshold(double cyclo_threshold) {
		this.cyclo_threshold = cyclo_threshold;
	}




	public int getNumberOfHits() {
		return numberOfHits;
	}




	public void setNumberOfHits(int numberOfHits) {
		this.numberOfHits = numberOfHits;
	}




	public int getNumberOfMethods() {
		return numberOfMethods;
	}




	public void setNumberOfMethods(int numberOfMethods) {
		this.numberOfMethods = numberOfMethods;
	}




	public double getAtfd_threshold() {
		return atfd_threshold;
	}




	public void setAtfd_threshold(double atfd_threshold) {
		this.atfd_threshold = atfd_threshold;
	}




	public double getLaa_threshold() {
		return laa_threshold;
	}




	public void setLaa_threshold(double laa_threshold) {
		this.laa_threshold = laa_threshold;
	}




	public int getNumbersOfDCI() {
		return numbersOfDCI;
	}




	public void setNumbersOfDCI(int numbersOfDCI) {
		this.numbersOfDCI = numbersOfDCI;
	}




	public int getNumbersOfDII() {
		return numbersOfDII;
	}




	public void setNumbersOfDII(int numbersOfDII) {
		this.numbersOfDII = numbersOfDII;
	}




	public int getNumbersOfADCI() {
		return numbersOfADCI;
	}




	public void setNumbersOfADCI(int numbersOfADCI) {
		this.numbersOfADCI = numbersOfADCI;
	}




	public int getNumbersOfADII() {
		return numbersOfADII;
	}




	public void setNumbersOfADII(int numbersOfADII) {
		this.numbersOfADII = numbersOfADII;
	}










}
