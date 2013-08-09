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
	// =====
	// These tests are mainly for detecting IUT behaviour when
	// dealing with unusual - i.e. negative or large absolute value - 
	// arguments during construction.

	public void testCentsConstructorSimple() {
		m = new Money(0, 10);
		assertEquals("$0.10", m.toString());
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
	
	public void testCentsConstructorWithIllegalLargeParams(){
		try{
			m = new Money(0,100);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid value: out of range", e.getMessage());
		}
		try{
			m = new Money(0,-100);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid value: out of range", e.getMessage());
		}
	}

	// tests for Hundredths Constructor
	// =====
	// These tests are based on same considerations as the tests
	// for the Cents Constructor.

	public void testHundredthsConstructorWithTrailingZeros() {
		m = new Money(0, 0, 10);
		assertEquals("$0.001", m.toString());
	}

	public void testHundredthsConstructorWithLegalSingleNegative() {
		m = new Money(0, 0, -1);
		assertEquals("-$0.0001", m.toString());
		m = new Money(0, -1, 0);
		assertEquals("-$0.01", m.toString());
		m = new Money(-1, 0, 0);
		assertEquals("-$1.00", m.toString());
	}

	public void testHundredthsConstructorWithLegalLeadingNegative() {
		m = new Money(0, -1, 1);
		assertEquals(m.toString(), "-$0.0101");
		m = new Money(-1, 0, 1);
		assertEquals(m.toString(), "-$1.0001");
		m = new Money(-1, 1, 0);
		assertEquals(m.toString(), "-$1.01");
		m = new Money(-1, 1, 1);
		assertEquals(m.toString(), "-$1.0101");
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
		try {
			m = new Money(1, 1, -1);
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

	public void testHundredthsConstructorWithIllegalLargeParams() {
		try{
			m = new Money(0, 0, 100);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid value: out of range", e.getMessage());
		}
		try{
			m = new Money(0, 0, -100);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid value: out of range", e.getMessage());
		}
		try{
			m = new Money(0, 100, 0);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid value: out of range", e.getMessage());
		}
		try{
			m = new Money(0, -100, 0);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid value: out of range", e.getMessage());
		}
	}

	// tests for compareTo() method.
	// =====
	// The outcome of these tests would be relevant ONLY IF 
	// the previous tests (namely the tests for constructors) have
	// all successfully passed.
	// JUnit "Errors" certainly still indicate a failure.
	//
	// All these tests are testing on a Money object with all
	// three of Dollars, Cents, and Hundredths having a value.
	// (The case on $0.00 may be an exception to this.)
	//
	// The tests 
	// -	first partition on whether the Money object "m" to test 
	// 	represents a positive/zero/negative amount, 
	// -	then partition on which part of the other money object
	// 	(namely "o") is different from m

	public void testCompareToForPositiveVsSame() {
		Money o;
		m = new Money(2, 2, 2);
		o = new Money(2, 2, 2);
		assertEquals(0, m.compareTo(o));
		assertEquals(0, o.compareTo(m));
	}

	public void testCompareToForPositiveVsDollarDiff() {
		Money o;
		m = new Money(2, 2, 2);
		o = new Money(3, 0);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(1, 0);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForPositiveVsCentsDiff() {
		Money o;
		m = new Money(2, 2, 2);
		o = new Money(2, 3);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(2, 1);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForPositiveVsHundredthsDiff() {
		Money o;
		m = new Money(2, 2, 2);
		o = new Money(2, 2, 3);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(2, 2, 1);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForPositiveVsNonpositive() {
		Money o;
		m = new Money(1, 1, 1);
		o = new Money(-1, 0);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
		o = new Money(0, -1);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
		o = new Money(0, 0, -1);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
		o = new Money(0, 0, 0);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForZeroVsPositive() {
		Money o;
		m = new Money(0, 0);
		o = new Money(1, 0);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
	}

	public void testCompareToForZeroVsZero() {
		Money o;
		m = new Money(0, 0);
		o = new Money(0, 0);
		assertEquals(0, m.compareTo(o));
		assertEquals(0, o.compareTo(m));
	}

	public void testCompareToForZeroVsNegative() {
		Money o;
		m = new Money(0, 0);
		o = new Money(-1, 0);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForNegativeVsNonnegative() {
		Money o;
		m = new Money(-1, 0);
		o = new Money(0, 0);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(0, 0, 1);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(0, 1);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(1, 0);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
	}

	public void testCompareToForNegativeVsDollarDiff() {
		Money o;
		m = new Money(-2, 2, 2);
		o = new Money(-1, 0);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(-3, 0);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForNegativeVsCentsDiff() {
		Money o;
		m = new Money(-2, 2, 2);
		o = new Money(-2, 1);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(-2, 3);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForNegativeVsHundredthsDiff() {
		Money o;
		m = new Money(-2, 2, 2);
		o = new Money(-2, 2, 1);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(-2, 2, 3);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToNull(){
		try {
			m = new Money();
			int i = m.compareTo(null);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: null argument", e.getMessage());
		}	
	}

	// tests for equals() method.
	// =====
	// These tests in principle are based on the same thinking as
	// the above tests for compareTo() method.
	// -	first partition on how many fields (i.e. out of Dollar,
	// 	Cents, and Hundredths) of the Money object to be tested
	// 	(namely "m") are non-trivial (i.e. non-zero).
	// - 	then partition on what part of the other Money object
	// 	is different from "m".

	public void testEqualsForDollarCentsHundredthsVsSame() {
		m = new Money(2, 2, 2);
		assertEquals(true, m.equals(new Money(2, 2, 2)));
	}

	public void testEqualsForDollarCentsHundredthsVsDollarDiff() {
		m = new Money(2, 2, 2);
		assertEquals(false, m.equals(new Money(1, 2, 2)));
	}

	public void testEqualsForDollarCentsHundredthsVsCentsDiff() {
		m = new Money(2, 2, 2);
		assertEquals(false, m.equals(new Money(2, 1, 2)));
	}

	public void testEqualsForDollarCentsHundredthsVsHundredthsDiff() {
		m = new Money(2, 2, 2);
		assertEquals(false, m.equals(new Money(2, 2, 1)));
	}

	public void testEqualsForDollarCentsHundredthsVsSignDiff() {
		m = new Money(2, 2, 2);
		assertEquals(false, m.equals(new Money(-2, 2, 2)));
	}

	public void testEqualsForCentsHundredthsVsSignDiff() {
		m = new Money(0, 2, 2);
		assertEquals(false, m.equals(new Money(0, -2, 2)));
	}

	public void testEqualsForHundredthsVsSignDiff() {
		m = new Money(0, 0, 2);
		assertEquals(false, m.equals(new Money(0, 0, -2)));
	}

	public void testEqualsForNull() {
		m = new Money();
		assertEquals(false, m.equals(null));
	}

	// tests for multiply() method.
	
	public void testMultiplyNoCarries () {
		m = new Money(1, 1, 1);
		assertEquals("$2.0202", (m.multiply(2.0)).toString());
	}
	public void testMultiplySequencialCarry (){
		m = new Money(1, 33, 50);
		assertEquals("$4.005", (m.multiply(3.0)).toString());
	}
	public void testMultiplyParallelCarry (){
		m = new Money(1, 50, 50);
		assertEquals("$3.01", (m.multiply(2.0)).toString());
	}

	public void testMultiplyByOne(){
		m = new Money(2, 2, 2);
		assertEquals("$2.0202", (m.multiply(1.0)).toString());
		m = new Money(-2, 2, 2);
		assertEquals("-$2.0202", (m.multiply(1.0)).toString());
	}
	
	public void testDivideAllFieldsDivisible (){
		m = new Money(4, 4, 4);
		assertEquals("$1.0101", (m.multiply(0.25)).toString());	
	}
	public void testDivideDollarAndCentsBroken (){
		m =  new Money(1, 30, 0);
		assertEquals("$0.325", (m.multiply(0.25)).toString());
	}
	public void testDividePositiveAndNeedingToRoundUp (){
		m = new Money(9, 9, 9);
		assertEquals("$1.8182", (m.multiply(0.2)).toString());
	}

	public void testDividePositiveAndNeedingToRound5(){
		m = new Money(6, 2, 2);
		assertEquals("$1.5051", (m.multiply(0.25)).toString());
	}

	public void testDividePositiveAndNeedingToRoundDown(){
		m = new Money(6, 0, 96);
		assertEquals("$1.2019", (m.multiply(0.2)).toString());
	}

	public void testMultiplyByNegativeSequentialCarry() {
		m = new Money(1, 33, 50);
		assertEquals("-$4.005", (m.multiply(-3.0)).toString());
	}

	public void testDivideNegativeByNegativeNeedingToRoundUp(){
		m = new Money(-9, 9, 9);
		assertEquals("$1.8182", (m.multiply(-0.2)).toString());
	}

	public void testDivideNegativeAndNeedingToRoundUp(){
		m = new Money(-6, 0, 96);
		assertEquals("-$1.2019", (m.multiply(0.2)).toString());
	}

	public void testDivideNegativeAndNeedingToRound5(){
		m = new Money(-6, 2, 2);
		assertEquals("-$1.5051", (m.multiply(0.25)).toString());
	}

	public void testDivideNegativeAndNeedingToRoundDown(){
		m = new Money(-9, 9, 9);
		assertEquals("-$1.8182", (m.multiply(0.2)).toString());
	}

	public void testMultiplyByZero(){
		m = new Money(9, 9, 9);
		assertEquals("$0.00", (m.multiply(0.0)).toString());
	}

	// tests for add() method.
	// =====
	// These tests use two independent partitions:
	// -	How many "fields" are used in the Money object to
	// 	test (namely "m") - i.e., out of the 3 of
	// 	Dollars, Cents, and Hundredths, how many are non-zero.
	// -	Whether "m" is positive/negative.
	// -	Whether the amount to add to "m" is positive/negative.
	// - 	How many Carries occured. "Carry" is as in carries 
	// 	between different "fields" when performing addition.
	
	public void testAddPositiveOneFieldWithNoCarries(){
		m = new Money(0, 0, 1);
		assertEquals("$0.0003", (m.add(new Money(0, 0, 2))).toString());
		m = new Money(0, 1, 0);
		assertEquals("$0.03", (m.add(new Money(0, 2, 0))).toString());
		m = new Money(1, 0, 0);
		assertEquals("$3.00", (m.add(new Money(2, 0, 0))).toString());
	}

	public void testAddPositiveOneFieldWithOneCarry(){
		m = new Money(0, 0, 25);
		assertEquals("$0.01", (m.add(new Money(0, 0, 75))).toString());
		m = new Money(0, 25, 0);
		assertEquals("$1.00", (m.add(new Money(0, 75, 0))).toString());
	}

	public void testAddPositiveOneFieldWithTwoCarries(){
		// no such case!
	}

	public void testAddPositiveTwoFieldsWithNoCarries(){
		m = new Money(0, 1, 1);
		assertEquals("$0.0303", (m.add(new Money(0, 2, 2))).toString());
		m = new Money(1, 0, 1);
		assertEquals("$3.0003", (m.add(new Money(2, 0, 2))).toString());
		m = new Money(1, 1, 0);
		assertEquals("$3.03", (m.add(new Money(2, 2, 0))).toString());
	}

	public void testAddPositiveTwoFieldsWithOneCarry(){
		m = new Money(0, 25, 1);
		assertEquals("$1.0003", (m.add(new Money(0, 75, 2))).toString());
		m = new Money(1, 0, 25);
		assertEquals("$1.01", (m.add(new Money(0, 0, 75))).toString());
		m = new Money(1, 25, 0);
		assertEquals("$2.00", (m.add(new Money(0, 75, 0))).toString());
		m = new Money(0, 1, 25);
		assertEquals("$0.02", (m.add(new Money(0, 0, 75))).toString());
	}

	public void testAddPositiveTwoFieldsWithTwoCarries(){
		m = new Money(0, 25, 25);
		assertEquals("$1.01", (m.add(new Money(0, 75, 75))).toString());
		// possibly redundant test:
		m = new Money(0, 24, 25);
		assertEquals("$1.00", (m.add(new Money(0, 75, 75))).toString());
	}

	public void testAddPositiveThreeFieldsWithNoCarries(){
		m = new Money(1, 1, 1);
		assertEquals("$3.0303", (m.add(new Money(2, 2, 2))).toString());
	}

	public void testAddPositiveThreeFieldsWithOneCarry(){
		m = new Money(1, 25, 1);
		assertEquals("$2.0003", (m.add(new Money(0, 75, 2))).toString());
		m = new Money(1, 1, 25);
		assertEquals("$1.04", (m.add(new Money(0, 2, 75))).toString());
	}

	public void testAddPositiveThreeFieldsWithTwoCarries(){
		m = new Money(1, 25, 25);
		assertEquals("$2.01", (m.add(new Money(0, 75, 75))).toString());
		// possibly redundant test:
		m = new Money(1, 24, 25);
		assertEquals("$2.00", (m.add(new Money(0, 75, 75))).toString());
	}

	public void testAddNegativeOneFieldWithNoCarry(){
		m = new Money(0, 0, -1);
		assertEquals("-$0.0003", (m.add(new Money(0, 0, -2))).toString());
		m = new Money(0, -1);
		assertEquals("-$0.03", (m.add(new Money(0, -2))).toString());
		m = new Money(-1, 0);
		assertEquals("-$3.00", (m.add(new Money(-2, 0))).toString());
	}

	public void testAddNegativeOneFieldWithOneCarry(){
		m = new Money(0, 0, -25);
		assertEquals("-$0.01", (m.add(new Money(0, 0, -75))).toString());
		m = new Money(0, -25);
		assertEquals("-$1.00", (m.add(new Money(0, -75))).toString());
	}

	public void testAddNegativeOneFieldWithTwoCarries(){
		// no such case!	
	}

	public void testAddNegativeTwoFieldsWithNoCarries(){
		m = new Money(0, -1, 1);
		assertEquals("-$0.0303", (m.add(new Money(0, -2, 2))).toString());
		m = new Money(-1, 0, 1);
		assertEquals("-$3.0003", (m.add(new Money(-2, 0, 2))).toString());
		m = new Money(-1, 1);
		assertEquals("-$3.03", (m.add(new Money(-2, 2))).toString());
	}

	public void testAddNegativeTwoFieldsWithTwoCarries(){
		m = new Money(0, -25, 25);
		assertEquals("-$1.01", (m.add(new Money(0, -75, 75))).toString());
	}

	public void testAddNegativeThreeFieldsWithTwoCarries(){
		m = new Money(-1, 25, 25);
		assertEquals("-$2.01", (m.add(new Money(0, -75, 75))).toString());
	}

	// tests for adding Money objects representing amounts of different sign.

	public void testAddNegativeOneFieldBecomingPositiveWithNoCarries(){
		m = new Money(0, 0, -1);
		assertEquals("$0.0001", (m.add(new Money(0, 0, 2))).toString());
		m = new Money(0, -1);
		assertEquals("$0.01", (m.add(new Money(0, 2))).toString());
		m = new Money(-1, 0);
		assertEquals("$1.00", (m.add(new Money(2, 0))).toString());
	}
	
	public void testAddNegativeOneFieldBecomingPositiveWithOneCarry(){
		m = new Money(0, 0, -25);
		assertEquals("$0.0075", (m.add(new Money(0, 1, 0))).toString());
		m = new Money(0, -25);
		assertEquals("$0.75", (m.add(new Money(1, 0))).toString());
	}

	public void testAddNegativeOneFieldBecomingPositiveWithTwoCarries(){
		// no such case!
	}

	public void testAddNegativeTwoFieldsBecomingPositiveWithNoCarries(){
		m = new Money(0, -1, 1);
		assertEquals("$0.0101", (m.add(new Money(0, 2, 2))).toString());
		m = new Money(-1, 1);
		assertEquals("$1.01", (m.add(new Money(2, 2))).toString());
		m = new Money(-1, 0, 1);
		assertEquals("$1.0001", (m.add(new Money(2, 0, 2))).toString());
	}

	public void testAddNegativeTwoFieldsBecomingPositiveWithOneCarry(){
		m = new Money(0, -1, 1);
		assertEquals("$0.9901", (m.add(new Money(1, 0, 2))).toString());
		m = new Money(0, -1, 1);
		assertEquals("$0.0099", (m.add(new Money(0, 2, 0))).toString());
	}

	public void testAddNegativeTwoFieldsBecomingPositiveWithTwoCarries(){
		// omitted.
	}

	public void testAddNegativeThreeFieldsBecomingPositiveWithNoCarries(){
		m = new Money(-1, 1, 1);
		assertEquals("$1.0101", (m.add(new Money(2, 2, 2))).toString());
	}

	public void testAddNegativeThreeFieldsBecomingPositiveWithOneCarry(){
		// unknown. ???
	}

	public void testAddNegativeThreeFieldsBecomingPositiveWithTwoCarries(){
		m = new Money(-1, 1, 1);
		assertEquals("$0.9899", (m.add(new Money(2, 0))).toString());	
	}

	/**
	 * DO NOT DELETE THIS This is needed for the automatic marking process.
	 **/
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestMoney.class);
	}
}
