/**
 * 
 */
package org.wahlzeit.utils;

/**
 * 
 * A set of utility functions for basic double handling.
 *
 */
public class DoubleUtil {
    public static final double DELTA = 0.0000001;
    
    public static boolean isDoubleEqual(double firstValue, double secondValue){
	return (Math.abs(firstValue - secondValue) < DELTA);
    }

}
