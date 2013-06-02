package estsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParsingHtmlTagAndPrintPath {

	static List<String[]> tagList = new ArrayList<String[]>();
	static final String[] noCloseTags = {"img","br","input","meta"};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = "";
		while(sc.hasNextLine()){
			line = sc.nextLine();
			//line�� �����±׿� �����±׷� �з��Ͽ� �����Ѵ�.
			saveTags(line);

			//�����±װ� ��Ÿ���� ��´�.
			if(line.indexOf("</html>") >= 0){
				//path�� ��´�.
				printPath();
			}
		}
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
		//tag�� whitespace�� �����ϸ� ù��° whitespace�������� tag������ �Ѵ�.
		if(idxWhtspc >= 0)
			tag = tag.substring(0, idxWhtspc); 
		
		int firstChr = (int)tag.charAt(0);
		//����, ����, �� �̿��� �±�(�ּ� ...)�� �з��Ͽ� �����Ѵ�.
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
		if(hasNotCloseTag(tagInfo[0])){
			tagList.add(tagInfo);
			String[] a = {tagInfo[0],"E"};
			tagList.add(a);
		}else{
			tagList.add(tagInfo);
		}
	}
	
	private static boolean hasNotCloseTag(String tag){
		for(String noClosetag: noCloseTags)
			if(tag.toLowerCase().equals(noClosetag))
				return true;
		return false;
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
			
			if(printPath.equals(""))
				System.out.println("[]");
			else
				System.out.println("["+printPath.substring(2)+"]");
		}
		if(!tagList.isEmpty()) tagList.clear();
		if(!pathList.isEmpty()) tagList.clear();
	}
}
