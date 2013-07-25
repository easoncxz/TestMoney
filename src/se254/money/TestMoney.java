package se254.money;
/**
 * SOFTENG 254 2011 Assignment 1 submission
 *
 * Author: (Xuzong Chen, xche985)
 **/

import junit.framework.TestCase;

public class TestMoney extends TestCase {

	private Money money;
	
	public void setUp(){
		money = new Money();
	}
	
	public void testDefaultConstructor(){
		assertTrue(money.equals(money));
	}

    /**
     * DO NOT DELETE THIS
     * This is needed for the automatic marking process.
     **/
	// public static void main(String[] args) {
	// junit.textui.TestRunner.run(TestInheritanceModel.class);
	// }
}
