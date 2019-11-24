package iscte.iul.pt.defectDetention;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class File {



	public static int sheetIndex;
	private double loc_threshold = 0;
	private double cyclo_threshold = 0;
	private double atfd_threshold = 0;
	private double laa_threshold = 0;
	private int numberOfHits = 0;
	private int numberOfMethods = 0;



	public void readExcel(String fileLocation, List<Rule> ruleList) throws IOException {

		FileInputStream excelFilePath = new FileInputStream(fileLocation);
		XSSFWorkbook workbook = new XSSFWorkbook(excelFilePath);

		Sheet sheet = workbook.getSheetAt(sheetIndex);


		for(Row row : sheet) {		

			/* TODO : Otimizar a leitura do excel
			for(Cell cell : row) {
				if(cell.getCachedFormulaResultTypeEnum().STRING.equals("String")) {
					System.out.println("okkkkkkkkkkkkkkkk");
				}
			}
			 */

			if(row.getRowNum()>sheet.getFirstRowNum() && row.getRowNum() <= sheet.getLastRowNum()) {
				int rowStartIndex = 2; //Porque as colunas do sheet iniciam em C
				String className = row.getCell(rowStartIndex).getStringCellValue();
				rowStartIndex++;

				String methodName = row.getCell(rowStartIndex).getStringCellValue();
				rowStartIndex++;

				double LOC =  row.getCell(rowStartIndex).getNumericCellValue();
				rowStartIndex++;

				double CYCLO =  row.getCell(rowStartIndex).getNumericCellValue();
				rowStartIndex++;

				double ATFD =  row.getCell(rowStartIndex).getNumericCellValue();
				rowStartIndex++;

				double LAA = 0; 

				//				double LAA = row.getCell(rowStartIndex).getNumericCellValue(); //TODO : Tenho erro nesta Linha
				rowStartIndex++;

				boolean is_long_method = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				boolean iPlasma = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				boolean PMD = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				boolean is_feature_envy = row.getCell(rowStartIndex).getBooleanCellValue();
				rowStartIndex++;

				//				System.out.println(className + "     " + methodName + "     " + LOC + "     " + CYCLO + "     "
				//						+ ATFD + "     " + LAA + "     " + is_long_method + "     " + iPlasma + "     " + PMD + "     "
				//						+ is_feature_envy + "\n");

//				showIsLongMethodsResults(ruleList, LOC, CYCLO, is_long_method);
				showIsFeatureEnvyResults(ruleList, ATFD, LAA, is_feature_envy);
				
				this.numberOfMethods ++;
			}
		}
		
		/*
		System.out.println("\n");
		System.out.println("Aplicação para avaliação daqualidade de deteção de defeitos IS LONG METHOD em projetos de software" + "\n");
		System.out.println("Para um LOC thresold de " + this.loc_threshold + " e" + " CYCLO thresold de " + this.cyclo_threshold  + " o número de acertos para is_long_method é de " + this.numberOfHits/2 + " num total de " + this.numberOfMethods  + " Métodos.");
	*/
		
		System.out.println("\n");
		System.out.println("Aplicação para avaliação daqualidade de deteção de defeitos IS FEATURE ENVY em projetos de software" + "\n");
		System.out.println("Para um ATFD thresold de " + this.atfd_threshold + " e" + " LAA thresold de " + this.laa_threshold  + " o número de acertos para is_feature_envy é de " + this.numberOfHits/2 + " num total de " + this.numberOfMethods  + " Métodos.");

	
	
	}




	public void showIsLongMethodsResults(List<Rule> ruleList, double loc, double cyclo, boolean isLongMethod) {

		for(Rule rule : ruleList) {
			if(rule.getSymbol().equals(">")) {
				if((loc > this.loc_threshold || cyclo > this.cyclo_threshold) && isLongMethod == true) {
					this.numberOfHits ++;
				}
			}
			
			if(rule.getSymbol().equals("<")) {
				if((loc < this.loc_threshold || cyclo < this.cyclo_threshold) && isLongMethod == true) {
					this.numberOfHits ++;
				}
			}
		}
	}
	
	
	
	public void showIsFeatureEnvyResults(List<Rule> ruleList, double atfd, double laa, boolean isFeatureEnvy) {

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



	public boolean isLongMethod(List<Rule> ruleList) {

		boolean result = false;

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

		return result;
	}


	
	public boolean isFeatureEnvy(List<Rule> ruleList) {

		boolean result = false;

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

		return result;
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










}
