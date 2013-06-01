package util;

import java.util.ArrayList;
import java.util.List;

public class EscapeCharTest {

	static List<String[]> tagList = new ArrayList<String[]>();
	
	public static void main(String[] args) {
		String singleChar1 = "	<!DOCTYPE >&lt;  df <html> <head> <title > </title> </head> <body> <div id=dkeik > <img id=> </div> </body> </html>";
		
		//line을 시작태그와 종료태그로 분류하여 저장한다.
		saveTags(singleChar1);
		//path를 찍는다.
		printPath();
	}
	
	private static void saveTags(String line){
		String newLine = "";
		String[] tagInfo;
		for(int i=0; i<line.length(); i++){
			//<가 나타나면 시작태그인지 종료태그인지를 분류하여 저장한다.
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
		//시작, 종료, 그 이외의 태그(주석 ...)
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
		//img, br, input태그는 별도의 종료태그가 없다. 따라서 종료태그를 같이 넣어준다.
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
			else	//종료태그 일 경우 path의 마지막 요소를 제거한다.
				pathList.remove(pathList.size()-1);
			
			//저장한 path를 append한다.
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