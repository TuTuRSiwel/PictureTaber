import java.io.File;

public class People {
	
	File filePath;
	String PreProcessedFileName;
	String processFile ;
	String firstName = "";
	String famillyName = "";
	String phone = "";
	String email = "";
	
	public People(File filePath) {
		// TODO Auto-generated constructor stub
		PreProcessedFileName = fileNamePreProcess(filePath.getName());
		processFile = PreProcessedFileName;
		this.filePath = filePath;
		this.email = findPeopleEmail();
		this.phone = findPeoplePhone();
		findPeopleName();
		
		////
		System.out.println("////////------ generated info -------////////");
		System.out.println(this.famillyName);
		System.out.println(this.firstName);
		System.out.println(this.phone);
		System.out.println(this.email);
		System.out.println(this.filePath);
		System.out.println("------------ end of generated info -----------\n");
	}
	
	
	private String findPeopleEmail() {
		// TODO Auto-generated method stub
		String[] splitedLine = this.processFile.split(" ");
		int count = 0;
		for(String s:splitedLine) {
			if((s.indexOf('@') != -1)&&(count == splitedLine.length-1)) {
				String email = s.replaceFirst("[.][^.]+$", ""); 
				return email;
			}
			
			if(s.indexOf('@') != -1) { 
				return s;
			}
			
			count++;
		}
		return null;	
	}


	private String findPeoplePhone() {
		// TODO Auto-generated method stub
		char[] charArray = this.processFile.toCharArray();
		String phoneNumber = "";
		boolean capture = false;
		boolean isInternational = false; 
		for(char c:charArray) {
			if(c!=' ') {
				
				if (!Character.isDigit(c) && capture == true) {
					capture = false;
					//System.out.println(phoneNumber);
					return phoneNumber;
				}
				
				if (c == '0' || c == '+' ) {
					if(c=='+') {isInternational = true;}
					capture = true;
					}
				if (capture == true) {
					phoneNumber += c;
				}
				
			}

		}
		//System.out.println(phoneNumber);
		return phoneNumber;	
		
	}


	private void findPeopleName() {
		// TODO Auto-generated method stub
		String[] splitedLine = PreProcessedFileName.split(" ");
		
		int NameIndexLimit = 0; 
		
		for (String s:splitedLine) {
			if(s.indexOf('0') != -1 || s.indexOf('+') != -1) {break;}
			NameIndexLimit++;
		}
		for(int i = 0 ; i<NameIndexLimit-1;i++) {
			this.famillyName += splitedLine[i];
		}
		
		this.firstName = splitedLine[NameIndexLimit-1];
		
	}


	private String fileNamePreProcess(String fileName) {
		// TODO Auto-generated method stub
	
		
		String preProcessedFileName = fileName; 
		preProcessedFileName = preProcessedFileName.trim().replaceAll("\\s{2,}", " ");

		//System.out.println("file went from\n"+fileName+"\n to \n"+preProcessedFileName);
		return preProcessedFileName;
	}
}
