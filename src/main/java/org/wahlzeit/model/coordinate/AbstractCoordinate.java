package org.wahlzeit.model.coordinate;

public abstract class AbstractCoordinate implements Coordinate {

    public AbstractCoordinate() {
	super();
    }

    protected void assertIsValidLatitude(double latitude) throws IllegalArgumentException {
	if (latitude < -90 || latitude > 90 || Double.isNaN(latitude)) {
	    throw new IllegalArgumentException("Latitude is out of range.");
	}
    }

    protected void assertIsValidLongitude(double longitude) throws IllegalArgumentException {
	if (longitude < -180 || longitude > 180 || Double.isNaN(longitude)) {
	    throw new IllegalArgumentException("Longitude is out of range.");
	}
    }

    protected void assertIsValidRadius(double radius) throws IllegalArgumentException {
	if (radius < 0 || Double.isNaN(radius)) {
	    throw new IllegalArgumentException("Radius is out of range.");
	}
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Coordinate) {
	    return this.isEqual((Coordinate) obj);
	}
	return false;
    }

    public abstract boolean isEqual(Coordinate coordinate);

    public double getDistance(Coordinate coordinate) {
	return this.getCartesianDistance(coordinate);
    }

    /*
     * delegates CartesianDistance to kind class implementation
     */
    public double getCartesianDistance(Coordinate coordinate) {
	return this.asCartesianCoordinate().getCartesianDistance(coordinate);
    }

    public abstract CartesianCoordinate asCartesianCoordinate(); 

    public abstract SphericCoordinate asSphericCoordinate(); 

    /*
     * delegates SphericDistance to kind class implementation
     */
    public double getSphericDistance(Coordinate coordinate) {
	return this.asSphericCoordinate().getSphericDistance(coordinate);
    }

}