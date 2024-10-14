package wifi.service;

import wifi.dto.WifiInfoDto;
import wifi.model.WifiInfoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WifiService extends SqliteConnection {

    /**
     * DTO를 Model로 변환하는 메서드
     */
    private WifiInfoModel convertDtoToModel(WifiInfoDto dto) {
        WifiInfoModel model = new WifiInfoModel();
        model.setAddress1(dto.getAddress1());
        model.setAddress2(dto.getAddress2());
        model.setServiceType(dto.getServiceType());
        model.setUserLat(dto.getLatitude());
        model.setUserLnt(dto.getLongitude());
        model.setRadius(dto.getRadius());
        return model;
    }

    /**
     * Model을 DTO로 변환하는 메서드
     */
    private WifiInfoDto convertModelToDto(WifiInfoModel model) {
        WifiInfoDto dto = new WifiInfoDto();
        dto.setAddress1(model.getAddress1());
        dto.setAddress2(model.getAddress2());
        dto.setServiceType(model.getServiceType());
        dto.setLatitude(model.getUserLat());
        dto.setLongitude(model.getUserLnt());
        dto.setRadius(model.getRadius());
        return dto;
    }

    /**
     * 와이파이 정보를 저장 (DTO 사용)
     */
    public boolean add(WifiInfoDto dto) {
        return add(convertDtoToModel(dto)); // DTO를 Model로 변환 후 저장
    }

    /**
     * 와이파이 정보를 저장 (Model 사용)
     */
    public boolean add(WifiInfoModel model) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnect();
            String sql = "INSERT INTO Wifi_Info (wifi_mgr_no, wifi_name, latitude, longitude) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, model.getAddress1());
            pstmt.setString(2, model.getServiceType());
            pstmt.setDouble(3, model.getUserLat());
            pstmt.setDouble(4, model.getUserLnt());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            close(null, pstmt, conn);
        }
    }

    /**
     * 와이파이 정보를 조회하여 DTO나 Model로 반환할 수 있도록 수정
     */
    public List<WifiInfoDto> getWifiInfoAsDto() {
        List<WifiInfoDto> wifiInfoDtoList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();
            String sql = "SELECT wifi_mgr_no, wifi_name, latitude, longitude, address1, address2, service_type, radius FROM Wifi_Info";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                WifiInfoDto dto = new WifiInfoDto();
                dto.setWifiMgrNo(rs.getString("wifi_mgr_no"));
                dto.setWifiName(rs.getString("wifi_name"));
                dto.setLatitude(rs.getDouble("latitude"));
                dto.setLongitude(rs.getDouble("longitude"));
                dto.setAddress1(rs.getString("address1"));
                dto.setAddress2(rs.getString("address2"));
                dto.setServiceType(rs.getString("service_type"));
                dto.setRadius(rs.getDouble("radius"));
                wifiInfoDtoList.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, conn);
        }

        return wifiInfoDtoList;
    }

    /**
     * 와이파이 정보를 조회하여 Model로 반환하는 로직
     */
    public List<WifiInfoModel> getWifiInfoAsModel() {
        List<WifiInfoModel> wifiInfoModelList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnect();
            String sql = "SELECT wifi_mgr_no, wifi_name, latitude, longitude, address1, address2, service_type, radius FROM Wifi_Info";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                WifiInfoModel model = new WifiInfoModel();
                model.setAddress1(rs.getString("address1"));
                model.setAddress2(rs.getString("address2"));
                model.setServiceType(rs.getString("service_type"));
                model.setUserLat(rs.getDouble("latitude"));
                model.setUserLnt(rs.getDouble("longitude"));
                model.setRadius(rs.getDouble("radius"));
                wifiInfoModelList.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, conn);
        }

        return wifiInfoModelList;
    }
}
