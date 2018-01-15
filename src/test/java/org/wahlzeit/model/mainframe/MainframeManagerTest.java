package org.wahlzeit.model.mainframe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class MainframeManagerTest extends LocalDatastoreServiceTestConfigProvider{

    MainframeManager mm;
    @Before
    public void setUp() throws Throwable {
	super.before();
	mm = MainframeManager.getInstance();
    }

    @Test
    public void testGetInstance() {
	assertSame(MainframeManager.getInstance().getClass(), MainframeManager.class);
    }

    @Test
    public void testCreateMainframe() {
	assertSame(mm.createMainframe("ibm").getClass(), Mainframe.class);
	assertSame(mm.createMainframe("ibm", "z14").getClass(), Mainframe.class);
	assertSame(mm.createMainframe("ibm", "z13", 10000000).getClass(), Mainframe.class);
    }

    @Test
    public void testGetMainframeType() {
	assertSame(mm.getMainframeType("ibm").getClass(), MainframeType.class);
	assertSame(mm.getMainframeType("zelda").getClass(), MainframeType.class);
	assertSame(mm.getMainframeType("1337").getClass(), MainframeType.class);
    }

}
