package org.wahlzeit.model.mainframe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class MainframeTest extends LocalDatastoreServiceTestConfigProvider{

    Mainframe mainframe;
    @Before
    public void setUp() throws Throwable {
	super.before();
	mainframe = MainframeManager.getInstance().createMainframe("ibm", "z14", 1337);
    }

    @Test
    public void testGetMainframeType() {
	MainframeType testType = MainframeManager.getInstance().getMainframeType("ibm");
	assertEquals(testType, mainframe.getMainframeType());
    }

    @Test
    public void testGetMillionInstructionsPerSecond() {
	assertEquals(1337, mainframe.getMillionInstructionsPerSecond());
    }

    @Test
    public void testGetModel() {
	assertEquals("z14", mainframe.getModel());
    }

    @Test(expected=NumberFormatException.class)
    public void testSetMillionInstructionsPerSecond() {
	mainframe.setMillionInstructionsPerSecond(99999999);
	assertEquals(99999999, mainframe.getMillionInstructionsPerSecond());
	mainframe.setMillionInstructionsPerSecond(0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSetModel() {
	mainframe.setModel("WuuupWuuup");
	assertEquals("WuuupWuuup", mainframe.getModel());
	mainframe.setModel("");
    }

}
