package WeekRoute.planer.service.Plan;

import WeekRoute.planer.domain.Plan;
import WeekRoute.planer.domain.user.Coordinate;
import WeekRoute.planer.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlanService {

    @Autowired
    private PlanMapper planMapper;


    public int getCurrentId() {
        return planMapper.getCurrentId();
    }

    /**
     *  일정목록 전체
     */
    public List<Plan> getPlanListAll(String login_id, String plan_date) {
        return planMapper.getPlanList(login_id, plan_date, "All", "");
    }


    /**
     *  루트 검색, 알고리즘 사용
     */
    public List<Coordinate> getRoute(String login_id, String plan_date) {
        String tmp;
        List<Coordinate> answer;
        String[] coordinate;
        List<Plan> timeExistPlan = planMapper.getPlanList(login_id, plan_date, "exist", "");
        List<Plan> nonTimeExistPlan = planMapper.getPlanList(login_id, plan_date, "non", "");

        if (nonTimeExistPlan.size() == 0) {
            answer = new ArrayList<>(timeExistPlan.size());
            for(int i=0; i<timeExistPlan.size(); i++) {
                Coordinate coord = new Coordinate();
                coordinate = timeExistPlan.get(i).getCoordinate().split(",");
                coord.setLat(coordinate[0]);
                coord.setLng(coordinate[1]);
                answer.add(coord);
            }
            return answer;
        } else {
            answer = new ArrayList<>(timeExistPlan.size()+nonTimeExistPlan.size());
            for(int i=0; i < timeExistPlan.size()+nonTimeExistPlan.size()-1; i++){
                if(i==0){
                    tmp = timeExistPlan.get(0).getCoordinate();
                    coordinate = timeExistPlan.get(0).getCoordinate().split(",");
                    Coordinate coord = new Coordinate();
                    coord.setLat(coordinate[0]);
                    coord.setLng(coordinate[1]);
                    answer.add(coord);
                    timeExistPlan.remove(0);
                }
                tmp = answer.get(i).getLat()+","+answer.get(i).getLng();
                Haversine haver = new Haversine();
                double distance = 999999;
                int removeIndex = 0;
                String returnCor = "";
                String removeSection="";

                double p1a = Double.parseDouble(tmp.split(",")[0]); // 위도
                double p1b = Double.parseDouble(tmp.split(",")[1]); // 경도
                for(int j=0; j<timeExistPlan.size(); j++) {
                    double p2a = Double.parseDouble(timeExistPlan.get(j).getCoordinate().split(",")[0]); // 위도
                    double p2b = Double.parseDouble(timeExistPlan.get(j).getCoordinate().split(",")[1]); // 경도
                    if(distance > haver.haversine(p1a, p1b, p2a, p2b)){
                        distance = haver.haversine(p1a, p1b, p2a, p2b);
                        removeIndex = j;
                        removeSection = "time";
                        returnCor = timeExistPlan.get(j).getCoordinate();
                    }
                }
                for(int k=0; k<nonTimeExistPlan.size(); k++) {
                    double p2a = Double.parseDouble(nonTimeExistPlan.get(k).getCoordinate().split(",")[0]); // 위도
                    double p2b = Double.parseDouble(nonTimeExistPlan.get(k).getCoordinate().split(",")[1]); // 경도
                    if(distance > haver.haversine(p1a, p1b, p2a, p2b)){
                        distance = haver.haversine(p1a, p1b, p2a, p2b);
                        removeIndex = k;
                        removeSection = "nonTime";
                        returnCor = nonTimeExistPlan.get(k).getCoordinate();
                    }
                }
                if(removeSection.equals("Time")){
                    timeExistPlan.remove(removeIndex);
                } else {
                    nonTimeExistPlan.remove(removeIndex);
                }
                coordinate = returnCor.split(",");
                Coordinate coord = new Coordinate();
                coord.setLat(coordinate[0]);
                coord.setLng(coordinate[1]);
                answer.add(coord);
            }
            return answer;
        }
    }


    public void addPlan(Plan plan) {
        int id = planMapper.getCurrentId();
        plan.setId(id);
        planMapper.addPlan(plan);
    }
}
