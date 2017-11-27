/**
 *
 */
package org.wahlzeit.model.coordinate;

import java.util.Objects;

import org.wahlzeit.utils.DoubleUtil;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class SphericCoordinate extends AbstractCoordinate {

    static final double DEFAULT_LATITUDE = 0.0;

    static final double DEFAULT_LONGITUDE = 0.0;

    static final double DEFAULT_RADIUS = 6371.0; // km

    private double latitude;
    private double longitude;
    private double radius;

    public SphericCoordinate() {
	this(SphericCoordinate.DEFAULT_LATITUDE, SphericCoordinate.DEFAULT_LONGITUDE);
    }

    public SphericCoordinate(double latitude, double longitude) {
	this(latitude, longitude, SphericCoordinate.DEFAULT_RADIUS);
    }

    public SphericCoordinate(double latitude, double longitude, double radius) {
	super.assertIsValidLatitude(latitude);
	this.assertIsValidLongitude(longitude);
	this.assertIsValidRadius(radius);

	this.latitude = latitude;
	this.longitude = longitude;
	this.radius = radius;
    }

    /*
     * transform a SphericCoordinate as a CartesianCoordinate source:
     * http://keisan.casio.com/exec/system/1359534351
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
	double radLatitude = Math.toRadians(this.getLatitude());
	double radLongitude = Math.toRadians(this.getLongitude());

	double x = this.getRadius() * Math.cos(radLatitude) * Math.sin(radLongitude);
	double y = this.getRadius() * Math.sin(radLatitude) * Math.sin(radLongitude);
	double z = this.getRadius() * Math.cos(radLongitude);

	return new CartesianCoordinate(x, y, z);
    }

    /*
     * return this object as it is ;-)
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
	return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.wahlzeit.model.Coordinate#getDistance(org.wahlzeit.model.Coordinate)
     */
    @Override
    public double getDistance(Coordinate coordinate) {
	return this.getSphericDistance(coordinate);
    }

    public double getLatitude() {
	return this.latitude;
    }

    public double getLongitude() {
	return this.longitude;
    }

    public double getRadius() {
	return this.radius;
    }

    /*
     * calculate the difference between to spheric coordinates.
     *
     */
    @Override
    public double getSphericDistance(Coordinate coordinate) {
	double radLatitude = Math.toRadians(this.getLatitude());
	double radLongitude = Math.toRadians(this.getLongitude());

	double otherRadLatitude = Math.toRadians(coordinate.asSphericCoordinate().getLatitude());
	double otherRadLongitude = Math.toRadians(coordinate.asSphericCoordinate().getLongitude());

	double deltaLat = radLatitude - otherRadLatitude;
	double deltaLng = radLongitude - otherRadLongitude;

	double angle = Math.sqrt(Math.sin((deltaLat) / 2) * Math.sin((deltaLat) / 2) + Math.cos(radLatitude)
		* Math.cos(otherRadLatitude) * (Math.sin((deltaLng) / 2) * Math.sin((deltaLng) / 2)));

	double distanceInKM = 2 * Math.asin(angle) * this.getRadius();

	return distanceInKM;
    }

    @Override
    public int hashCode() {
	return Objects.hash(this.getLatitude(), this.getLongitude(), this.getRadius());
    }

    /*
     * check if is equal and convert if necessary
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
	SphericCoordinate spherCoordinate = coordinate.asSphericCoordinate();
	return DoubleUtil.isDoubleEqual(this.getLatitude(), spherCoordinate.getLatitude())
		&& DoubleUtil.isDoubleEqual(this.getLongitude(), spherCoordinate.getLongitude())
		&& DoubleUtil.isDoubleEqual(this.getRadius(), spherCoordinate.getRadius());
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
	this.longitude = longitude;
    }

    public void setRadius(double radius) {
	this.radius = radius;
    }

}
