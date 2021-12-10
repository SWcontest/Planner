package WeekRoute.planer.controller;

import lombok.Getter;

@Getter
public class BusRouteResponseDto {

    private int totalWalk;
    private int totalTime;
    private int payment;
    private int totalDistance;
    private String firstStartStation;
    private String lastEndStation;

    public BusRouteResponseDto(int totalWalk, int totalTime, int payment, int totalDistance, String firstStartStation, String lastEndStation) {
        this.totalWalk = totalWalk;
        this.totalTime = totalTime;
        this.payment = payment;
        this.totalDistance = totalDistance;
        this.firstStartStation = firstStartStation;
        this.lastEndStation = lastEndStation;
    }
}
