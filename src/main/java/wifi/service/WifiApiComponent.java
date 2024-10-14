package wifi.service;

import com.google.gson.Gson;
import wifi.dto.WifiInfoDto;
import wifi.model.WifiInfoModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WifiApiComponent {

    /**
     * OpenAPI를 통해서 와이파이 정보의 JSON 문자열을 리턴함
     */
    public String getWifiInfoList(int pageIndex) {
        StringBuilder result = new StringBuilder();
        try {
            String apiUrl = "http://openapi.seoul.go.kr:8088/6b63575770696c683935525a714b6a/json/TbPublicWifiInfo/" + pageIndex + "/10";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * JSON 문자열을 WifiInfoDto 배열로 변환
     */
    public WifiInfoDto[] parseToDto(String jsonResponse) {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, WifiInfoDto[].class); // JSON을 DTO 배열로 변환
    }

    /**
     * JSON 문자열을 WifiInfoModel 배열로 변환
     */
    public WifiInfoModel[] parseToModel(String jsonResponse) {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, WifiInfoModel[].class); // JSON을 Model 배열로 변환
    }
}
