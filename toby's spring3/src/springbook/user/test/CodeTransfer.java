package springbook.user.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CodeTransfer {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		File file = new File("D:\\test.txt");
//		args[0] = "D:\\test.txt";
		File file = new File(args[0]);
		if(!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		//for문으로 한라인씩 wirte한다. 한 라인은 delimeter로 구분된 값이 들어간다.
		String text = "It's testing now!";
		bw.write(text);
		bw.newLine();
		bw.write(text);
		
		bw.close();

	}
}