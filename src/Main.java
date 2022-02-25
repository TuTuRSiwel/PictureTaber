import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Main { 
	public static void main(String[] args) 
	{
		//////////////// 1
		List<String> results = new ArrayList<String>();

		List<People> peopleList =  new ArrayList<People>();
		
		File[] files = new File(System.getProperty("user.dir")+"/InputPictures").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 
		//"D:/Drive/Drive_TravailVanburen/Isep Travaux/Numérique___/JAVA/ProjetJava/eclipse-workspace/PictureTaber/InputPictures"
		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		        System.out.println(file.getName());
		    }
	    	
	        ///// print the pile line per line
		    People newPeople = new People(file) ;
	    	peopleList.add(newPeople);//
	    	
		}
		try (PrintWriter writer = new PrintWriter(new File("Output.csv"))) {
			
			StringBuilder sb = new StringBuilder();
			sb.append("Nom"+",");
			sb.append("Prenom"+',');
			sb.append("Telephone"+',');
			sb.append("e-mail"+',');
			sb.append("image"+',');
			sb.append('\n');
			
			for(People p : peopleList) {
			
				sb.append(p.famillyName.replaceAll(",", "")+",");
				sb.append(p.firstName.replaceAll(",", "")+',');
				sb.append(p.phone.replaceAll(",", "")+',');
				sb.append(p.email.replaceAll(",", "")+',');
				sb.append('"'+p.filePath.getAbsolutePath()+'"'+',');
				sb.append('\n');
				
			}
			writer.write(sb.toString());
			writer.close();
			System.out.println("done!");
			
	    } catch (FileNotFoundException e) {
	      System.out.println(e.getMessage());
	    }
		//////////////// 2
		System.exit(0);
		
	}
}
	
