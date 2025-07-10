package com.potodev.NapRoute.util;

import com.potodev.NapRoute.enums.RadiusUnits;

public class ConversionUtil {
    public static double convertToMeters(double radius, RadiusUnits units) {
        return switch (units) {
            case KM -> radius * 1000;
            case MILES -> radius * 1609.34;
            case METER -> radius;
            default -> throw new IllegalArgumentException("Unsupported radius unit: " + units);
        };
    }

    public static double convertMetersToUnit(double meters, RadiusUnits units) {
        return switch (units) {
            case KM -> meters / 1000;
            case MILES -> meters / 1609.34;
            case METER -> meters;
            default -> throw new IllegalArgumentException("Unsupported radius unit: " + units);
        };
    }
}
