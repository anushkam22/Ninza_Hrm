package com.ninza.hrm.genericUtility;

import java.util.Random;

public class JavaUtils {

	public int getRandomNumber()
	{
		Random no = new Random();
		int i =no.nextInt(1000);
		return i;
	}
}
