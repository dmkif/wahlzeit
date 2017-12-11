package org.wahlzeit.model.coordinate;

public abstract class AbstractCoordinate implements Coordinate {

    public AbstractCoordinate() {
	super();
    }

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
    
    public double getDistance(Coordinate coordinate) {
	// precondition
	assertIsNotNull(coordinate);

	return this.getCartesianDistance(coordinate);
    }

    /*
     * delegates CartesianDistance to kind class implementation
     */
    public double getCartesianDistance(Coordinate coordinate) {

	// precondition
	assertIsNotNull(coordinate);

	return this.asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    public abstract CartesianCoordinate asCartesianCoordinate();

    public abstract SphericCoordinate asSphericCoordinate();

    /*
     * delegates SphericDistance to kind class implementation
     */
    public double getSphericDistance(Coordinate coordinate) {

	// precondition
	assertIsNotNull(coordinate);

	return this.asSphericCoordinate().getSphericDistance(coordinate);
    }

    public void assertIsNotNull(Object obj) throws IllegalArgumentException {
	if(obj == null) {
	    throw new IllegalArgumentException("Invalid argument - the given object is null. this is not allowed!");
	}

    }

    public void assertIsValidDouble(double value) throws IllegalArgumentException{
	if(value==Double.NaN) {
	    throw new IllegalArgumentException("Invalid argument - the argument must not be NaN");
	}else if(value == Double.NEGATIVE_INFINITY || value == Double.POSITIVE_INFINITY) {
	    throw new IllegalArgumentException("Invalid argument - the value should not be infinity");
	};
    }
}