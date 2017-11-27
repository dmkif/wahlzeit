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
package org.wahlzeit.model;

import java.util.logging.Logger;

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

//    	/**
//    	 * @methodtype init Loads all Photos from the Datastore and holds them in the cache
//    	 */
//    	public void init() {
//    		loadPhotos();
//    	}

//    	/**
//    	 * @methodtype command
//    	 *
//    	 * Load all persisted photos. Executed when Wahlzeit is restarted.
//    	 */
//    	public void loadPhotos() {
//    		Collection<MainframePhoto> existingPhotos = ObjectifyService.run(new Work<Collection<MainframePhoto>>() {
//    			@Override
//    			public Collection<MainframePhoto> run() {
//    				Collection<MainframePhoto> existingPhotos = new ArrayList<MainframePhoto>();
//    				readObjects(existingPhotos, MainframePhoto.class);
//    				return existingPhotos;
//    			}
//    		});
//
//    		for (MainframePhoto photo : existingPhotos) {
//    			if (!doHasPhoto(photo.getId())) {
//    				log.config(LogBuilder.createSystemMessage().
//    						addParameter("Load Photo with ID", photo.getIdAsString()).toString());
//    				loadScaledImages(photo);
//    				doAddPhoto(photo);
//    			} else {
//    				log.config(LogBuilder.createSystemMessage().
//    						addParameter("Already loaded Photo", photo.getIdAsString()).toString());
//    			}
//    		}
//
//    		log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
//    	}
}
