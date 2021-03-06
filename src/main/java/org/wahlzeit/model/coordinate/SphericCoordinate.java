/**
 *
 */
package org.wahlzeit.model.coordinate;

import java.util.HashMap;
import java.util.Objects;

import org.wahlzeit.utils.DoubleUtil;

import com.googlecode.objectify.annotation.Subclass;

/**
 * @author dmkif
 *
 */
/**
 * @author dmkif
 *
 */
@Subclass
public class SphericCoordinate extends AbstractCoordinate {

    static final double DEFAULT_LATITUDE = 0.0;

    static final double DEFAULT_LONGITUDE = 0.0;

    static final double DEFAULT_RADIUS = 6371.0; // km
    
    private final static HashMap<Integer, SphericCoordinate> coordinateMap = new HashMap<>();

    private final double latitude;
    private final double longitude;
    private final double radius;



    /**
     * @methodtype constructor
     */
    private SphericCoordinate(double latitude, double longitude) throws IllegalArgumentException {
	this(latitude, longitude, SphericCoordinate.DEFAULT_RADIUS);
    }

    /**
     * @methodtype constructor
     */
    public SphericCoordinate(double latitude, double longitude, double radius) throws IllegalArgumentException {
	// precondition
	assertIsValidLatitude(latitude);
	assertIsValidLongitude(longitude);
	assertIsValidRadius(radius);

	this.latitude = latitude;
	this.longitude = longitude;
	this.radius = radius;

	// postcondition
	assertClassInvariants();
    }

    
    /**
     * @methodtype helper
     */
    public static SphericCoordinate getInstance(double latitude, double longitude, double radius) {
	SphericCoordinate tempCoordinate = new SphericCoordinate(latitude, longitude, radius);
	
	if(!coordinateMap.containsKey(tempCoordinate.hashCode())) {
	    synchronized(coordinateMap) {
		if(!coordinateMap.containsKey(tempCoordinate.hashCode())) {
		    coordinateMap.put(tempCoordinate.hashCode(), tempCoordinate);
		}
	    }
	}
	return coordinateMap.get(tempCoordinate.hashCode());
    }
    
    /**
     * @methodtype helper
     */
    public static SphericCoordinate getInstance(double latitude, double longitude) {
	return getInstance(latitude, longitude, DEFAULT_RADIUS);
    }
    
    /**
     * @methodtype helper
     */
    public static SphericCoordinate getInstance() {
	return getInstance(DEFAULT_LATITUDE, DEFAULT_LONGITUDE, DEFAULT_RADIUS);
    }
    
    /**
     * @methodtype query transform a SphericCoordinate as a CartesianCoordinate
     *             source: http://keisan.casio.com/exec/system/1359534351
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
	assertClassInvariants();
	double radLatitude = Math.toRadians(this.getLatitude());
	double radLongitude = Math.toRadians(this.getLongitude());

	double x = this.getRadius() * Math.cos(radLatitude) * Math.sin(radLongitude);
	double y = this.getRadius() * Math.sin(radLatitude) * Math.sin(radLongitude);
	double z = this.getRadius() * Math.cos(radLongitude);

	return CartesianCoordinate.getInstance(x, y, z);
    }

    /**
     * @methodtype query
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
	assertClassInvariants();
	return this;
    }

    /**
     * @methodtype query
     */
    public double getLatitude() {
	return this.latitude;
    }

    /**
     * @methodtype query
     */
    public double getLongitude() {
	return this.longitude;
    }

    /**
     * @methodtype query
     */
    public double getRadius() {
	return this.radius;
    }

    /**
     * @methodtype query calculate the difference between to spheric coordinates.
     *
     */
    @Override
    public double getSphericDistance(Coordinate coordinate) {
	// precondition
	assertIsNotNull(coordinate);
	assertClassInvariants();

	double radLatitude = Math.toRadians(this.getLatitude());
	double radLongitude = Math.toRadians(this.getLongitude());

	double otherRadLatitude = Math.toRadians(coordinate.asSphericCoordinate().getLatitude());
	double otherRadLongitude = Math.toRadians(coordinate.asSphericCoordinate().getLongitude());

	double deltaLat = radLatitude - otherRadLatitude;
	double deltaLng = radLongitude - otherRadLongitude;

	double angle = Math.sqrt(Math.sin((deltaLat) / 2) * Math.sin((deltaLat) / 2) + Math.cos(radLatitude)
		* Math.cos(otherRadLatitude) * (Math.sin((deltaLng) / 2) * Math.sin((deltaLng) / 2)));

	double distanceInKM = 2 * Math.asin(angle) * this.getRadius();

	// postcondition
	assertIsValidDouble(distanceInKM);

	return distanceInKM;
    }

    /**
     * @methodtype query
     */
    @Override
    public int hashCode() {
	// precondition
	assertClassInvariants();
	return Objects.hash(this.getLatitude(), this.getLongitude(), this.getRadius());
    }

    /**
     * @methodtype query 
     * check if is equal and convert if necessary
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
	// precondition
	assertIsNotNull(coordinate);
	assertClassInvariants();
	SphericCoordinate spherCoordinate = coordinate.asSphericCoordinate();
	return DoubleUtil.isDoubleEqual(this.getLatitude(), spherCoordinate.getLatitude())
		&& DoubleUtil.isDoubleEqual(this.getLongitude(), spherCoordinate.getLongitude())
		&& DoubleUtil.isDoubleEqual(this.getRadius(), spherCoordinate.getRadius());
    }

    /**
     * @throws CoordinateParameterException
     * @methodtype assertion 
     * inpspired by: https://github.com/Snengl/wahlzeit
     */
    protected void assertIsValidLatitude(double latitude) {
	assertIsValidDouble(latitude);
	if (latitude < -90 || latitude > 90) {
	    throw new IllegalArgumentException("Latitude is out of range! Value must be between -90 and 90 degree.");
	}
	;
    }

    /**
     * @throws CoordinateParameterException
     * @methodtype assertion 
     * inpspired by: https://github.com/Snengl/wahlzeit
     */
    protected void assertIsValidLongitude(double longitude) throws IllegalArgumentException {
	assertIsValidDouble(longitude);
	if (longitude < -180 || longitude > 180) {
	    throw new IllegalArgumentException("Longitude is out of range! Value must be between -180 and 180 degree.");
	}
    }

    /**
     * @throws CoordinateParameterException
     * @methodtype assertion 
     * inpspired by: https://github.com/Snengl/wahlzeit
     */
    protected void assertIsValidRadius(double radius) throws IllegalArgumentException {
	assertIsValidDouble(radius);
	if (radius < 0) {
	    throw new IllegalArgumentException("Radius is out of range! Value must be between greater than 0.");
	}
	;
    }

    /**
     * @methodtype assertion
     */
    @Override
    protected void assertClassInvariants() {
	assertIsNotNull(this);
	assertIsValidLatitude(this.getLatitude());
	assertIsValidLongitude(this.getLongitude());
	assertIsValidRadius(this.getRadius());
    }

}
