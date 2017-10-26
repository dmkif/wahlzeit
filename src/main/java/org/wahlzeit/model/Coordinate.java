/**
 * 
 */
package org.wahlzeit.model;

import javax.xml.bind.helpers.NotIdentifiableEventImpl;

import org.hamcrest.core.IsNull;

/**
 * @author dmkif
 *
 */
public class Coordinate {
	private double x;
	private double y;
	private double z;
	
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getDistance(Coordinate coord) {

		return Math.sqrt(Math.pow(this.x - coord.x, 2)+Math.pow(this.y - coord.y, 2)+Math.pow(this.z - coord.z, 2));
	}
	
	public boolean isEqual(Coordinate cord)
	{
		if(cord!=null)
		{
			return this.x== cord.x && this.y == cord.y && this.z == cord.z;
		}
		return false;
	}
}
