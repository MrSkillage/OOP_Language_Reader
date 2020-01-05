package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Parser implements Runnable {

	private Database db = null;
	private String file;
	private int k;
	
	public Parser(String file, int k) {
		this.file = file;
		this.k = k;
	}
	
	public void setDB(Database db) {
		this.db = db;
	}
	
	@Override
	public void run() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			
			while ((line = br.readLine()) != null) {
				String[] record = line.trim().split("@");
				if (record.length != 2) continue;
				parse(record[0], record[1]);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parse(String text, String lang, int... ks) {
		Language language = Language.valueOf(lang);
		
		for(int i = 0; i <= text.length() - k; i++) {
			CharSequence kmer = text.substring(i, i + k);
			db.add(kmer, language);
		}
	}
	
	/**
	 * QueryParser method
	 */
	public Map<Integer, LanguageEntry> queryParser(int k, String file) {
		
		//Declare two new maps and use a ConcurrentHashMap
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, LanguageEntry> queryMap = new ConcurrentHashMap<>();
		
		//Try Catch Statement
		try {
			//New BufferedReader br
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			//Initialization of String Variables
			String line = null;
			String words = null;
			String queryText = "";
			
			//TempArray List
			ArrayList<String> tempArray = new ArrayList<>();

			//While there is still a next line to be read, then read
			while ( (line = br.readLine()) != null) {
				//Trim next line
				words = line.trim().replace("\n", "").replace("\r", "");
				//Add String words to the ArrayList tempArray
				tempArray.add(words);
			}
			
			//New String using join
			String newWord = String.join("", tempArray);
			
			/**
			 * Checks the size of the newWord String doesn't exceed over 400 characters then closes BufferedReader
			 */
			
			for (char c : newWord.toCharArray()) {
				queryText += c;
				if (queryText.length() >= 400)
					break;
				br.close();
			}
			
			/**
			 * For loop uses an If-Statement to check if substring has occurred already
			 * If so then add 1 to the value else put hashCode with value 1 (enters for the first time)
			 */
			
			for (int i = 0; i < queryText.length() - k; i++) {
				CharSequence kmer = queryText.substring(i, i + k);
				if (map.containsKey(kmer.hashCode())) {
					map.put(kmer.hashCode(), map.get(kmer.hashCode()) + 1);
				} else {
					map.put(kmer.hashCode(), 1);
				}
			}
			
			//Map goes through Sort Method
			map = Sort(map);
			
			//Initialized Variable
			int current = 1;
			
			/**
			 * For each couple:
			 * entry = new Language with couple key & value
			 * set Rank then put into queryMap 
			 * Increment rank for next couple
			 */
			for (Map.Entry<Integer, Integer> couple : map.entrySet()) {
				LanguageEntry entry = new LanguageEntry(couple.getKey(), couple.getValue());
				entry.setRank(current);
				queryMap.put(entry.getKmer(), entry);
				if (current <= 1)
					current++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Return queryMap
		return queryMap;
	}
	
	//Sort method used to sort the passed Map by the frequency of a kmer (words) repetited appearance
	public static Map<Integer, Integer> Sort(Map<Integer, Integer> wordCount) {
		return wordCount.entrySet().parallelStream().sorted((Map.Entry.<Integer, Integer>comparingByValue().reversed())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (i, i2) -> i, LinkedHashMap::new));
	}
	
}
