package WeekRoute.planer.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class BusRouteService {

    public void getBusRoute(double sLat, double sLng, double eLat, double eLng) {
        String appkey = "yKtViKSnZPoKlM5MW4ew%2BgOGFRdDSQjZ3QXzplm8pbE";
        String apiUrl = "https://api.odsay.com/v1/api/searchPubTransPath?SX=" + sLng + "&SY=" + sLat + "&EX=" + eLng + "&EY=" + eLat + "&OPT=1&apiKey=" + appkey;
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
            System.out.println(response.toString());

        } catch (Exception e){
            System.out.println(e);
        }
    }
}





