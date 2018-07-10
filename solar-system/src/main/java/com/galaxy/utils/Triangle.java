package com.galaxy.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Triangle {
    private Point p;
    private Point q;
    private Point r;

    public Triangle(Point p,Point  q,Point  r){
        this.p = p;
        this.q = q;
        this.r = r;
    }

    public Point getP() {
        return p;
    }

    public void setP(Point p) {
        this.p = p;
    }

    public Point getQ() {
        return q;
    }

    public void setQ(Point q) {
        this.q = q;
    }

    public Point getR() {
        return r;
    }

    public void setR(Point r) {
        this.r = r;
    }

    //area of triangle is
    //1/2[x1(y2-y3)+x2(y3-y1)+x3(y1-y2)]
    public double getArea() {
        double x1 = p.getX();
        double x2 = q.getX();
        double x3 = r.getX();
        double y1 = p.getY();
        double y2 = q.getY();
        double y3 = r.getY();

        double areaOfTriangle = ((x1*(y2-y3))+(x2*(y3-y1))+(x3*(y1-y2)))*0.5;

        BigDecimal bdArea = new BigDecimal(Math.abs(areaOfTriangle));
        bdArea = bdArea.setScale(2, RoundingMode.HALF_UP);
        return bdArea.doubleValue();
    }

    public double getPerimeter(){
        double perimeter =  p.distance(q) + q.distance(r) + r.distance(p);
        BigDecimal bdArea = new BigDecimal(perimeter);
        bdArea = bdArea.setScale(2, RoundingMode.HALF_UP);
        return bdArea.doubleValue();
    }

    //A point is inside of a triangle if the sum of all the area
    //of all triangles formed with the point and 2 of the points in the triangle
    //equals the area of the original triangle
    public boolean isPointInside(Point point){
        boolean result = false;
        if(point != null){
            double area = this.getArea();
            double area1 = (new Triangle(point, this.p, this.q)).getArea();
            double area2 = (new Triangle(point, this.q, this.r)).getArea();
            double area3 = (new Triangle(point, this.r, this.p)).getArea();
            result = area == area1 + area2 + area3;
        }

        return result;
    }
}
