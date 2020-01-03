package ie.gmit.sw;

public class Runner {

	public static void main(String[] args) throws Throwable{
		
		Parser p = new Parser("wili-2018-Small-11750-Edited.txt", 1);
		
		Database db = new Database();
		p.setDB(db);
		Thread t = new Thread(p);
		t.start();
		t.join();
		
		db.resize(300);
		
		
		
	}

}
