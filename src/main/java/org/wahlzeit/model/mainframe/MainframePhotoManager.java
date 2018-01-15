/*
 * Autor: Daniel Mulzer
 *
 * Date: 15.11.2017
 *
 * Version: adap-cw05
 *
 * Class: MainframePhotoManager
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

import java.util.logging.Logger;

import org.wahlzeit.model.PhotoManager;

/**
 * A photo manager provides access to and manages mainframe photos.
 */

public class MainframePhotoManager extends PhotoManager {

    	private static final Logger log = Logger.getLogger(MainframePhotoManager.class.getName());

    	/**
    	 *
    	 */
    	public MainframePhotoManager() {
    		super();    		
    	}
}
