package jborg.exam.examNoBS24.product.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SortByCode
{

	public static final String alphaNum ="ABC";
	
	public static final String price = "Price";
	
	public static final Set<String> set = new HashSet<>(Arrays.asList(alphaNum, price));
	
	public static boolean isKnownSortCode(String s)
	{
		return set.contains(s);
	}
}
