package se254.money;

/**
 * SOFTENG 254 2011 Assignment 1 submission
 *
 * Author: (Xuzong Chen, xche985)
 **/

import junit.framework.TestCase;

/**
 * As an assumption allowing for the logic of the tests, toString() is assumed
 * to be implemented correctly (i.e. can reliably represent the state of a Money
 * object correctly, as specified by the Money.toString() documentation.), due
 * to it being the only public method of the Money class that can report the
 * state of a Money object independently of the Money class.
 * 
 * @author (Xuzong Chen, xche985)
 */
public class TestMoney extends TestCase {

	private Money money;

	public void setUp() {
		money = new Money();
	}

	/**
	 * A trivial test.
	 */
	public void testDefaultConstructor() {
		assertTrue(money != null);
		assertEquals(money.toString(), "$0.00");
	}

	/**
	 * To test all cases for construction and toString().
	 */
	public void testCentsConstructor() {
		Money m;
		try {
			m = new Money(0, 0);
			assertEquals(m.toString(), "$0.00");
			m = new Money(0, 99);
			assertEquals(m.toString(), "$0.99");
			m = new Money(0, -99);
			assertEquals(m.toString(), "-$0.99");
			m = new Money(1, 0);
			assertEquals(m.toString(), "$1.00");
			m = new Money(1, 10);
			assertEquals(m.toString(), "$1.10");
			try {
				m = new Money(1, -10); // illegal argument
			} catch (IllegalArgumentException e) {
				// test passed.
			}
			m = new Money(-1, 0);
			assertEquals(m.toString(), "-$1.00");
			m = new Money(-1, 2);
			assertEquals(m.toString(), "-$1.02");
			try {
				m = new Money(-1, -2); // illegal argument
			} catch (IllegalArgumentException e) {
				// test passed.
			}
		} catch (NullPointerException e) {
			fail(); // i.e. for when m is null.
		} // should capture Exception exceptions?
	}

	/**
	 * DO NOT DELETE THIS This is needed for the automatic marking process.
	 **/
	// public static void main(String[] args) {
	// junit.textui.TestRunner.run(TestInheritanceModel.class);
	// }
}
