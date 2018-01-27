/*
 * Autor: Daniel Mulzer
 *
 * Date: 14.01.2018
 *
 * Version: adap-cw12
 *
 * Class: MainframeManager
 *
 * This file is part of the Wahlzeit MainframeType rating application.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

import org.wahlzeit.model.GlobalsManager;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.ObjectManager;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;

/**
 * @author dmkif
 *
 */
public class MainframeManager extends ObjectManager{
    
    private static final Logger log = Logger.getLogger(MainframeManager.class.getName());
    public static boolean useDatastore = true;

    private static HashMap<Integer, Mainframe> mainframeMap = new HashMap<>();
    private static HashMap<String, MainframeType> mainframeTypeMap = new HashMap<>();
    private static MainframeManager instance = new MainframeManager();
    
    private MainframeManager() {
	if(useDatastore) {
	    loadMainframeTypes();
	    loadMainframes();
	}
    }

    public synchronized static MainframeManager getInstance() {
	if(MainframeManager.instance == null) {
	    MainframeManager.instance = new MainframeManager();
	}
	
	return MainframeManager.instance;
    }
    
    public Mainframe createMainframe(String manufacturer) {
	//TODO add assert
	MainframeType mt = getInstance().getMainframeType(manufacturer);
	Mainframe result = mt.createInstance();
	if(useDatastore) {
	    writeObject(result);
	    GlobalsManager.getInstance().saveGlobals();
	}
	doAddMainframe(result);
	
	return result;
    }
    
    public Mainframe createMainframe(String manufacturer, String model) {
	Mainframe mf = createMainframe(manufacturer);
	mf.setModel(model);
	return mf;
    }

    public Mainframe createMainframe(String manufacturer, String model, int mips) {
	Mainframe mf = createMainframe(manufacturer, model);
	mf.setMillionInstructionsPerSecond(mips);
	return mf;
    }

    public MainframeType createMainframeType(String manufacturer) {
	if(manufacturer==null || manufacturer.isEmpty()){
	    throw new IllegalArgumentException("manufacturer must not be null or empty");
	}
	
	return doCreateMainframeType(manufacturer);
    }
    
    public MainframeType doCreateMainframeType(String manufacturer) {
	if(!doHasMainframeType(manufacturer)) {
		MainframeType mainframeType =  new MainframeType(manufacturer);
		if(MainframeManager.useDatastore) {
		    writeObject(mainframeType);
		    GlobalsManager.getInstance().saveGlobals();
		}
		doAddMainframeType(mainframeType);
		log.config(LogBuilder.createSystemMessage().addParameter("Added new Mainframe:", mainframeType.getManufacturer()).toString());
	}
	
	return mainframeTypeMap.get(manufacturer);
    }
    
    public synchronized MainframeType getMainframeType(String typeName) {
	if(!doHasMainframeType(typeName)) {
	    this.createMainframeType(typeName);
	}
	return mainframeTypeMap.get(typeName);
    }
    
	/**
	 * @methodtype command
	 *
	 * Load all persisted MainframeTypes. Executed when Wahlzeit is restarted.
	 */
	public void loadMainframeTypes() {
		Collection<MainframeType> existingMainframeTypes = ObjectifyService.run(new Work<Collection<MainframeType>>() {
			@Override
			public Collection<MainframeType> run() {
				Collection<MainframeType> existingMainframeTypes = new ArrayList<MainframeType>();
				readObjects(existingMainframeTypes, MainframeType.class);
				return existingMainframeTypes;
			}
		});

		for (MainframeType mainframeType : existingMainframeTypes) {
			if (!doHasMainframeType(mainframeType.getManufacturer())) {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Load MainframeType with Name", mainframeType.getManufacturer()).toString());
				doAddMainframeType(mainframeType);
			} else {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Already loaded MainframeType",mainframeType.getManufacturer()).toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All MainframeTypes loaded.").toString());
	}
	
	private void doAddMainframeType(MainframeType type)
	{
	    mainframeTypeMap.put(type.getManufacturer(), type);
	}
	
	private boolean doHasMainframeType(String manufacturer) {
	    return mainframeTypeMap.containsKey(manufacturer);
	}
	
	/**
	 * @methodtype command
	 *
	 * Load all persisted MainframeTypes. Executed when Wahlzeit is restarted.
	 */
	public void loadMainframes() {
		Collection<Mainframe> existingMainframes = ObjectifyService.run(new Work<Collection<Mainframe>>() {
			@Override
			public Collection<Mainframe> run() {
				Collection<Mainframe> existingMainframes = new ArrayList<Mainframe>();
				readObjects(existingMainframes, Mainframe.class);
				return existingMainframes;
			}
		});

		for (Mainframe mainframe : existingMainframes) {
			if (!doHasMainframe(mainframe.hashCode())) {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Load Mainframe with Name", mainframe.toString()).toString());
				doAddMainframe(mainframe);
			} else {
				log.config(LogBuilder.createSystemMessage().
						addParameter("Already loaded Mainframe",mainframe.toString()).toString());
			}
		}

		log.info(LogBuilder.createSystemMessage().addMessage("All Mainframes loaded.").toString());
	}
	
	private void doAddMainframe(Mainframe value)
	{
	    mainframeMap.put(value.hashCode(), value);
	}
	
	private boolean doHasMainframe(int id) {
	    return mainframeTypeMap.containsKey(id);
	}
	
	public static void setUseDatastore(boolean value) {
	    useDatastore = value;
	}
}
