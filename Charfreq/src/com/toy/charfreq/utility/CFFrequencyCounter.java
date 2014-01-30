package com.toy.charfreq.utility;

import java.util.HashMap;
import java.util.Locale;

public class CFFrequencyCounter {
	
	private HashMap<Character, Integer> mFrequencies;
	private String mInput;
	
	public CFFrequencyCounter(String input) {
		
		// Clean input of non-alpha and create our map
		mInput  = input.replaceAll("[^a-zA-Z]","").toUpperCase(new Locale("en_US"));
		mFrequencies = new HashMap<Character, Integer>();
	}
	
	public HashMap<Character, Integer> generateFrequencyMap() {
		
		// Iterate input and generate frequency count
		for(int i = 0; i < mInput.length(); i++) {
			Character currentCharacter = mInput.charAt(i);
			Integer charCount = mFrequencies.get(currentCharacter);
			
			// If the character hasn't been seen in the map yet add it as the first occurrence
			// otherwise increment the number of times its been seen.
			mFrequencies.put(currentCharacter, charCount != null ? charCount + 1 : 1);
		}
		return mFrequencies;
	}
}
