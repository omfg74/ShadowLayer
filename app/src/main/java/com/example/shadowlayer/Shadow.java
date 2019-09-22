package com.example.shadowlayer;

public class Shadow {
    private int radius;
    private float cornerRadius;
    private String color;
    private float alpha;
    private int x;
    private int y;

    public void setCornerRadius(float cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public int getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public float getAlpha() {
        return alpha;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
