package comp3111G15;

import java.io.File;

public class tester {
	public static void main(String[] args) throws Exception {

		String csvFile = "Sample Student Data File.CSV";
		File file = new File(csvFile);
		System.out.println(file.getAbsolutePath());
		if(file.canRead())
		{
			System.out.println("readable");
		    // read file here
		} else {
			System.out.println("not readable");
			
		}
		

	}
}
