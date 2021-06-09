/**
 * Data structure which determines the hashtable size and adds a word into it with associated page numbers.
 * 
 * @author Ryan Koepke
 */
package dataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

import dataElelement.ConcordanceDataElement;
import interfaces.ConcordanceDataStructureInterface;


public class ConcordanceDataStructure implements ConcordanceDataStructureInterface{
	private int size;
	private String test;
	private LinkedList<ConcordanceDataElement>[] hashTable;
	private final double LOAD_FACTOR = 1.5;  
	private ListIterator<ConcordanceDataElement> iter;
	
	/**
	 * Constructor that initializes the hashtable based on a load factor of 1.5.
	 * 
	 * @param num The estimated size of the hashtable.
	 */
	public ConcordanceDataStructure(int num) {
	    boolean prime = false;
	    int n = (int) (num / LOAD_FACTOR);
	    while(!prime) {
	    	int factorCount = 0;
	    	int factorRange = n/2;
	    	for(int i = 2; i < factorRange; i ++) {
	    		if(n % i == 0) {
	    			factorCount ++;
	    		}
	    	}
	    	if(factorCount > 0) {
	    		n++;
	    	}else {
	    		int check = (n -3);
	    		if(check % 4 == 0) {
	    			prime = true;
	    		}else {
	    			n++;
	    		}
	    	}
	    }
	    this.size = n;
	    @SuppressWarnings("unchecked")
        LinkedList<ConcordanceDataElement>[] temp = new LinkedList[n];
        hashTable = temp;
	}
	/**
	 * Constructor that initializes the hashtable given just an integer value for testing purposes.
	 * 
	 * @param testing String to show the concordance data structure is for testing.
	 * @param size The initial size of the hashtable.
	 */
	public ConcordanceDataStructure(String testing, int size) {
		@SuppressWarnings("unchecked")
		LinkedList<ConcordanceDataElement>[] temp = new LinkedList[size];
		hashTable = temp;
		this.size = size;
		this.test = testing;
	}
	
	/**
	 * A method to get the hashtable size.
	 * 
	 * @return An integer value of the hashtable size.
	 */
	@Override
	public int getTableSize() {
		return this.size;
	}
	
	/**
    * Returns an ArrayList of the words at this index
    * [0] of the ArrayList holds the first word in the "bucket" (index)
    * [1] of the ArrayList holds the next word in the "bucket", etc.
    * This is used for testing
    * @param index location within the hash table
    * @return an Arraylist of the words at this index
    */
	@Override
	public ArrayList<String> getWords(int index) {
		ArrayList<String> words = new ArrayList<>();
		iter = hashTable[index].listIterator();
		while(iter.hasNext()) {
			words.add(iter.next().getWord());
		}
		return words;
	}

	/**
    * Returns an ArrayList of the Linked list of page numbers for each word at this index
    * [0] of the ArrayList holds the LinkedList of page numbers for the first word in the "bucket" (index)
    * [1] of the ArrayList holds the LinkedList of page numbers for next word in the "bucket", etc.
    * This is used for testing
    * @param index location within the hash table
    * @return an ArrayList of the Linked list of page numbers for each word at this index
    */
	@Override
	public ArrayList<LinkedList<Integer>> getPageNumbers(int index) {
		ArrayList<LinkedList<Integer>> pageLists = new ArrayList<>();
		iter = hashTable[index].listIterator();
		while(iter.hasNext()) {
			pageLists.add(iter.next().getList());
		}
		return pageLists;
	}

	/** 
    * Use the hashcode of the ConcordanceDataElement to see if it is 
    * in the hashtable.
    * 
    * If the word does not exist in the hashtable - Add the ConcordanceDataElement 
    * to the hashtable. Put the line number in the linked list
    *  
    * If the word already exists in the hashtable add the line number to the end of 
    * the linked list in the ConcordanceDataElement 
    * (if the line number is not currently there).  
    * 
    * @param word the word to be added/updated with a line number.
    * @param lineNum the line number where the word is found
    */
	@Override
	public void add(String word, int lineNum) {
		ConcordanceDataElement cde = new ConcordanceDataElement(word);
		LinkedList<ConcordanceDataElement> newList = new LinkedList<>();
		int index = Math.abs(cde.hashCode()) % hashTable.length;
		if(hashTable[index] == null) {
			cde.addPage(lineNum);
			newList.add(cde);
			hashTable[index] = newList;
		}else {
			iter = hashTable[index].listIterator();
			boolean addNewWord = true;
			boolean addNewPage = true;
			while(iter.hasNext()) {
				ConcordanceDataElement data = iter.next();
				if(data.compareTo(cde) == 0) {
					LinkedList<Integer> pageList = data.getList();
					ListIterator<Integer> pageIter = pageList.listIterator();
					while(pageIter.hasNext()) {
						int pageNum = pageIter.next();
						if(pageNum == lineNum) {
							addNewPage = false;
						}
					}
					if(addNewPage == true) {
						data.addPage(lineNum);
					}
					addNewWord = false;
				}
			}
			cde.addPage(lineNum);
			if(addNewWord == true) {
				hashTable[index].add(cde);
			}
		}
	}
	/** 
	 * Display the words in Alphabetical Order followed by a :, 
	 * followed by the line numbers in numerical order, followed by a newline
	 * 
	 * @return an ArrayList of Strings.  Each string has one word,
	 * followed by a :, followed by the line numbers in numerical order,
	 * followed by a newline.
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> all = new ArrayList<>();
		ArrayList<ConcordanceDataElement> words = new ArrayList<>();
		String word = "";
		for(int i = 0; i < hashTable.length; i ++) {
			if(hashTable[i] != null) {
				iter = hashTable[i].listIterator();
				while(iter.hasNext()) {
					ConcordanceDataElement data = iter.next();
					words.add(data);
				}
				
			}
		}
		Collections.sort(words);
		for(int i = 0; i < words.size(); i ++) {
			LinkedList<Integer> pageList = words.get(i).getList();
			ListIterator<Integer> pageIter = pageList.listIterator();
			ArrayList<String> intList = new ArrayList<>();
			while(pageIter.hasNext()) {
				String num = "" + pageIter.next();
				intList.add(num);
			}
			String integers = String.join(", ", intList);
			word += words.get(i).getWord() + ": " + integers;
			all.add(word);
			word = "";
		}
		return all;
	}

}
