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
		m = new Money(0, -99);
		assertEquals("-$0.99", m.toString());
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
	
	public void testCentsConstructorWithLargeParams(){
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

	public void testHundredthsConstructorWithLargeParams() {
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
	// (These test depend on the previous tests passing.)

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
		m = new Money(1, 2, 1);
		o = new Money(1, 3);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(1, 1);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
	}

	public void testCompareToForPositiveVsHundredthsDiff() {
		Money o;
		m = new Money(1, 1, 2);
		o = new Money(1, 1, 3);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(1, 1, 1);
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
		o = new Money(0, 1);
		assertEquals(-1, m.compareTo(o));
		assertEquals(1, o.compareTo(m));
		o = new Money(0, 0, 1);
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
		o = new Money(0, -1);
		assertEquals(1, m.compareTo(o));
		assertEquals(-1, o.compareTo(m));
		o = new Money(0, 0, -1);
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
		int i;
		try {
			m = new Money();
			i = m.compareTo(null);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid: null argument", e.getMessage());
		}	
	}

	// test for equals() method.
	
	public void testEqualsForEqualMoney() {
		m = new Money(-2, 2);
		assertEquals(true, m.equals(new Money(-2, 2)));
	}

	public void testEqualsForUnequalMoney() {
		m = new Money(2, 2);
		assertEquals(false, m.equals(new Money(-2, 2)));
	}

	public void testEqualsForNull() {
		m = new Money(2, 2);
		assertEquals(false, m.equals(null));
	}

	// tests for multiply() method.
	//
	// factor > 1		
	
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

	// factor == 1

	public void testMultiplyByOne(){
		m = new Money(2, 2, 2);
		assertEquals("$2.0202", (m.multiply(1.0)).toString());
		m = new Money(-2, 2, 2);
		assertEquals("-$2.0202", (m.multiply(1.0)).toString());
	}
	
	// 0 < factor < 1
	 
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

	public void testDividePositiveAndNeedingToRound5Javaly(){
		m = new Money(6, 2, 2);
		assertEquals("$1.5051", (m.multiply(0.25)).toString());
	}

	public void testDividePositiveAndNeedingToRoundDown(){
		m = new Money(6, 0, 96);
		assertEquals("$1.2019", (m.multiply(0.2)).toString());
	}

	// factor <= 0

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

	public void testDivideNegativeAndNeedingToRound5Javaly(){
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
	//
	// adding positive

	public void testAddByPositiveWithNoCarries (){
		m = new Money(5, 5, 5);
		assertEquals("$10.101", (m.add(new Money(5, 5, 5))).toString());
	}
	public void testAddByPositiveWithSequencialCarries (){
		m = new Money(1, 99, 1);
		assertEquals("$2.00", (m.add(new Money(0, 0, 99))).toString());	
	}
	public void testAddByPositiveWithParallelCarries (){
		m = new Money(1, 50, 50);
		assertEquals("$3.01", (m.add(new Money(1, 50, 50))).toString());
	}

	// adding negative

	public void testAddByNegativeWithNoBorrows (){
		m = new Money(2, 2, 2);
		assertEquals("$1.0101", (m.add(new Money(-1, 1, 1))).toString());
	}

	public void testAddByNegativeWithSequencialBorrows (){
		m = new Money(1, 0, 2);
		assertEquals("$0.9999", (m.add(new Money(0, 0, -3))).toString());
	}

	public void testAddByNegativeWithParallelBorrows (){
		m = new Money(2, 2, 2);
		assertEquals("$1.9798", (m.add(new Money(0, -4, 4))).toString());
	}

	public void testAddNegativeToPositiveGettingNegative(){
		m = new Money(2, 2, 2);
		assertEquals("-$3.0303", (m.add(new Money(-5, 5, 5))).toString());
	}

	public void testAddNegativeToPositiveGettingZero(){
		m = new Money(2, 2, 2);
		assertEquals("$0.00", (m.add(new Money(-2, 2, 2))).toString());
	}

	// adding zero

	public void testAddByZero(){
		m = new Money(2, 2, 2);
		assertEquals("$2.0202", (m.add(new Money())).toString());
	}

	// adding null
	
	public void testAddByNull(){
		m = new Money(2, 2);
		try{
			Money o = m.add(null);
			fail("Note: Should have thrown IllegalArgumentException.");
		} catch(IllegalArgumentException e){
			assertEquals("Invalid: null argument", e.getMessage());
		}
	}

	// negative adding
	
	public void testAddToNegativeGettingNegative(){
		m = new Money(-2, 2, 2);
		assertEquals("-$1.0101", (m.add(new Money(1, 1, 1))).toString());
		m = new Money(-1, 50, 50);
		assertEquals("-$3.01", (m.add(new Money(-1, 50, 50))).toString());
	}

	public void testAddToNegativeGettingZero(){
		m = new Money(-2, 2, 2);
		assertEquals("$0.00", (m.add(new Money(2, 2, 2))).toString());
	}

	public void testAddToNegativeGettingPositive(){
		m = new Money(-2, 2, 2);
		assertEquals("$3.0303", (m.add(new Money(5, 5, 5))).toString());
	}

	/**
	 * DO NOT DELETE THIS This is needed for the automatic marking process.
	 **/
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestMoney.class);
	}
}
