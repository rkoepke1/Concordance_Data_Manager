/**
 * Manages the Concordance Data Structur to create a concordance file from either an input string or a text file.
 * 
 * @author Ryan Koepke
 */
package dataManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dataStructure.ConcordanceDataStructure;
import interfaces.ConcordanceDataManagerInterface;
public class ConcordanceDataManager implements ConcordanceDataManagerInterface{
	private Scanner inFile;
	private ConcordanceDataStructure data = new ConcordanceDataStructure(500);
	private PrintWriter outFile;
	/**
	    * 
	    * Display the words in Alphabetical Order followed by a :, 
	    * followed by the line numbers in numerical order, followed by a newline
	    * 
	    * @param input a String (usually consist of several lines) to 
	    * make a concordance of.
	    * 
	    * @return an ArrayList of Strings.  Each string has one word,
	    * followed by a :, followed by the line numbers in numerical order,
	    * followed by a newline.
	    */
	@Override
	public ArrayList<String> createConcordanceArray(String input) {
		input = input.toLowerCase();
		String[] lines = input.split("\n");
		String words = "";
		int lineNumber = 0;
		for(int i = 0; i < lines.length; i++) {
			String[] wordArr = lines[i].split(" ");
			for(int j = 0; j < wordArr.length; j++) {
				String word = "";
				for(int k = 0; k < wordArr[j].length(); k++) {
					int c = wordArr[j].charAt(k);
					if(((c > 95 && c < 123) || c == 39)) {
						word += (char)c;
					}
				}
				if(word.length() >= 3 && !(word.equals("the") || (word.equals("and")))) {
					words += word + " ";
				}
				word = "";
			}
			lineNumber ++;
			String [] wordsArr = words.split(" ");
			for(int l = 0; l < wordsArr.length; l ++) {
				if(wordsArr[l].length() > 0) {
					data.add(wordsArr[l], lineNumber);
				}
			}
			words = "";
		}
		ArrayList<String> newList = new ArrayList<>();
		ArrayList<String> dataList = data.showAll();
		for(int i = 0; i < dataList.size(); i++) {
			newList.add(dataList.get(i) + "\n");
		}
		return newList;
	}

	 /**
	    * Creates a file that holds the concordance  
	    * 
	    * @param input the File to read from
	    * @param output the File to write to
		* 
	    * @return true if the concordance file was created successfully.
	    * @throws FileNotFoundException if file not found
	    */
	@Override
	public boolean createConcordanceFile(File input, File output) throws FileNotFoundException {
		if(!(input.canRead()) || !(output.canWrite())) {
			throw new FileNotFoundException();
		}
		inFile = new Scanner(input);
		outFile = new PrintWriter(output);
		int lineNumber = 1;
		while(inFile.hasNextLine()) {
			String line = inFile.nextLine().toLowerCase();
			String[] lineArr = line.split(" ");
			String words = "";
			for(int i = 0; i < lineArr.length; i++) {
				String word = "";
				for(int j = 0; j < lineArr[i].length(); j ++) {
					int c = lineArr[i].charAt(j);
					if((c > 95 && c < 123) || c == 39) {
						word += (char)c;
					}
				}
				if(word.length() >= 3 && !(word.equals("the") || word.equals("and"))) {
					words += word + " ";
				}
				word = "";
			}
			String [] wordsArr = words.split(" ");
			for(int i = 0; i < wordsArr.length; i ++) {
				if(wordsArr[i].length() > 0) {
					data.add(wordsArr[i], lineNumber);
				}
			}
			lineNumber ++;
		}
		inFile.close();
		ArrayList<String> list = data.showAll();
		for(int i = 0; i < list.size(); i++) {
			outFile.println(list.get(i));
		}
		outFile.close();
		return true;
	}

}
