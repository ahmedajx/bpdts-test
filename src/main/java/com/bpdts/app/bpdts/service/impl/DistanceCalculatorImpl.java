package com.bpdts.app.bpdts.service.impl;

import com.bpdts.app.bpdts.service.DistanceCalculator;
import com.bpdts.app.bpdts.vo.LocationVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorImpl implements DistanceCalculator {

    @Value("${location.latitude}")
    private double locationLatitude;

    @Value("${location.longitude}")
    private double locationLongitude;

    @Value("${min.distance}")
    private double minimumDistance;

    @Override
    public boolean withinCertainMilesFromLocation(LocationVO location) {
        double distanceFromLondon = calculateDistanceFromLocation(location);

        return distanceFromLondon <= minimumDistance;
    }

    private double calculateDistanceFromLocation(LocationVO location) {
        LocationVO locationCompare = new LocationVO();
        locationCompare.setLatitude(locationLatitude);
        locationCompare.setLongitude(locationLongitude);

        double theta = location.getLongitude() - locationCompare.getLongitude();

        double dist = Math.sin(Math.toRadians(location.getLatitude())) * Math.sin(Math.toRadians(locationCompare.getLatitude())) +
                Math.cos(Math.toRadians(location.getLatitude())) * Math.cos(Math.toRadians(locationCompare.getLatitude())) *
                Math.cos(Math.toRadians(theta));

        dist = Math.toDegrees(Math.acos(dist));

        return dist * 60 * 1.1515;
    }
}
