package org.wahlzeit.model.coordinate;

import org.wahlzeit.utils.DesignPattern;

@DesignPattern(
	name = "Template",
	participants = {"Coordinate", "CartesianCoordinate", "SphericCoordinate"}
)
public abstract class AbstractCoordinate implements Coordinate {

    /**
     * @methodtype constructor
     */
    public AbstractCoordinate() {
	super();
    }

    /**
     * @methodtype query
     */
    @Override
    public boolean equals(Object obj) {
	// precondition
	assertIsNotNull(obj);

	if (obj instanceof Coordinate) {
	    return this.isEqual((Coordinate) obj);
	}
	return false;
    }

    public abstract boolean isEqual(Coordinate coordinate);

    protected abstract void assertClassInvariants();

    /**
     * @methodtype query
     */
    public double getDistance(Coordinate coordinate) {
	// precondition
	assertIsNotNull(coordinate);

	return this.getCartesianDistance(coordinate);
    }

    /**
     * @methodtype query delegates CartesianDistance to kind class implementation
     */
    public double getCartesianDistance(Coordinate coordinate) {

	// precondition
	assertIsNotNull(coordinate);

	return this.asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    public abstract CartesianCoordinate asCartesianCoordinate();

    public abstract SphericCoordinate asSphericCoordinate();

    /**
     * @methodtype query delegates SphericDistance to kind class implementation
     */
    public double getSphericDistance(Coordinate coordinate) {

	// precondition
	assertIsNotNull(coordinate);

	return this.asSphericCoordinate().getSphericDistance(coordinate);
    }

    /**
     * @methodtype assertion
     */
    public void assertIsNotNull(Object obj) throws IllegalArgumentException {
	if (obj == null) {
	    throw new IllegalArgumentException("Invalid argument - the given object is null. this is not allowed!");
	}

    }

    /**
     * @methodtype assertion
     */
    public void assertIsValidDouble(double value) throws IllegalArgumentException {
	if (value == Double.NaN) {
	    throw new IllegalArgumentException("Invalid argument - the argument must not be NaN");
	} else if (value == Double.NEGATIVE_INFINITY || value == Double.POSITIVE_INFINITY) {
	    throw new IllegalArgumentException("Invalid argument - the value should not be infinity");
	}
	;
    }
}