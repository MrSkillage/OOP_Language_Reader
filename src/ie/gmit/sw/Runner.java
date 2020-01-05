package ie.gmit.sw;

import java.util.Map;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws Throwable{
		
		//Call menu and in
		Menu menu = new Menu();
		Scanner in = new Scanner(System.in);
		
		//Declaration of Variables 
		String textFile, queryFile;
		int kmer;
		
		//Display Menu Message
		menu.Display();		
		
		//Display Kmer Request Message & wait for user input
		menu.kmerRequestMessage();
		kmer = in.nextInt();
		
		//Display File Request Message & wait for user input
		menu.fileMessage();
		textFile = in.next();
		
		//Try Catch Statement
		try {
			
			//New Database db and Parser par
			Database db = new Database();
			Parser par = new Parser(textFile, kmer);			
			//Set database to parser
			par.setDB(db);
			
			//Make Thread and run calling start() and join existing thread 
			Thread t1 = new Thread(par);
			t1.start(); 
			t1.join();
			
			//Display QueryFile Request Message & wait for user input
			menu.queryFileMessage();
			queryFile = in.next();
			
			//Close Scanner
			in.close();
			
			//New Map equal to parser queryParse Method with kmer and queryFile entered
			Map<Integer, LanguageEntry> map = par.queryParser(kmer, queryFile);
			
			//Display the result Message
			menu.result(db, map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
