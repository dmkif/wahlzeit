/*
 * Autor: Daniel Mulzer
 *
 * Date: 08.11.2017
 *
 * Version: adap-cw05
 *
 * Class: MainframePhoto
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

import com.googlecode.objectify.annotation.Subclass;

/**
 * @author dmkif
 *
 */
@Subclass
public class MainframePhoto extends Photo {

    private int millionInstructionsPerSecond;
    private String manufacturer;
    /**
     * default constructor, initialise a object with default values
     */
    public MainframePhoto() {
	this(0,"example_manufacturer");
    }

    /**
     * @param myId
     */
    public MainframePhoto(PhotoId myId) {
	this(myId, 0, "example_manufacturer");
    }
    
    public MainframePhoto(PhotoId myId, int millionInstructionsPerSecond, String manufacturer) {
	super(myId);
	this.setMillionInstructionsPerSecond(millionInstructionsPerSecond);
	this.setManufacturer(manufacturer);
    }

    public MainframePhoto(int millionInstructionsPerSecond, String manufacturer) {
	super();
	this.setMillionInstructionsPerSecond(millionInstructionsPerSecond);
	this.setManufacturer(manufacturer);
    }

    /**
     * @return the millionInstructionsPerSecond
     */
    public int getMillionInstructionsPerSecond() {
        return this.millionInstructionsPerSecond;
    }

    /**
     * @param millionInstructionsPerSecond the millionInstructionsPerSecond to set
     */
    public void setMillionInstructionsPerSecond(int millionInstructionsPerSecond) {
	if(millionInstructionsPerSecond<0) {
	    throw new NumberFormatException("out of range! value must be greater than zero");
	}
	else {
	    this.millionInstructionsPerSecond = millionInstructionsPerSecond;
	}
    }

    /**
     * @return the manufactor
     */
    public String getManufacturer() {
        return new String(this.manufacturer);
    }

    /**
     * @param manufactor the manufactor to set
     */
    public void setManufacturer(String manufactor) {
	if(manufactor != null && !manufactor.isEmpty()) {
	    this.manufacturer = new String(manufactor);
	}
	else {
	    throw new IllegalArgumentException("Manufactor must not be NULL or empty!");
	}
    }

}
