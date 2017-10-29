

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
package org.wahlzeit.model;

public class Coordinate {
    private double x;

    private double y;

    private double z;

    public Coordinate(double x, double y, double z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
	if (this.getClass().isInstance(obj)) {
	    return this.isEqual((Coordinate) obj);
	}
	return false;
    }

    public double getDistance(Coordinate coord) {

	return Math.sqrt(Math.pow(this.x - coord.x, 2) 
		+ Math.pow(this.y - coord.y, 2) 
		+ Math.pow(this.z - coord.z, 2));
    }

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    public double getZ() {
	return z;
    }
    public boolean isEqual(Coordinate cord) {
	if (cord != null) {
	    return this.x == cord.x && this.y == cord.y && this.z == cord.z;
	}
	return false;
    }

}
