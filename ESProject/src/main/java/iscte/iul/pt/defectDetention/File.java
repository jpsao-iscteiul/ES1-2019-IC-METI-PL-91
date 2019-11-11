package iscte.iul.pt.defectDetention;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class File {



	public static int sheetIndex;



	public void readExcel(String fileLocation) throws IOException {

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

				System.out.println(className + "     " + methodName + "     " + LOC + "     " + CYCLO + "     "
						+ ATFD + "     " + LAA + "     " + is_long_method + "     " + iPlasma + "     " + PMD + "     "
						+ is_feature_envy + "\n");

			}


		}
	}



}
