/**
 * 
 */
package org.wahlzeit.model.coordinate;

public interface Coordinate {
    CartesianCoordinate asCartesianCoordinate();

    double getCartesianDistance(Coordinate coordinate);

    SphericCoordinate asSphericCoordinate();

    double getSphericDistance(Coordinate corrdinate);

    double getDistance(Coordinate coordinate);

    boolean isEqual(Coordinate coordinate);
}
