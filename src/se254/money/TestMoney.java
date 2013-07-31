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

	private Money m;

	/**
	 * A trivial test.
	 */
	public void testDefaultConstructor() {
		assertTrue(m != null);
		assertEquals(m.toString(), "$0.00");
	}

	public void testCentsConstructorWithTrailingZeros() {
		m = new Money(1, 10);
		assertEquals(m.toString(), "$1.10");
	}

	public void testCentsConstructor() {
		// legal negative cases.
		m = new Money(-1, 0);
		assertEquals(m.toString(), "-$1.00");
		m = new Money(0, -1);
		assertEquals(m.toString(), "-$0.01");
		m = new Money(-1, 1);
		assertEquals(m.toString(), "-$1.01");

		// negative after positive.
		try {
			m = new Money(1, -1);
		} catch (IllegalArgumentException e) {
			// test passed.
		}

		// multiple negatives.
		try {
			m = new Money(-1, -2);
		} catch (IllegalArgumentException e) {
			// test passed.
		}

	}

	public void testHundredthConstructor() {
		Money m;

		// simple cases & trailing zeros.
		m = new Money(0, 0, 0);
		assertEquals(m.toString(), "$0.00");
		m = new Money(0, 0, 1);
		assertEquals(m.toString(), "$0.0001");
		m = new Money(0, 0, 10);
		assertEquals(m.toString(), "$0.001");
		m = new Money(0, 1, 1);
		assertEquals(m.toString(), "$0.0101");
		m = new Money(0, 1, 10);
		assertEquals(m.toString(), "$0.011");
		m = new Money(1, 0, 1);
		assertEquals(m.toString(), "$1.0001");
		m = new Money(1, 0, 10);
		assertEquals(m.toString(), "$1.001");

		// simple negative cases.
		m = new Money(0, 0, -1);
		assertEquals(m.toString(), "-$0.0001");
		m = new Money(0, 0, -10);
		assertEquals(m.toString(), "-$0.001");
		m = new Money(0, -1, 0);
		assertEquals(m.toString(), "-$0.01");
		m = new Money(-1, 0, 0);
		assertEquals(m.toString(), "-$1.00");

		// legal: multi-param negative cases.
		m = new Money(0, -1, 1);
		assertEquals(m.toString(), "-$0.1001");
		m = new Money(-1, 0, 1);
		assertEquals(m.toString(), "-$1.0001");
		m = new Money(-1, 0, 10);
		assertEquals(m.toString(), "-$1.001");

		// illegal: negative after positive.
		try {
			m = new Money(0, 1, -1);
		} catch (IllegalArgumentException e) {
			// test passed.
		}
		try {
			m = new Money(1, 0, -1);
		} catch (IllegalArgumentException e) {
			// test passed.
		}
		try {
			m = new Money(1, -1, 0);
		} catch (IllegalArgumentException e) {
			// test passed.
		}

		// illegal: multiple negatives.
		try {
			m = new Money(-1, -1, 0);
		} catch (IllegalArgumentException e) {
			// test passed.
		}
		try {
			m = new Money(0, -1, -1);
		} catch (IllegalArgumentException e) {
			// test passed.
		}
		try {
			m = new Money(-1, 0, -1);
		} catch (IllegalArgumentException e) {
			// test passed.
		}
		try {
			m = new Money(-1, -1, -1);
		} catch (IllegalArgumentException e) {
			// test passed.
		}

	}
	/**
	 * DO NOT DELETE THIS This is needed for the automatic marking process.
	 **/
	// public static void main(String[] args) {
	// junit.textui.TestRunner.run(TestInheritanceModel.class);
	// }
}
