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
			System.out.println("Original String : "+str);
			n = str.replaceFirst("^.*\\D","");
		    str = str.substring(0, str.lastIndexOf(n));
		    System.out.println("Extract seq : "+n);
		    System.out.print("Cleansing String : "+str);
		} catch (Exception e) {
			
		}
	}

}
