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
		
		//for������ �Ѷ��ξ� wirte�Ѵ�. �� ������ delimeter�� ���е� ���� ����.
		String text = "It's testing now!";
		bw.write(text);
		bw.newLine();
		bw.write(text);
		
		bw.close();

	}
}