package WeekRoute.planer.service;

import WeekRoute.planer.controller.BusRouteResponseDto;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class BusRouteService {

    public BusRouteResponseDto getBusRoute(double sLat, double sLng, double eLat, double eLng) {
        String appkey = "yKtViKSnZPoKlM5MW4ew%2BgOGFRdDSQjZ3QXzplm8pbE";
        String apiUrl = "https://api.odsay.com/v1/api/searchPubTransPath?SX=" + sLng + "&SY=" + sLat + "&EX=" + eLng + "&EY=" + eLat + "&OPT=1&apiKey=" + appkey;
        BusRouteResponseDto dto = null;
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode==200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            JsonParser parser = new JsonParser();
            Object obj = parser.parse(response.toString());
            JsonObject jsonObject = (JsonObject) obj;
            JsonObject result = jsonObject.getAsJsonObject("result");
            JsonArray path = result.getAsJsonArray("path");
            JsonObject path1 = path.get(0).getAsJsonObject();
            JsonObject info = path1.getAsJsonObject("info");

             dto = new BusRouteResponseDto(
                            info.get("totalWalk").getAsInt(),
                            info.get("totalTime").getAsInt(),
                            info.get("payment").getAsInt(),
                            info.get("totalDistance").getAsInt(),
                            info.get("firstStartStation").getAsString(),
                            info.get("lastEndStation").getAsString()
            );

        } catch (Exception e){
            System.out.println(e);
        }
        return dto;
    }
}





