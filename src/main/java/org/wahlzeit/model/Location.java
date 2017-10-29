/*
 * Autor: Daniel Mulzer
 *
 * Date: 29.10.2017
 *
 * Version: adap-cw03
 *
 * Class: Location
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

/**
 * @author dmkif
 *
 */
public class Location {
    
    private Coordinate coordinate;

    public Location(Coordinate coordinate)
    {
	this.coordinate=coordinate;
    }
    /**
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
	return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(Coordinate coordinate) {
	this.coordinate = coordinate;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this.getClass().isInstance(obj)) {
	    return this.coordinate.equals(((Location) obj).getCoordinate());
	}
	return false;
    }

}
