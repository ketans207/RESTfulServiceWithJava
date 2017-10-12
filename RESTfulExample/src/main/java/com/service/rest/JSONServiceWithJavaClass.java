package com.service.rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class JSONServiceWithJavaClass {

	private static final String path = "C:/temp/Register.txt";

	public String registerInService(String param) {

		int asciiChar = JSONServiceWithJavaClass.getTokenId(param.toCharArray());

		String token = String.valueOf(asciiChar) + "=" + param + "\n";

		JSONServiceWithJavaClass.writeLine(path, token);

		return String.valueOf(asciiChar);
	}

	public List<String> fetchMatchingStrings(String param) {

		List<String> tokens = JSONServiceWithJavaClass.readLines(path);

		String searchString = param.concat("=");
		List<String> matchingTokens = new ArrayList<String>();
		for (String token : tokens) {
			if (token.startsWith(searchString)) {
				String[] words = token.split("=");
				matchingTokens.add(words[1]);
			}
		}

		return matchingTokens;
	}

	public static int getStringId(char[] input, int position) {

		if (null == input || position >= input.length || position < 0)
			return 0;
		
		int total = +input[position];

		if (position > 0 && input[position] != input[position - 1]) {
			total += input[position - 1];
		}

		position = position + 1;
		int subTotal = getStringId(input, position);

		return total + subTotal;

	}

	public static int getTokenId(char[] input) {

		if (null == input)
			return 0;
		int total = getStringId(input, 0);

		return total;
	}

	private static void writeLine(String path, String data) {
		File input = new File(path);
		try {
			FileUtils.write(input, data, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static List<String> readLines(String path) {
		File input = new File(path);
		List<String> token = null;
		try {
			token = FileUtils.readLines(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return token;
	}

	
}
