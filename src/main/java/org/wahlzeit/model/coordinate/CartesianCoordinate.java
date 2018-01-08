
/*
 * Autor: Daniel Mulzer
 *
 * Date: 29.10.2017
 *
 * Version: adap-cw03
 *
 * Class: Coordinate
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.model.coordinate;

import java.util.HashMap;
import java.util.Objects;

import org.wahlzeit.utils.DesignPattern;
import org.wahlzeit.utils.DoubleUtil;

import com.googlecode.objectify.annotation.Subclass;

@DesignPattern(
	name = "Singleton",
	participants = {"CartesianCoordinate"}
)
@Subclass
public class CartesianCoordinate extends AbstractCoordinate implements Coordinate {

    public static final double DEFAULT_X_COORDINATE = 0.0;

    public static final double DEFAULT_Y_COORDINATE = 0.0;

    public static final double DEFAULT_Z_COORDINATE = 0.0;

    private final static HashMap<Integer, CartesianCoordinate> coordinateMap = new HashMap<>();
    
    private final double x;
    private final double y;
    private final double z;

    /**
     * @methodtype constructor
     */
    private CartesianCoordinate() {
	this(CartesianCoordinate.DEFAULT_X_COORDINATE, CartesianCoordinate.DEFAULT_Y_COORDINATE,
		CartesianCoordinate.DEFAULT_Z_COORDINATE);
    }

    /**
     * @methodtype constructor
     */
    private CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
	// precondition
	assertIsValidX(x);
	assertIsValidY(y);
	assertIsValidZ(z);

	this.x = x;
	this.y = y;
	this.z = z;

	// postcondition
	assertClassInvariants();
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
	// precondition
	assertClassInvariants();
	return this;
    }
    
    /**
     * @methodtype helper
     */
    public static CartesianCoordinate getInstance(double x, double y, double z) {
	CartesianCoordinate tempCoordinate = new CartesianCoordinate(x, y, z);
	
	if(!coordinateMap.containsKey(tempCoordinate.hashCode())) {
	    synchronized(coordinateMap) {
		if(!coordinateMap.containsKey(tempCoordinate.hashCode())) {
		    coordinateMap.put(tempCoordinate.hashCode(), tempCoordinate);
		}
	    }
	}
	return coordinateMap.get(tempCoordinate.hashCode());
    }

    /*
     * @methodtype query (non-Javadoc) converts a cartesian coordinate in a spheric
     * source: http://keisan.casio.com/exec/system/1359534351
     */
    @Override
    public SphericCoordinate asSphericCoordinate() throws IllegalArgumentException {
	// precondition
	assertClassInvariants();

	double squareX = Math.pow(this.getX(), 2);
	double squareY = Math.pow(this.getY(), 2);
	double squareZ = Math.pow(this.getZ(), 2);

	double r = Math.sqrt(squareX + squareY + squareZ);
	double lat = Math.atan(this.getY() / this.getX());
	double lng = Math.atan(Math.sqrt(squareX + squareY) / this.getZ());

	return new SphericCoordinate(Math.toDegrees(lat), Math.toDegrees(lng), r);
    }

    /**
     * @methodtype query
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws IllegalArgumentException {
	assertIsNotNull(coordinate);
	CartesianCoordinate cartCoordinate = coordinate.asCartesianCoordinate();
	short exponent = 2;
	return Math.sqrt(Math.pow(this.getX() - cartCoordinate.getX(), exponent)
		+ Math.pow(this.getY() - cartCoordinate.getY(), exponent)
		+ Math.pow(this.getZ() - cartCoordinate.getZ(), exponent));
    }

    /**
     * @methodtype query
     */
    public double getX() {
	return this.x;
    }

    /**
     * @methodtype query
     */
    public double getY() {
	return this.y;
    }

    /**
     * @methodtype query
     */
    public double getZ() {
	return this.z;
    }

    /**
     * @methodtype query
     */
    @Override
    public int hashCode() {
	// precondition
	assertClassInvariants();
	return Objects.hash(this.getX(), this.getY(), this.getZ());
    }

    /**
     * @methodtype query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
	if (coordinate != null) {
	    CartesianCoordinate cartCoordinate = coordinate.asCartesianCoordinate();
	    return DoubleUtil.isDoubleEqual(this.getX(), cartCoordinate.getX())
		    && DoubleUtil.isDoubleEqual(this.getY(), cartCoordinate.getY())
		    && DoubleUtil.isDoubleEqual(this.getZ(), cartCoordinate.getZ());
	}
	return false;
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsValidX(double value) throws IllegalArgumentException {
	try {
	    assertIsValidDouble(value);
	} catch (IllegalArgumentException ex) {
	    throw new IllegalArgumentException("Coordinate invalid X value " + ex.getMessage());
	}
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsValidY(double value) throws IllegalArgumentException {
	try {
	    assertIsValidDouble(value);
	} catch (IllegalArgumentException ex) {
	    throw new IllegalArgumentException("Coordinate invalid Y value " + ex.getMessage());
	}
    }

    /**
     * @methodtype assertion
     */
    protected void assertIsValidZ(double value) throws IllegalArgumentException {
	try {
	    assertIsValidDouble(value);
	} catch (IllegalArgumentException ex) {
	    throw new IllegalArgumentException("Coordinate invalid Z value " + ex.getMessage());
	}
    }

    /**
     * @methodtype assertion
     */
    @Override
    protected void assertClassInvariants() {
	assertIsNotNull(this);
	assertIsValidX(this.getX());
	assertIsValidY(this.getY());
	assertIsValidZ(this.getZ());
    }

}
