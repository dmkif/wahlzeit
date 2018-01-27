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
package org.wahlzeit.model.mainframe;

import org.wahlzeit.model.Location;
import org.wahlzeit.model.Photo;
import org.wahlzeit.model.PhotoId;

import com.googlecode.objectify.annotation.Container;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Subclass;

/**
 * @author dmkif
 *
 */
@Subclass
public class MainframePhoto extends Photo {
    
    private Mainframe mainframe;

    /**
     * @Methodtype constructor
     */
    public MainframePhoto() {
	super();
	this.mainframe = MainframeManager.getInstance().createMainframe("default_manufacturer", "default_model", 1);
    }

    /**
     * @Methodtype constructor
     */
    public MainframePhoto(PhotoId myId) {
	super(myId);

	this.mainframe = MainframeManager.getInstance().createMainframe("default_manufacturer", "default_model", 1);
    }

    public MainframePhoto(PhotoId myId, Location location) {
	super(myId);
	this.setLocation(location);
    }

    public MainframePhoto(int millionInstructionsPerSecond, String manufacturer, String model) {
	super();
	this.mainframe = MainframeManager.getInstance().createMainframe(manufacturer, model,
		millionInstructionsPerSecond);
    }

    /**
     * @MethodType get
     */
    public Mainframe getMainframe() {
	return this.mainframe;
    }

    public void setMainframe(Mainframe mf) {
	this.mainframe = mf;
    }
}
