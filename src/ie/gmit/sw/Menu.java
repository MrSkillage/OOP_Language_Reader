package ie.gmit.sw;

import java.util.Map;

public class Menu {

	//Display Main Menu Welcome Message
	void Display() {
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*              Text Language Detector             *");
		System.out.println("***************************************************");		
	}
	
	//Display Request Kmer Message
	void kmerRequestMessage() {
		System.out.println("Please enter in the number of kmers you wish to use: ");
	}
	
	//Display Request File Message
	void fileMessage() {
		System.out.println("Enter WILI file location: ");
	}
	
	//Display Request QueryFile Message
	void queryFileMessage() {
		System.out.println("Enter Query file location: ");
	}
	
	//Display RESULT Message
	void result(Database db, Map<Integer, LanguageEntry> theMap) {
		System.out.println("Processing Query....... please wait...");
		//Calls getLanguage method from db and passes parameter theMap
		System.out.println("Text appears to be written in " + db.getLanguage(theMap));
	}
	
}
