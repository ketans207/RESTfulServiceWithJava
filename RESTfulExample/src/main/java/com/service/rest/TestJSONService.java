package com.service.rest;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestJSONService {
	
	JSONServiceWithJavaClass jsonService = new JSONServiceWithJavaClass();
	

	@Test
	public void testRegisterInService(){
		String param = "abc";
		jsonService.registerInService(param);
		
		int asciiVal = JSONService.getTokenId(param.toCharArray());
		String fetchParam = String.valueOf(asciiVal);
		List<String> matchingTokens = new ArrayList<String>();
		matchingTokens = jsonService.fetchMatchingStrings(fetchParam);
		String testMatchingString =  matchingTokens.get(0);
		
		
		assertEquals(param, testMatchingString);
	}
	
	@Test
	public void testRegisterInServiceRepeatChar(){
		String param = "aabc";
		jsonService.registerInService(param);
		
		int asciiVal = JSONService.getTokenId(param.toCharArray());
		String fetchParam = String.valueOf(asciiVal);
		List<String> matchingTokens = new ArrayList<String>();
		matchingTokens = jsonService.fetchMatchingStrings(fetchParam);
		String testMatchingString =  matchingTokens.get(0);
		
		
		assertEquals(param, testMatchingString);
	}
	
	
	@Test
	public void testFetchMatchingStrings(){
		String param = "489";
		String matchingString = "abc";
		
		List<String> matchingTokens = new ArrayList<String>();
		matchingTokens = jsonService.fetchMatchingStrings(param);
		String testmatchingString =  matchingTokens.get(0);
		
		assertEquals(matchingString, testmatchingString);	
	}
	
	@Test
	public void testFetchMatchingStringsMultipleEntries(){
		
		String multiVal = "pqr"; // registering "pqr" two times in file
		
		jsonService.registerInService(multiVal);
		jsonService.registerInService(multiVal);
		
		String param = "564";
		
		List<String> matchingString = new ArrayList<String>();// preparing expected result
		matchingString.add("pqr");
		matchingString.add("pqr");
		List<String> testmatchingString = new ArrayList<String>();
		
		testmatchingString = jsonService.fetchMatchingStrings(param);
		
		assertEquals(matchingString, testmatchingString);	
	}
	
	@Test
	public void testGetTokenId(){
		String param = "def";
		int tokenid = 504;
		int testTokenid = JSONServiceWithJavaClass.getTokenId(param.toCharArray());
		
		assertEquals(tokenid, testTokenid);
		
	}
	
	@Test
	public void testGetStringId(){
		
		String param = "xyz";
		int tokenid = 604;
		
		int testTokenid = JSONServiceWithJavaClass.getStringId(param.toCharArray(), 0);
		
		assertEquals(tokenid, testTokenid);
		
	}
	

}
