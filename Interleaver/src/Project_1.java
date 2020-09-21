/*
 * Author: Drew Grobmeier 
 * Description: An Interleaver used for Cyrptology
 * 
 * Note:	You will have to clear the contents of file.txt to get the desired 
 * 			output that is being looked for. The directions were a little 
 * 			ambiguous on whether or not to keep the padded Z's in the encryption
 * 			I took them out, but I could easily replace them to have the Z's
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.util.Scanner;


public class Project_1 {
	
	public static void stringToMemory(String data) {
		//Changing to uppercase
		String newData = data.toUpperCase().trim();
		//getting rid of punctuations and spaces
		String newerData = newData.replaceAll("\\s+","");
		//print to check
		System.out.println("The text to encrypt is: " + newerData);

		//Important for comparing size between linkedlists and interleaver input. 
		System.out.print("How many columns would you like to have for the interleaver: ");
		Scanner input = new Scanner(System.in);
		int numberInput = input.nextInt();
		System.out.println();

		//Top
		LinkedList<String> ll = new LinkedList<String>();
		//Bottom
		LinkedList<String> zz = new LinkedList<String>();
			
		//For looking for every other character within the linkedList
		for (int i=0; i<newerData.length(); i+=2) {
			System.out.print(newerData.charAt(i));
			String s = String.valueOf(newerData.charAt(i)); 
			ll.add(s);
		}
		
		//Printing the top row
		System.out.println(" --> " + ll + " This is the top row" );
		for (int i=1; i<newerData.length(); i+=2) {
			String s = String.valueOf(newerData.charAt(i)); 
			zz.add(s);
			System.out.print(s);
			}
		
		//bottom list has to equal top list in size, if not, pad with Z's
		while (zz.size() < ll.size()) {
			zz.add("Z");
		}
		
		System.out.println(" --> " + zz + " This is the bottom row with padded Z");
		
		System.out.println();
		//If the length of the interleaver is specified, padding Z's
		if (numberInput > ll.size() || numberInput > zz.size()) {
			System.out.print("Printing with padded Z's now...");
		}
		while (numberInput > ll.size() || numberInput > zz.size()) {
			ll.add("Z");
			zz.add("Z");
		}
		
		while (numberInput < ll.size() || numberInput < zz.size()) {
			ll.removeLast();
			zz.removeLast();
		}
		
		System.out.println();
		System.out.println(ll);
		System.out.println(zz);
		
		System.out.println();
		// Appending zz to ll to create ciphertext
		ll.addAll(zz);
		System.out.println("Ciphertext with Z: " + ll);
		// Removing the letter "Z" for the ciphertext
		while (ll.contains("Z")) {
			ll.remove("Z");
		}
		// Yes, I know the plaintext may contain Z, but it is highly unlikely
		// Instructions were ambiguous on continuing with the letter Z or taking it out
		System.out.println("Ciphertext without Z: " + ll);
		
		try {
			File text = new File("file.txt");
			PrintWriter printWriter = new PrintWriter ("file.txt");
			printWriter.println ("Ciphertext without Z: " + ll);
			printWriter.close();
		}
		 catch (FileNotFoundException e) {
				System.out.println("This didn't work, try again...");
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		//read in a text file input
		try {
			File text = new File("givenText.txt");
			Scanner read = new Scanner(text);
			while (read.hasNextLine()) {
				String data = read.nextLine();
				stringToMemory(data);
			}
			
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("There is no file chosen");
			e.printStackTrace();
		}
		
	}
	
}
