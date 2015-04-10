package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class generates a password according to a user's specifications. 
 * @author Brandon Soto
 * @version Apr 10, 2014
 */
public final class PasswordGenerator {
	
	/** All of the digits that can be included in the password. */
	public static final Character[] NUMBERS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	/** Lowercase letters that can be included in the password. */
	public static final Character[] LOWERCASE_LETTERS = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 
		'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
		};
	
	/** Uppercase letters that can be included in the password. */
	public static final Character[] UPPERCASE_LETTERS = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
		'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
		};
	
	/** Punctuation that can be included in the password. */
	public static final Character[] PUNCTUATION = {
		' ', '`', '~', '.', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', 
		'=', '\\', '/', '|', '[', ']', '{' , '}', ';', ':',	'\"', '<', '>', ',', '?'
		};
	
	public static final PasswordGenerator GENERATOR_INSTANCE = new PasswordGenerator();
	
	/** Contains constant character arrays to include in generating the password. */
	private final List<Character[]> myCharacterGroups;
	
	/** Desired length of the password. */
	private int myPasswordLength;
	
	/** Prevents client instantiation. Constructs a PasswordGenerator with all password options disabled. */
	private PasswordGenerator() {
		myPasswordLength = 0;
		myCharacterGroups = new ArrayList<Character[]>();
	}
	
	/**
	 * Randomly generates a new password. 
	 * @return the new password. 
	 */
	public String generatePassword() {
		final StringBuilder thePassword = new StringBuilder();

		if (myPasswordLength > 0) {
			final Random rand = new Random();
			final int listSize = myCharacterGroups.size();
			Character[] chosenCharGroup;
			Character chosenChar;
			
			if (listSize >= 0) {
				
				for (int i = 0; i < myPasswordLength; i++) {
					chosenCharGroup = myCharacterGroups.get(rand.nextInt(listSize));
					chosenChar = chosenCharGroup[rand.nextInt(chosenCharGroup.length)];
					
					thePassword.append(chosenChar + "");
				}
				
			}
			
		}
		
		return thePassword.toString();
	}
	
	/**
	 * Sets the password length to the passed length.
	 * @param length desired length of password.
	 */
	public void setPasswordLength(final int length) {
		if (length >= 0) {
			myPasswordLength = length; 
		} 
	}
	
	/**
	 * @return password length. 
	 */
	public int getPasswordLength() {
		return myPasswordLength;
	}
	/** 
	 * Adds the passed array to the possible options that generates the password. Note: if the
	 * passed array is already in the list of possible options, it is ignored. 
	 * @param theCharGroup desired char group to include. 
	 */
	public void addGroup(final Character[] theCharGroup) {
		// only want the same char group in the list once
		if (!myCharacterGroups.contains(theCharGroup)) {
			myCharacterGroups.add(theCharGroup);
		}
	}
	
	/** 
	 * Removes the passed array from the possible options that generates the password.
	 * @param theCharGroup desired char group to remove. 
	 */
	public void removeGroup(final Character[] theCharGroup) {
		myCharacterGroups.remove(theCharGroup);
	}
	
}
