package org.wahlzeit.model.mainframe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class MainframeTypeTest extends LocalDatastoreServiceTestConfigProvider{

    Mainframe mainframe;
    MainframeType mainframeType;
    @Before
    public void setUp() throws Throwable {
	super.before();
	MainframeManager.setUseDatastore(false);
	mainframe = MainframeManager.getInstance().createMainframe("ibm", "z14", 1337);
	mainframeType = MainframeManager.getInstance().getMainframeType("myTestType");
    }

    @Test
    public void testGetManufacturer() {
	assertSame("ibm", mainframe.getMainframeType().getManufacturer());
	assertSame("ibm", MainframeManager.getInstance().getMainframeType("ibm").getManufacturer());
	assertSame("myTestType", mainframeType.getManufacturer());
	assertNotSame(mainframeType.getManufacturer(), mainframe.getMainframeType().getManufacturer());
    }

    @Test
    public void testGetSuperType() {
	assertNull(mainframeType.getSuperType());
	mainframeType.setSuperType(mainframe.getMainframeType());
	assertNotNull(mainframeType.getSuperType());
    }

    @Test
    public void testAddSubType() {
	assertFalse(mainframeType.hasInstance(mainframe));
	mainframeType.addSubType(mainframe.getMainframeType());
	assertTrue(mainframeType.hasInstance(mainframe));
    }

}
