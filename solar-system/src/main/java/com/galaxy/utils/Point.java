package com.galaxy.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //pitagorean equation for distance
    public double distance(Point point) {
        double distance = Math.sqrt(Math.pow((this.x - point.getX()), 2) + Math.pow((this.y - point.getY()), 2));
        BigDecimal bdDistance = new BigDecimal(distance);
        bdDistance = bdDistance.setScale(2, RoundingMode.HALF_UP);
        return bdDistance.doubleValue();
    }
}


