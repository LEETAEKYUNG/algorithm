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
			//���ڿ��� �������� �پ��ִ� ���ڸ� �����Ѵ�.
		    n = str.replaceFirst("^.*\\D","");
		    System.out.println("n:"+n);
		    str = str.substring(0, str.lastIndexOf(n));
		    System.out.println("n : "+n);
		    System.out.print("str : "+str);
		} catch (Exception e) {
			
		}
	}

}
