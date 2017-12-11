/**
 * 
 */
package org.wahlzeit.model.coordinate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.utils.DoubleUtil;

public class SphericCoordinateTest {

    private static final double TESTVALUE = 13.37;

    private SphericCoordinate sphericCoord;
    private SphericCoordinate sphericOtherCoord;
    private CartesianCoordinate cartesianCoord;
    private CartesianCoordinate cartesianOtherCoord;
    private CartesianCoordinate cartesianPrimitivCoord;

    @Before
    public void setUp() throws Exception {

	sphericCoord = new SphericCoordinate(49.597675, 12.273710, SphericCoordinate.DEFAULT_RADIUS);
	sphericOtherCoord = new SphericCoordinate(33.058975, -111.244683, SphericCoordinate.DEFAULT_RADIUS);

	cartesianCoord = new CartesianCoordinate(4762.6374858, 926.9484737, 4129.8385845);
	cartesianOtherCoord = new CartesianCoordinate(-1664.1323845, -3567.1235456, 5679.1234567);
	cartesianPrimitivCoord = new CartesianCoordinate(1000, 1000, 1000);
    }

    /**
     * Test method for constructors
     */
    @Test
    public void testHashCode() {
	assertEquals(sphericCoord.hashCode(), sphericCoord.asCartesianCoordinate().asSphericCoordinate().hashCode());
	assertEquals(sphericCoord.hashCode(),
		new SphericCoordinate(sphericCoord.getLatitude(), sphericCoord.getLongitude(), sphericCoord.getRadius())
			.hashCode());
	assertNotEquals(sphericCoord.hashCode(), sphericCoord.asCartesianCoordinate().hashCode());
	assertNotEquals(sphericCoord, cartesianCoord.asSphericCoordinate());
    }

    /**
     * Test method for constructors
     */
    @Test
    public void testSphericCoordinate() {
	assertEquals(SphericCoordinate.class, new SphericCoordinate().getClass());
	assertEquals(SphericCoordinate.class, new SphericCoordinate(1.11, 3.33).getClass());
	assertEquals(SphericCoordinate.class, new SphericCoordinate(1.11, 3.33, 4.44).getClass());
	try {
	    new SphericCoordinate(-12345.0013, 0.00);
	    fail();
	} catch (IllegalArgumentException ex) {
	}

	try {
	    new SphericCoordinate(49.00, -192.00);
	    fail();
	} catch (IllegalArgumentException ex) {
	}
	try {
	    new SphericCoordinate(90.00, -180.00, -10.00);
	    fail();
	} catch (IllegalArgumentException ex) {
	}
    }

    /**
     * Test method for transforming as Cartesian coordinate
     */
    @Test
    public void testAsCartesianCoordinate() {
	assertEquals(CartesianCoordinate.class, sphericCoord.asCartesianCoordinate().getClass());
	assertNotEquals(CartesianCoordinate.class, sphericCoord.getClass());
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#asSphericCoordinate()}.
     */
    @Test
    public void testAsSphericCoordinate() {
	assertEquals(SphericCoordinate.class, sphericCoord.getClass());
	assertNotEquals(SphericCoordinate.class, sphericCoord.asCartesianCoordinate().getClass());
	assertEquals(SphericCoordinate.class, sphericCoord.asSphericCoordinate().getClass());
	assertEquals(SphericCoordinate.class, sphericCoord.asCartesianCoordinate().asSphericCoordinate().getClass());
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
	assertTrue(sphericCoord.equals(sphericCoord));
	assertFalse(sphericCoord.equals(cartesianCoord));
	try {
	    sphericCoord.equals(null);
	    fail("invalid assertion passed!");
	}catch(IllegalArgumentException er) {
	    
	}
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#getCartesianDistance(org.wahlzeit.model.coordinate.Coordinate)}.
     */
    @Test
    public void testGetCartesianDistance() {
	assertEquals(11195.5976476, sphericCoord.getCartesianDistance(sphericOtherCoord), DoubleUtil.DELTA);
	assertEquals(11195.5976476, sphericCoord.getCartesianDistance(sphericOtherCoord.asCartesianCoordinate()),
		DoubleUtil.DELTA);
	assertEquals(4415.1925623, sphericCoord.getCartesianDistance(cartesianCoord), DoubleUtil.DELTA);
	assertEquals(4415.1925623, cartesianCoord.getCartesianDistance(sphericCoord), DoubleUtil.DELTA);
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#getDistance(org.wahlzeit.model.coordinate.Coordinate)}.
     */
    @Test
    public void testGetDistance() {
	assertEquals(11195.5976476, sphericCoord.getDistance(sphericOtherCoord), DoubleUtil.DELTA);
	assertEquals(11195.5976476, sphericCoord.getDistance(sphericOtherCoord.asCartesianCoordinate()),
		DoubleUtil.DELTA);
	assertEquals(4415.1925623, sphericCoord.getDistance(cartesianCoord), DoubleUtil.DELTA);
	assertNotEquals(sphericCoord.getSphericDistance(cartesianCoord),
		sphericCoord.asCartesianCoordinate().getDistance(cartesianCoord));
	// usually next two testcases have the same distance ...
	// is it true, because we meassure the distance on a sphere in only one
	// direction
	// so the distance between point a and point b maybe different as distance point
	// b and point a
	assertEquals(3198.8100886, sphericCoord.getSphericDistance(cartesianPrimitivCoord), DoubleUtil.DELTA);
	assertEquals(5226.9015275, cartesianPrimitivCoord.asSphericCoordinate().getDistance(sphericCoord),
		DoubleUtil.DELTA);

    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#getLatitude()}.
     */
    @Test
    public void testGetLatitude() {
	assertFalse(DoubleUtil.isDoubleEqual(TESTVALUE, sphericCoord.getLatitude()));

	sphericCoord = new SphericCoordinate(TESTVALUE, 0.0);
	assertTrue(DoubleUtil.isDoubleEqual(TESTVALUE, sphericCoord.getLatitude()));
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#getLongitude()}.
     */
    @Test
    public void testGetLongitude() {
	assertFalse(DoubleUtil.isDoubleEqual(TESTVALUE, sphericCoord.getLongitude()));

	sphericCoord = new SphericCoordinate(0.0, TESTVALUE);
	assertTrue(DoubleUtil.isDoubleEqual(TESTVALUE, sphericCoord.getLongitude()));
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#getRadius()}.
     */
    @Test
    public void testGetRadius() {
	assertFalse(DoubleUtil.isDoubleEqual(TESTVALUE, sphericCoord.getRadius()));

	sphericCoord = new SphericCoordinate(0.0, 0.0, TESTVALUE);
	assertTrue(DoubleUtil.isDoubleEqual(TESTVALUE, sphericCoord.getRadius()));
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#getSphericDistance(org.wahlzeit.model.coordinate.Coordinate)}.
     */
    @Test
    public void testGetSphericDistance() {
	assertEquals(9270.4512616, sphericCoord.getSphericDistance(sphericOtherCoord), DoubleUtil.DELTA);
	assertEquals(6670.8640116, sphericCoord.getSphericDistance(sphericOtherCoord.asCartesianCoordinate()),
		DoubleUtil.DELTA);
	assertEquals(5487.6320538, sphericCoord.getSphericDistance(cartesianCoord), DoubleUtil.DELTA);
	assertEquals(5488.1643714, cartesianCoord.getSphericDistance(sphericCoord), DoubleUtil.DELTA);
	assertEquals(cartesianCoord.getSphericDistance(sphericCoord),
		cartesianCoord.asSphericCoordinate().getSphericDistance(sphericCoord), DoubleUtil.DELTA);
    }

    /**
     * Test method for
     * {@link org.wahlzeit.model.coordinate.SphericCoordinate#isEqual(org.wahlzeit.model.coordinate.Coordinate)}.
     */
    @Test
    public void testIsEqual() {
	CartesianCoordinate tmpCoordinate = sphericCoord.asCartesianCoordinate();
	assertTrue(sphericCoord.isEqual(sphericCoord));
	assertTrue(sphericCoord
		.isEqual(new CartesianCoordinate(tmpCoordinate.getX(), tmpCoordinate.getY(), tmpCoordinate.getZ())));
	assertFalse(sphericCoord.isEqual(sphericOtherCoord));
	assertFalse(sphericOtherCoord.isEqual(sphericOtherCoord.asCartesianCoordinate()));
    }

}
