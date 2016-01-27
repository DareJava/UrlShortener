package com.amacodecode.services;


public interface ShortenerInterface {

	// converts id from base ten to 62
 public String convertIntbaseTenToBaseSixTwo(int baseTenNumber);
 
 public String getFullUrlFromUsingString(String base);
 
 public int persistUrl(String url);
 
}
