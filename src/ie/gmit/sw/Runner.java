package ie.gmit.sw;

import java.util.Map;
import java.util.Scanner;

public class Runner {

	public static void main(String[] args) throws Throwable{
		
		Menu menu = new Menu();
		menu.Display();
		
		Scanner in = new Scanner(System.in);
		String textFile, queryFile;
		int kmer;
		
		menu.kmerRequestMessage();
		kmer = in.nextInt();
		
		menu.fileMessage();
		textFile = in.next();
		
		try {
			
			Database db = new Database();
			Parser par = new Parser(textFile, kmer);			
			par.setDB(db);
			
			Thread t1 = new Thread(par);
			t1.start();
			t1.join();
			
			menu.queryFileMessage();
			queryFile = in.next();
			Map<Integer, LanguageEntry> map = par.queryParser(kmer, queryFile);
			
			in.close();
			
			menu.result(db, map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
