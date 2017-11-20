
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

import java.util.Objects;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.utils.DoubleUtil;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

public class CartesianCoordinate implements Coordinate {

    public static final double DEFAULT_X_COORDINATE = 0.0;

    public static final double DEFAULT_Y_COORDINATE = 0.0;

    public static final double DEFAULT_Z_COORDINATE = 0.0;

    private double x;

    private double y;
    private double z;

    public CartesianCoordinate() {
	this(CartesianCoordinate.DEFAULT_X_COORDINATE, CartesianCoordinate.DEFAULT_Y_COORDINATE,
		CartesianCoordinate.DEFAULT_Z_COORDINATE);
    }

    public CartesianCoordinate(double x, double y, double z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
	return this;
    }

    /*
     * (non-Javadoc) converts a cartesian coordinate in a spheric source:
     * http://keisan.casio.com/exec/system/1359534351
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
	double squareX = Math.pow(this.getX(), 2);
	double squareY = Math.pow(this.getY(), 2);
	double squareZ = Math.pow(this.getZ(), 2);

	double r = Math.sqrt(squareX + squareY + squareZ);
	double lat = Math.atan(this.getY() / this.getX());
	double lng = Math.atan(Math.sqrt(squareX + squareY) / this.getZ());

	return new SphericCoordinate(Math.toDegrees(lat), Math.toDegrees(lng), r);
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Coordinate) {
	    return this.isEqual((Coordinate) obj);
	}
	return false;
    }

    @Override
    public double getCartesianDistance(Coordinate coordinate) {
	if (coordinate == null) {
	    throw new IllegalArgumentException();
	}
	return this.getDistance(coordinate);
    }

    @Override
    public double getDistance(Coordinate coordinate) {
	if (coordinate == null) {
	    throw new IllegalArgumentException();
	}
	CartesianCoordinate cartCoordinate = coordinate.asCartesianCoordinate();
	short exponent = 2;
	return Math.sqrt(Math.pow(this.getX() - cartCoordinate.getX(), exponent)
		+ Math.pow(this.getY() - cartCoordinate.getY(), exponent)
		+ Math.pow(this.getZ() - cartCoordinate.getZ(), exponent));
    }

    @Override
    public double getSphericDistance(Coordinate coordinate) {
	return this.asSphericCoordinate().getDistance(coordinate);
    }

    public double getX() {
	return this.x;
    }

    public double getY() {
	return this.y;
    }

    public double getZ() {
	return this.z;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.getX(), this.getY(), this.getZ());
    }

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

    public void setX(double x) {
	this.x = x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public void setZ(double z) {
	this.z = z;
    }

}
