package excelTest.sxssf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadSamUsingToHalfChar {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String text = "LG_����";
		String strLine = "";
		String [] strArray = null;

		File f = new File("D:\\CmmCd.txt");
		BufferedReader bin = new BufferedReader(new FileReader(f));
		
		while((strLine=bin.readLine()) != null){
			strArray = strLine.split("!",-1);
			
			//dao.insertTestCmmCdVal(strArray);
			for(int i = 0 ; i < strArray.length-1 ; i++){
				System.out.println("strArray["+i+"]:"+toHalfChar(strArray[i]));
			}
		}

		text = toFullChar(text);
		System.out.println("to text:"+text);

		text = toHalfChar(text);
		System.out.println("to text:"+text);
	}
	
	/**
     * �ݰ����ڷ� �����Ѵ�
     * @param src �����Ұ�
     * @return String ����Ȱ�
     */
    private static String toHalfChar(String src)
    {
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //�����̰ų� Ư�� ���� �ϰ��.
            if (c >= '��' && c <= '��')
            {
                c -= 0xfee0;
            }
            else if (c == '��')
            {
                c = 0x20;
            }
            // ���ڿ� ���ۿ� ��ȯ�� ���ڸ� �״´�
            strBuf.append(c);
        }
        return strBuf.toString();
    }    


    /**
     * �������ڷ� �����Ѵ�.
     * @param src �����Ұ�
     * @return String ����Ȱ�
     */
    private static String toFullChar(String src)
    {
        // �Էµ� ��Ʈ���� null �̸� null �� ����
        if (src == null)
            return null;
        // ��ȯ�� ���ڵ��� �׾Ƴ��� StringBuffer �� �����Ѵ�
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //�����̰ų� Ư�� ���� �ϰ��.
            if (c >= 0x21 && c <= 0x7e)
            {
                c += 0xfee0;
            }
            //�����ϰ��
            else if (c == 0x20)
            {
                c = 0x3000;
            }
            // ���ڿ� ���ۿ� ��ȯ�� ���ڸ� �״´�
            strBuf.append(c);
        }
        return strBuf.toString();
    }

}
