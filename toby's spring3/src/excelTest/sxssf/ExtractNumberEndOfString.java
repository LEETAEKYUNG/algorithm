package excelTest.sxssf;

public class ExtractNumberEndOfString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "ME123213TA_ME3213EST_TEST12";
		String n = "";
		
		try {
			//문자열의 마지막에 붙어있는 숫자만 추출한다.
		    n = str.replaceFirst("^.*\\D","");
		    System.out.println("n:"+n);
		    str = str.substring(0, str.lastIndexOf(n));
		    System.out.println("n : "+n);
		    System.out.print("str : "+str);
		} catch (Exception e) {
			
		}
	}

}
