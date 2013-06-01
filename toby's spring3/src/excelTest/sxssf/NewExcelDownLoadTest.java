package excelTest.sxssf;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class NewExcelDownLoadTest {
	 public static void main(String[] args) throws Throwable {
		 	long StartTime = System.currentTimeMillis();
		 	System.out.println("Start StartTime : "+StartTime);
		 	
		 	Workbook wb = new SXSSFWorkbook(1000); // keep 100 rows in memory, exceeding rows will be flushed to disk
	        Sheet sh = wb.createSheet();
	        for(int rownum = 0; rownum < 30; rownum++){
	            Row row = sh.createRow(rownum);
	            for(int cellnum = 0; cellnum < 40; cellnum++){
	                Cell cell = row.createCell(cellnum);
	                String address = new CellReference(cell).formatAsString();
	                cell.setCellValue(address);
	            }
	        }

//	        // Rows with rownum < 900 are flushed and not accessible
//	        for(int rownum = 0; rownum < 900; rownum++){
//	          Assert.assertNull(sh.getRow(rownum));
//	        }
//
//	        // ther last 100 rows are still in memory
//	        for(int rownum = 900; rownum < 1000; rownum++){
//	            Assert.assertNotNull(sh.getRow(rownum));
//	        }
//	        
	        long EndTime = System.currentTimeMillis();
	        System.out.println("Execution time is : "+ (new SimpleDateFormat("mm:ss:SSS")).format(new Date(EndTime - StartTime)));
	        
	        System.out.println("Finish EndTime : "+EndTime);
	        System.out.println("Finish!");
	        
	        
	        FileOutputStream out = new FileOutputStream("D:\\111.xlsx");
	        wb.write(out);
	        out.close();
	    }
}
