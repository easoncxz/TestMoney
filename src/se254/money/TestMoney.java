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

	public void testDefaultConstructor() {
		m = new Money();
		assertEquals("$0.00", m.toString());
	}

	// tests for Cents Constructor

	public void testCentsConstructorSimple() {
		m = new Money(1, 0);
		assertEquals("$1.00", m.toString());
	}

	public void testCentsConstructorWithLeadingZero() {
		m = new Money(0, 0);
		assertEquals("$0.00", m.toString());
	}

	public void testCentsConstructorWithTrailingZero() {
		m = new Money(1, 10);
		assertEquals("$1.10", m.toString());
	}

	public void testCentsConstructorWithLegalNegatives() {
		m = new Money(-1, 0);
		assertEquals("-$1.00", m.toString());
		m = new Money(0, -1);
		assertEquals("-$0.01", m.toString());
		m = new Money(-1, 1);
		assertEquals("-$1.01", m.toString());
	}

	public void testCentsConstructorWithIllegalNegativeAfterPositive() {
		try {
			m = new Money(1, -1);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: negative after non-zero", e.getMessage());
		}
	}

	public void testCentsConstructorWithIllegalMultipleNegatives() {
		try {
			m = new Money(-1, -2);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: more than one negative value",
					e.getMessage());
		}
	}

	// tests for Hundredths Constructor

	public void testHundredthsConstructorSimple() {
		m = new Money(1, 0, 0);
		assertEquals("$1.00", m.toString());
	}

	public void testHundredthsConstructorWithLeadingZero() {
		m = new Money(0, 0, 1);
		assertEquals("$0.0001", m.toString());
	}

	public void testHundredthsConstructorWithTrailingZeros() {
		m = new Money(0, 0, 10);
		assertEquals("$0.001", m.toString());
	}

	public void testHundredthsConstructorWithLegalOneNegative() {
		m = new Money(0, 0, -1);
		assertEquals("-$0.0001", m.toString());
		m = new Money(0, 0, -10);
		assertEquals("-$0.001", m.toString());
		m = new Money(0, -1, 0);
		assertEquals("-$0.01", m.toString());
		m = new Money(-1, 0, 0);
		assertEquals("-$1.00", m.toString());
	}

	public void testHundredthsConstructorWithLegalNegatives() {
		m = new Money(0, -1, 1);
		assertEquals(m.toString(), "-$0.0101");
		m = new Money(-1, 0, 1);
		assertEquals(m.toString(), "-$1.0001");
		m = new Money(-1, 1, 0);
		assertEquals(m.toString(), "-$1.01");
		m = new Money(-1, 0, 10);
		assertEquals(m.toString(), "-$1.001");
	}

	public void testHundredthsConstructorWithIllegalNegativeAfterPositive() {
		try {
			m = new Money(0, 1, -1);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: negative after non-zero", e.getMessage());
		}
		try {
			m = new Money(1, 0, -1);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: negative after non-zero", e.getMessage());
		}
		try {
			m = new Money(1, -1, 0);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: negative after non-zero", e.getMessage());
		}
	}

	public void testHundredthsConstructorWithIllegalMultipleNegatives() {
		try {
			m = new Money(-1, 0, -1);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: more than one negative value",
					e.getMessage());
		}
		try {
			m = new Money(0, -1, -1);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: more than one negative value",
					e.getMessage());
		}
		try {
			m = new Money(-1, -1, 0);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: more than one negative value",
					e.getMessage());
		}
		try {
			m = new Money(-1, -1, -1);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: more than one negative value",
					e.getMessage());
		}
	}

	// tests for compareTo() method.
	// (These test depend on the previous tests passing.)

	public void testCompareToForPositiveVsDollarDiff() {
	}

	public void testCompareToForPositiveVsCentsDiff() {
	}

	public void testCompareToForPositiveVsHundredthsDiff() {
	}

	public void testCompareToForPositiveVsNonpositive() {
	}

	public void testCompareToForZeroVsPositive() {
	}

	public void testCompareToForZeroVsZero() {
	}

	public void testCompareToForZeroVsNegative() {
	}

	public void testCompareToForNegativeVsNonnegative() {
	}

	public void testCompareToForNegativeVsDollarDiff() {
	}

	public void testCompareToForNegativeVsCentsDiff() {
	}

	public void testCompareToForNegativeVsHundredthsDiff() {
	}

	/**
	 * DO NOT DELETE THIS This is needed for the automatic marking process.
	 **/
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestMoney.class);
	}
}
