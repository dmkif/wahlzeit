/*
 * Autor: Daniel Mulzer
 *
 * Date: 14.01.2018
 *
 * Version: adap-cw12
 *
 * Class: Mainframe
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

package org.wahlzeit.model.mainframe;

import java.util.Objects;

import org.wahlzeit.services.DataObject;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Mainframe extends DataObject {
    @Id Long id;
    
    @Container
    private MainframeType mainframeType;
    
    protected int millionInstructionsPerSecond = 1;
    protected String model;
    
    /**
     * 
     * @methodtype cosntructor
     */
    private Mainframe(MainframeType mainframeType) {
	this.mainframeType = mainframeType;
	incWriteCount();
    }
    
    /**
     * 
     * @methodtype constructor
     */
    public Mainframe(MainframeType mainframeType, String model, int millionInstructionsPerSecond) {
	this.mainframeType = mainframeType;
	this.model = model;
	this.millionInstructionsPerSecond = millionInstructionsPerSecond;
	incWriteCount();
    }

    private void assertIsNotNull(Object obj) {
	if(obj == null) {
	    throw new IllegalArgumentException("Value must not be null");
	}
    }

    private void assertIsValidInt(int value) {
	if(value <= 0) {
	    throw new NumberFormatException("Only integers greater than 0 are allowed");
	}
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if(obj == null) {
	    return false;
	}
	return this.hashCode() == obj.hashCode();
    }

    /**
     * 
     * @methodtype query
     */
    public MainframeType getMainframeType() {
	return mainframeType;
    }

    /**
     * @return the millionInstructionsPerSecond
     */
    public int getMillionInstructionsPerSecond() {
        return this.millionInstructionsPerSecond;
    }

    /**
     * @Methodtype get
     */
    public String getModel() {
        return this.model;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	return Objects.hash(this.mainframeType,id);
    }

    /**
     * @param millionInstructionsPerSecond the millionInstructionsPerSecond to set
     */
    public void setMillionInstructionsPerSecond(int millionInstructionsPerSecond) {
	assertIsNotNull(millionInstructionsPerSecond);
	assertIsValidInt(millionInstructionsPerSecond);
	this.millionInstructionsPerSecond = millionInstructionsPerSecond;
	incWriteCount();
    }
    
    /**
     * @param typeName the typeName to set
     */
    public void setModel(String model) {
	assertIsNotNull(model);
	if(model.isEmpty()) {
	    throw new IllegalArgumentException("Modelname must not be empty");
	}
        this.model = model;
        incWriteCount();
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Mainframe [model="+this.model+", MIPS="+this.millionInstructionsPerSecond+" manufacturer="+this.mainframeType.getManufacturer()+"]";
    }

}
