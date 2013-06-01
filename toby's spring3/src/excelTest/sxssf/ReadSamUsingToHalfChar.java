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

		String text = "LG_전자";
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
     * 반각문자로 변경한다
     * @param src 변경할값
     * @return String 변경된값
     */
    private static String toHalfChar(String src)
    {
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //영문이거나 특수 문자 일경우.
            if (c >= '！' && c <= '～')
            {
                c -= 0xfee0;
            }
            else if (c == '　')
            {
                c = 0x20;
            }
            // 문자열 버퍼에 변환된 문자를 쌓는다
            strBuf.append(c);
        }
        return strBuf.toString();
    }    


    /**
     * 전각문자로 변경한다.
     * @param src 변경할값
     * @return String 변경된값
     */
    private static String toFullChar(String src)
    {
        // 입력된 스트링이 null 이면 null 을 리턴
        if (src == null)
            return null;
        // 변환된 문자들을 쌓아놓을 StringBuffer 를 마련한다
        StringBuffer strBuf = new StringBuffer();
        char c = 0;
        int nSrcLength = src.length();
        for (int i = 0; i < nSrcLength; i++)
        {
            c = src.charAt(i);
            //영문이거나 특수 문자 일경우.
            if (c >= 0x21 && c <= 0x7e)
            {
                c += 0xfee0;
            }
            //공백일경우
            else if (c == 0x20)
            {
                c = 0x3000;
            }
            // 문자열 버퍼에 변환된 문자를 쌓는다
            strBuf.append(c);
        }
        return strBuf.toString();
    }

}
