/**
 * 
 */
package org.wahlzeit.utils;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author dmkif
 *
 */
public class DoubleUtilTest {

    /**
     * Test method for {@link org.wahlzeit.utils.DoubleUtil#isDoubleEqual(double, double)}.
     */
    @Test
    public void testIsDoubleEqual() {
	double firstValue = Double.MAX_VALUE;
	double secondValue = Double.MIN_VALUE;
	
	assertTrue(DoubleUtil.isDoubleEqual(firstValue, Double.MAX_VALUE));
	assertTrue(DoubleUtil.isDoubleEqual(secondValue, Double.MIN_VALUE));
	
	firstValue = DoubleUtil.DELTA *10;
	secondValue = DoubleUtil.DELTA / 10;
	
	assertFalse(DoubleUtil.isDoubleEqual(firstValue, secondValue));
	secondValue += DoubleUtil.DELTA * 10;
	assertTrue(DoubleUtil.isDoubleEqual(firstValue, secondValue));
	assertTrue(DoubleUtil.isDoubleEqual(firstValue, secondValue + 30 *(DoubleUtil.DELTA/100)));
    }

}
