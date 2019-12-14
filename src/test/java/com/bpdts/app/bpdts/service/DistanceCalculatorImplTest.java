package com.bpdts.app.bpdts.service;

import com.bpdts.app.bpdts.service.impl.DistanceCalculatorImpl;
import com.bpdts.app.bpdts.vo.LocationVO;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DistanceCalculatorImplTest {

    private DistanceCalculatorImpl distanceCalculator = new DistanceCalculatorImpl();

    public DistanceCalculatorImplTest() {
        ReflectionTestUtils.setField(distanceCalculator, "minimumDistance", 50.00);
        ReflectionTestUtils.setField(distanceCalculator, "locationLatitude", 51.509865);
        ReflectionTestUtils.setField(distanceCalculator, "locationLongitude", -0.118092);
    }

    @Test
    public void calculateWhetherBoltonIsWithin50MilesFromLondon() {
        LocationVO bolton = new LocationVO();
        bolton.setLatitude(53.58333);
        bolton.setLongitude(-2.43333);

        assertFalse(distanceCalculator.withinCertainMilesFromLocation(bolton));
    }

    @Test
    public void calculateWhetherWestminsterIsWithin50MilesFromLondon() {
        LocationVO westminster = new LocationVO();
        westminster.setLatitude(51.4975);
        westminster.setLongitude(0.1357);

        assertTrue(distanceCalculator.withinCertainMilesFromLocation(westminster));
    }
}
