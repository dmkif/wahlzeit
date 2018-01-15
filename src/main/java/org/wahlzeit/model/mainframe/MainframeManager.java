/*
 * Autor: Daniel Mulzer
 *
 * Date: 14.01.2018
 *
 * Version: adap-cw12
 *
 * Class: MainframeManager
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

import java.util.HashMap;

import org.wahlzeit.services.ObjectManager;

/**
 * @author dmkif
 *
 */
public class MainframeManager extends ObjectManager{

    private static HashMap<Integer, Mainframe> mainframeMap = new HashMap<>();
    private static HashMap<String, MainframeType> mainframeTypeMap = new HashMap<>();
    private static MainframeManager instance = new MainframeManager();
    
    private MainframeManager() {
	
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
	mainframeMap.put(result.hashCode(), result);
	
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
	if(!mainframeTypeMap.containsKey(manufacturer)) {
		MainframeType mainframeType =  new MainframeType(manufacturer);
		mainframeTypeMap.put(manufacturer, mainframeType);
	}
	
	return mainframeTypeMap.get(manufacturer);
    }
    
    public synchronized MainframeType getMainframeType(String typeName) {
	if(!mainframeTypeMap.containsKey(typeName)) {
	    this.createMainframeType(typeName);
	}
	return mainframeTypeMap.get(typeName);
    }
}
