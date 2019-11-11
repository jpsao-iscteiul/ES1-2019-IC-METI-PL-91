package ES1_2019_IC_METI_PL_91.ESProject;

import java.io.IOException;

import iscte.iul.pt.defectDetention.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) 
    {
        System.out.println( "Hello World!" );
        System.out.println( "Hello World!" );
        
        File file = new File();
        
        /*
         * TODO : O caminho do ficheiro deve ser substituido pelo import do 
         * ficheiro que vem do gui.
         */
        try {
			file.readExcel("/home/andre/Desktop/Long-Method.xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
		
		}
    }
}
