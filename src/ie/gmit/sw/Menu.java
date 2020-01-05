package ie.gmit.sw;

import java.util.Map;

public class Menu {

	static void Display() {
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*              Text Language Detector             *");
		System.out.println("***************************************************");		
	}
	
	void kmerRequestMessage() {
		System.out.println("Please enter in the number of kmers you wish to use: ");
	}
	
	void fileMessage() {
		System.out.println("Enter WILI file location: ");
	}
	
	void queryFileMessage() {
		System.out.println("Enter Query file location: ");
	}
	
	void result(Database db, Map<Integer, LanguageEntry> theMap) {
		System.out.println("Processing Query....... please wait...");
		System.out.println("Text appears to be written in " + db.getLanguage(theMap));
	}
	
}
