package WeekRoute.planer.controller;

import WeekRoute.planer.service.BusRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BusRouteController {

    private final BusRouteService busRouteService;

    @GetMapping("/api/v1/bus/{sLat}/{sLng}/{eLat}/{eLng}")
    public int getBusRoute(
            @PathVariable double sLat,
            @PathVariable double sLng,
            @PathVariable double eLat,
            @PathVariable double eLng) {
        busRouteService.getBusRoute(sLat, sLng, eLat, eLng);
        return 0;
    }
}
