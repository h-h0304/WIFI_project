package step.step2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DataBaseConnection {

    /**
     * 데이터베이스 연결 및 테이블 생성, 공공데이터 삽입 테스트
     */
    public static void test() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // SQLite 데이터베이스 파일 연결 (경로 설정)
            String url = "jdbc:sqlite:db/wifi_db.sqlite";  // SQLite 데이터베이스 파일 경로 지정
            conn = DriverManager.getConnection(url);
            System.out.println("SQLite 데이터베이스에 성공적으로 연결되었습니다.");

            // 1. ddl.sql 파일에서 SQL 스크립트 읽어와 테이블 생성
            String sqlFilePath = "db/ddl.sql";  // ddl.sql 파일 경로
            String ddlSql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
            stmt = conn.createStatement();
            stmt.executeUpdate(ddlSql);  // 테이블 생성 SQL 실행
            System.out.println("테이블 생성 완료.");

            // 2. 공공 데이터 API 호출 및 데이터 삽입
            insertWifiData(conn);

            // 3. 현재 날짜와 시간을 조회하는 테스트 쿼리 실행
            String timeSql = "SELECT date(), time();";  // 현재 날짜와 시간을 조회하는 SQL 쿼리
            rs = stmt.executeQuery(timeSql);
            while (rs.next()) {
                String date = rs.getString(1);  // 첫 번째 컬럼: date()
                String time = rs.getString(2);  // 두 번째 컬럼: time()
                System.out.println("현재 날짜: " + date);
                System.out.println("현재 시간: " + time);
            }

        } catch (Exception e) {
            System.out.println("데이터베이스 연결 또는 쿼리 실행 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            // 리소스 정리
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("리소스 해제 중 오류가 발생했습니다: " + e.getMessage());
            }
        }
    }

    /**
     * 공공 와이파이 데이터를 API로부터 가져와서 SQLite에 삽입하는 함수
     */
    private static void insertWifiData(Connection conn) {
        String apiUrl = "http://openapi.seoul.go.kr:8088/6b63575770696c683935525a714b6a/json/TbPublicWifiInfo/1/5/";

        try {
            // API 호출
            HttpURLConnection connApi = (HttpURLConnection) new URL(apiUrl).openConnection();
            connApi.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connApi.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            // API 응답을 JSON 객체로 변환
            JSONObject jsonObject = new JSONObject(sb.toString());
            JSONArray rows = jsonObject.getJSONObject("TbPublicWifiInfo").getJSONArray("row");

            // 데이터 삽입 준비
            String insertSQL = "INSERT INTO Wifi_Info (wifi_mgr_no, wifi_name, address1, address2, floor, install_type, service_type, communication_network, construction_year, indoor_outdoor, latitude, longitude, work_dttm) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String selectSQL = "SELECT COUNT(*) FROM Wifi_Info WHERE wifi_mgr_no = ?";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            PreparedStatement pstmtSelect = conn.prepareStatement(selectSQL);

            // 각 와이파이 데이터 삽입
            for (int i = 0; i < rows.length(); i++) {
                JSONObject wifi = rows.getJSONObject(i);
                String wifiMgrNo = wifi.getString("X_SWIFI_MGR_NO");

                // 중복 데이터 확인
                pstmtSelect.setString(1, wifiMgrNo);
                ResultSet rs = pstmtSelect.executeQuery();
                rs.next();
                int count = rs.getInt(1);
                rs.close();

                if (count == 0) {
                    // 중복이 없을 경우에만 데이터 삽입
                    pstmt.setString(1, wifiMgrNo);
                    pstmt.setString(2, wifi.getString("X_SWIFI_MAIN_NM"));
                    pstmt.setString(3, wifi.getString("X_SWIFI_ADRES1"));
                    pstmt.setString(4, wifi.getString("X_SWIFI_ADRES2"));
                    pstmt.setString(5, wifi.optString("X_SWIFI_INSTL_FLOOR", ""));
                    pstmt.setString(6, wifi.getString("X_SWIFI_INSTL_TY"));
                    pstmt.setString(7, wifi.getString("X_SWIFI_SVC_SE"));
                    pstmt.setString(8, wifi.getString("X_SWIFI_CMCWR"));
                    pstmt.setInt(9, wifi.getInt("X_SWIFI_CNSTC_YEAR"));
                    pstmt.setString(10, wifi.getString("X_SWIFI_INOUT_DOOR"));
                    pstmt.setDouble(11, wifi.getDouble("LAT"));
                    pstmt.setDouble(12, wifi.getDouble("LNT"));
                    pstmt.setString(13, wifi.getString("WORK_DTTM"));

                    pstmt.executeUpdate();  // 데이터 삽입
                } else {
                    System.out.println("중복된 데이터로 인해 삽입 건너뜀: " + wifiMgrNo);
                }
            }

            System.out.println("공공 와이파이 데이터 삽입 완료.");

        } catch (Exception e) {
            System.out.println("공공데이터 API 호출 또는 데이터 삽입 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

}
