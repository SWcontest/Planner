package WeekRoute.planer.controller;

import WeekRoute.planer.service.BusRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BusRouteController {

    private final BusRouteService busRouteService;

    // 위도 경도로 버스 정보 요청
    @GetMapping("/api/v1/bus/{sLat}/{sLng}/{eLat}/{eLng}")
    @ResponseBody
    public BusRouteResponseDto getBusRoute(
            @PathVariable double sLat,
            @PathVariable double sLng,
            @PathVariable double eLat,
            @PathVariable double eLng) {
        return busRouteService.getBusRoute(sLat, sLng, eLat, eLng);
    }
}
