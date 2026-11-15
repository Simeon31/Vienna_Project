package com.warehouseoptimizer.warehouse_optimization_app.modules.optimization.domain;

public final class GeoDistance {

    private static final double EARTH_RADIUS_KM = 6371.0088;

    private GeoDistance() {
    }

    public static double haversineKm(
            double latitude1,
            double longitude1,
            double latitude2,
            double longitude2) {
        double latitudeDelta = Math.toRadians(latitude2 - latitude1);
        double longitudeDelta = Math.toRadians(longitude2 - longitude1);
        double firstLatitude = Math.toRadians(latitude1);
        double secondLatitude = Math.toRadians(latitude2);

        double a = Math.pow(Math.sin(latitudeDelta / 2), 2)
                + Math.cos(firstLatitude)
                * Math.cos(secondLatitude)
                * Math.pow(Math.sin(longitudeDelta / 2), 2);

        return EARTH_RADIUS_KM * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}
