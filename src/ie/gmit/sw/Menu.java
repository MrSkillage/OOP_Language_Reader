package ie.gmit.sw;

import java.util.Map;

public class Menu {

	/**
	 * Prints a menu to the user via System.out.println() statements.
	 * @author Conor Rabbitte
	 */
	void Display() {
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*              Text Language Detector             *");
		System.out.println("***************************************************");		
	}
	
	/**
	 * Prints a Kmer Request Message to the user.
	 */
	void kmerRequestMessage() {
		System.out.println("Please enter in the number of kmers you wish to use: ");
	}
	
	/**
	 * Prints a File Request Message to the user.
	 */
	void fileMessage() {
		System.out.println("Enter WILI file location: ");
	}
	
	/**
	 * Prints a QueryFile Request Message to the user.
	 */
	void queryFileMessage() {
		System.out.println("Enter Query file location: ");
	}
	
	/**
	 * Takes in a Database db and a Map<> theMap and prints a final result to the user
	 * using the Method getLanguage from the Database class, using theMap as a parameter.
	 * @author Conor Rabbitte
	 * @param db - A Database 
	 * @param theMap - A Map of type Map<Integer, LanguageEntry>
	 */
	void result(Database db, Map<Integer, LanguageEntry> theMap) {
		System.out.println("Processing Query....... please wait...");
		//Calls getLanguage method from db and passes parameter theMap
		System.out.println("Text appears to be written in " + db.getLanguage(theMap));
	}
	
}
