/**
 * A Data Element that holds the word and the page list of the input word.
 * 
 * @author Ryan Koepke
 */
package dataElelement;

import java.util.LinkedList;

public class ConcordanceDataElement implements Comparable<ConcordanceDataElement>{
	private String word;
	private LinkedList<Integer> list;
	
	/**
	 * Constructor for the data element to initialize the word.
	 * 
	 * @param word Input the word to be initialized into the data element.
	 */
	public ConcordanceDataElement(String word) {
		this.word = word;
		this.list = new LinkedList<Integer>();
	}
	
	/**
	 * Add a line number to the linked list if the number doesn't exist in the list.
	 * 
	 * @param lineNum The line number the word is found.
	 */
	public void addPage(int lineNum) {
		boolean hasNum = false;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i) == lineNum)
				hasNum = true;
		}
		if(!hasNum) {
			list.add(lineNum);
		}
	}
	
	/**
	 * Returns the linked list of integers that represent the line numbers.
	 * 
	 * @return The linked list of page numbers of the data element.
	 */
	public LinkedList<Integer> getList(){
		return this.list;
	}
	
	/**
	 * Return the word portion of the Concordance Data Element.
	 * 
	 * @return The word of the data element.
	 */
	public String getWord() {
		return this.word;
	}
	
	/**
	 * Returns the hashCode.
	 * 
	 * @return The hashcode of this data element.
	 */
	public int hashCode() {
		//stem.out.println(word.hashCode());
		return word.hashCode();
	}
	
	/**
	 * The overridden compare method to compare data elements.
	 * 
	 * @param An inputed Data Element to compare from.
	 * @return An integer value based on the comparison of the input Concordance data element.
	 */
	@Override
	public int compareTo(ConcordanceDataElement o) {
		return word.compareTo(o.word);
	}
	
	/**
	 * Returns the word followed by page numbers Returns a string in the following format: word: page num, 
	 * page num Example: after: 2,8,15.
	 * 
	 * @return A formated string with the word and and page numbers.
	 */
	public String toString() {
		String num = "";
		for(int i = 0; i < list.size(); i ++) {
			num = list.get(i) + ",";
		}
		return word + ": " + num;
	}

}
