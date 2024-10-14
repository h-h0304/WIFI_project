package step.step1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenApi {

    /**
     * 오픈 API 샘플을 호출하여 JSON 형식의 문자열 반환
     *
     * @param startPage 시작 페이지
     * @param endPage 종료 페이지
     * @return API 호출 결과 문자열
     */
    public static String get(int startPage, int endPage) {

        String request = null;

        try {
            // 서울 공공 와이파이 정보 API URL 구성
            String url = String.format("http://openapi.seoul.go.kr:8088/6b63575770696c683935525a714b6a/json/TbPublicWifiInfo/%s/%s/", startPage, endPage);

            // URL 객체 생성 및 연결 설정
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode()); // 연결 확인용 응답 코드 출력

            BufferedReader rd;

            // 응답 코드가 200~300 사이일 경우 정상 처리, 그 외에는 오류 처리
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            // 응답 데이터를 문자열로 읽어들임
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            request = sb.toString();

        } catch (Exception e) {
            System.out.println("openApi error: " + e.getMessage()); // 예외 발생 시 오류 메시지 출력
        }

        return request; // API 호출 결과 반환
    }
}