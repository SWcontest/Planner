package WeekRoute.planer.service.Plan;

import WeekRoute.planer.domain.Plan;
import WeekRoute.planer.mapper.PlanMapper;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PlanService {

    @Autowired
    private PlanMapper planMapper;


    public int getCurrentId() {
        return planMapper.getCurrentId();
    }

    /**
     *  오늘의 일정 조회
     */
    public List<Plan> getPlanList(String login_id, String plan_date) {

        String firstPlace, secondPlace, thirdPlace, route;

        int count = 0;

        // 시간이 정해진 걸 가져오고,
        List<Plan> Plan = planMapper.getPlanList(login_id, plan_date,"", "");
        List<Plan> TimeExistPlan = planMapper.getPlanList(login_id, plan_date, "1", "");

        if(Plan.size() == 0) {
            return null;
        } else if (Plan.size() == 1 || Plan.size() == 2) {
            return Plan;
        } else {
            if (TimeExistPlan.size() == 0) {
                firstPlace = Plan.get((int) Math.random() * (Plan.size() - 0)).getCoordinate();
                secondPlace = findMin(firstPlace, Plan, "");
                thirdPlace = findMin2(firstPlace, secondPlace, Plan);
                route = getRoute(firstPlace, secondPlace, thirdPlace);
                List<Plan> resultList = new ArrayList<Plan>() {{
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[0]).get(0));
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[1]).get(0));
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[2]).get(0));
                }};
                return resultList;
            } else if (TimeExistPlan.size() == 1) {
                firstPlace = TimeExistPlan.get(0).getCoordinate();
                secondPlace = findMin(firstPlace, Plan, "");
                thirdPlace = findMin2(firstPlace, secondPlace, Plan);
                route = getRoute(firstPlace, secondPlace, thirdPlace);
                List<Plan> resultList = new ArrayList<Plan>() {{
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[0]).get(0));
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[1]).get(0));
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[2]).get(0));
                }};
                return resultList;
            } else if (TimeExistPlan.size() == 2) {
                firstPlace = TimeExistPlan.get(0).getCoordinate();
                secondPlace = TimeExistPlan.get(1).getCoordinate();
                thirdPlace = findMin2(firstPlace, secondPlace, Plan);
                route = getRoute(firstPlace, secondPlace, thirdPlace);
                List<Plan> resultList = new ArrayList<Plan>() {{
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[0]).get(0));
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[1]).get(0));
                    add(planMapper.getPlanList(login_id, plan_date, "", route.split("-")[2]).get(0));
                }};
                return resultList;
            } else {
                return TimeExistPlan;
            }
        }
    }
    /**
     *  시간이 1개가 정해졌을 때
     */
    public String findMin(String first, List<Plan> Plan, String except) {
        Haversine haver = new Haversine();
        double dis;
        int count = 0;
        Map<String, Double> map = new HashMap<>();
        double p1a = Double.parseDouble(first.split(",")[0]); // 위도
        double p1b = Double.parseDouble(first.split(",")[1]); // 경도
        for(int i=1; i<Plan.size(); i++){
            String place2 = Plan.get(i).getCoordinate();
            if(except == place2)
            {
                continue;
            } else {
                double p2a = Double.parseDouble(place2.split(",")[0]); // 위도
                double p2b = Double.parseDouble(place2.split(",")[1]); // 경도
                dis = haver.haversine(p1a, p1b, p2a, p2b);
                map.put(place2, dis);
            }
        }

        List<String> listKeySet = new ArrayList<>(map.keySet());
        Collections.sort(listKeySet, (value1, value2) -> (map.get(value1).compareTo(map.get(value2))));
        String disTable[] = new String[listKeySet.size()];
        for(String key : listKeySet) {
            disTable[count] = key+","+Double.toString(map.get(key));
            count++;
        }
        return disTable[0];
    }

    /**
     *  시간이 2개가 정해졌을 때
     */
    public String findMin2(String place1, String place2, List<Plan> Plan) {
        String place3 = findMin(place1, Plan, place2);
        String place3_2 = findMin(place2, Plan, place1);
        if(Double.parseDouble(place3.split(",")[1]) > Double.parseDouble(place3_2.split(",")[1])){
            return place3.split(",")[0];
        } else {
            return place3_2.split(",")[0];
        }
    }

    /**
     * 목적지 3곳의 최단거리 계산
     */
    public String getRoute(String place1, String place2, String place3) {
        Haversine haver = new Haversine();
        double dis1, dis2, dis3, result;
        double p1a = Double.parseDouble(place1.split(",")[0]); // 위도
        double p1b = Double.parseDouble(place1.split(",")[1]); // 경도
        double p2a = Double.parseDouble(place2.split(",")[0]); // 위도
        double p2b = Double.parseDouble(place2.split(",")[1]); // 경도
        double p3a = Double.parseDouble(place3.split(",")[0]); // 위도
        double p3b = Double.parseDouble(place3.split(",")[1]); // 경도
        dis1 = haver.haversine(p1a, p1b, p2a, p2b);
        dis2 = haver.haversine(p1a, p1b, p3a, p3b);
        dis3 = haver.haversine(p2a, p2b, p3a, p3b);

        result = Math.min(dis1+dis2, dis1+dis3);
        result = Math.min(result, dis2+dis3);
        if(dis1+dis2 == result) {
            return place2 + "-" + place1 + "-" + place3;
        } else if(dis1+dis3 == result) {
            return place1 + "-" + place2 + "-" + place3;
        } else {
            return place1 + "-" + place3 + "-" + place2;
        }
    }

    /**
     *  일정추가
     */
    public void addPlan(Plan plan) {
        Float[] coords = findGeoPoint(plan.getPlace());
        plan.setCoordinate(coords[0]+","+coords[1]);
        planMapper.addPlan(plan);
    }



    public static Float[] findGeoPoint(String location) {
        if (location == null)
            return null;
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();

        Geocoder geocoder = new Geocoder();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);

        if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {
            GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();
            LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

            Float[] coords = new Float[2];
            coords[0] = latitudeLongitude.getLat().floatValue();
            coords[1] = latitudeLongitude.getLng().floatValue();
            return coords;
        }
        return null;
    }
}
