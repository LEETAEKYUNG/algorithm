package util;

import java.util.ArrayList;
import java.util.List;

public class EscapeCharTest {

	static List<String[]> tagList = new ArrayList<String[]>();
	
	public static void main(String[] args) {
		String singleChar1 = "	<!DOCTYPE >&lt;  df <html> <head> <title > </title> </head> <body> <div id=dkeik > <img id=> </div> </body> </html>";
		
		//line�� �����±׿� �����±׷� �з��Ͽ� �����Ѵ�.
		saveTags(singleChar1);
		//path�� ��´�.
		printPath();
	}
	
	private static void saveTags(String line){
		String newLine = "";
		String[] tagInfo;
		for(int i=0; i<line.length(); i++){
			//<�� ��Ÿ���� �����±����� �����±������� �з��Ͽ� �����Ѵ�.
			if((int)line.charAt(i) == (int)'<'){
				newLine = line.substring(i+1);
				tagInfo = getTagInfo(newLine.substring(0, getIdxOfEndTag(newLine)));
				if(tagInfo == null) continue;
				
				saveTagInfo(tagInfo);
			}
		}
	}
	
	private static String[] getTagInfo(String tag){
		String[] tagInfo = new String[2];
		int idxWhtspc = tag.indexOf((int)' ');
		if(idxWhtspc >= 0)
			tag = tag.substring(0, idxWhtspc); 
		
		int firstChr = (int)tag.charAt(0);
		//����, ����, �� �̿��� �±�(�ּ� ...)
		if((firstChr > (int)'a'  && firstChr > (int)'z') || (firstChr > (int)'A'  && firstChr > (int)'Z')){
			tagInfo[0] = tag;
			tagInfo[1] = "S";
		}
		else if(firstChr == (int)'/'){
			tagInfo[0] = tag.substring(1);
			tagInfo[1] = "E";
		}else{
			return null;
		}
		
		return tagInfo;
	}
	
	private static void saveTagInfo(String[] tagInfo){
		//img, br, input�±״� ������ �����±װ� ����. ���� �����±׸� ���� �־��ش�.
		if(tagInfo[0].toUpperCase().equals("IMG") || tagInfo[0].toUpperCase().equals("BR")
				|| tagInfo[0].toUpperCase().equals("INPUT")){
			tagList.add(tagInfo);
			String[] a = {tagInfo[0],"E"};
			tagList.add(a);
		}else{
			tagList.add(tagInfo);
		}
	}
	
	private static int getIdxOfEndTag(String line){
		return line.indexOf((int)'>');
	}
	
	private static void printPath(){
		List<String> pathList = new ArrayList<String>();
		for(String[] tagInfo: tagList){
			String printPath = "";
			if(tagInfo[1].equals("S"))
				pathList.add(tagInfo[0]);
			else	//�����±� �� ��� path�� ������ ��Ҹ� �����Ѵ�.
				pathList.remove(pathList.size()-1);
			
			//������ path�� append�Ѵ�.
			for(String path: pathList)
				printPath += ", "+path;
			
			if(printPath.equals("")){
				System.out.println("[]");
			}else{
				System.out.println("["+printPath.substring(2)+"]");
			}
		}
		if(!tagList.isEmpty()) tagList.clear();
		if(!pathList.isEmpty()) tagList.clear();
	}
}