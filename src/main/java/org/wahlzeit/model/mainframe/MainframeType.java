/*
 * Autor: Daniel Mulzer
 *
 * Date: 14.01.2018
 *
 * Version: adap-cw12
 *
 * Class: MainframeType
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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.ObjectManager;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class MainframeType extends DataObject {
    
    @Id
    protected Long idLong;
    @Parent
    protected Key parent = ObjectManager.applicationRootKey;

    @Ignore
    protected MainframeType superType = null;
    
    @Container
    protected Set<MainframeType> subTypes = new HashSet<>();

    private String manufacturer;
    
    /**
     * @Methodtype constructor
     */
    public MainframeType(String manufacturer) {
	this.manufacturer = manufacturer;
	incWriteCount();
    }
    
    /**
     * @Methodtype get
     */
    public String getManufacturer() {
        return this.manufacturer;
    }

    /**
     * @Methodtype get
     */
    public MainframeType getSuperType() {
	return this.superType;
    }

    /**
     * @Methodtype query
     */
    public Iterator<MainframeType> getSubTypeIterator() {
	return this.subTypes.iterator();
    }

    /**
     * 
     * @Methodtype command
     */
    public void addSubType(MainframeType mt) {
	assert (mt != null) : "tried to set null sub-type";
	mt.setSuperType(this);
	subTypes.add(mt);
    }

    /**
     * 
     * @Methodtype command
     */
    public void setSuperType(MainframeType mainframeType) {
	this.superType = mainframeType;	
	incWriteCount();
    }

    /**
     * 
     * @Methodtype factory
     */
    public Mainframe createInstance() {
	return new Mainframe(this, "example_model", 1);
    }

    /**
     * 
     * @Methodtype query
     */
    public boolean hasInstance(Mainframe mainframe) {
	assert (mainframe != null) : "asked about null object";
	if (mainframe.getMainframeType() == this) {
	    return true;
	}
	for(MainframeType type: subTypes) {
	    if(type.hasInstance(mainframe)) {
		return true;
	    }
	}
	return false;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	return Objects.hash(this.manufacturer);
    }
}
