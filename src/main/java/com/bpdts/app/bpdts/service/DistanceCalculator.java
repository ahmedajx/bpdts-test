package com.bpdts.app.bpdts.service;

import com.bpdts.app.bpdts.vo.LocationVO;

public interface DistanceCalculator {

    boolean withinCertainMilesFromLocation(LocationVO locationOne);
}
