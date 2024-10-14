package wifi.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import wifi.dto.PosHistoryDto;
import wifi.model.PosHistoryModel;

public class PosHistoryService extends SqliteConnection {

    /**
     * DTO를 Model로 변환하는 메서드
     */
    private PosHistoryModel convertDtoToModel(PosHistoryDto dto) {
        PosHistoryModel model = new PosHistoryModel();
        model.setUserLat(dto.getUserLat());
        model.setUserLnt(dto.getUserLnt());
        model.setSearchTime(dto.getSearchTime());
        return model;
    }

    /**
     * Model을 DTO로 변환하는 메서드
     */
    private PosHistoryDto convertModelToDto(PosHistoryModel model) {
        PosHistoryDto dto = new PosHistoryDto();
        dto.setUserLat(model.getUserLat());
        dto.setUserLnt(model.getUserLnt());
        dto.setSearchTime(model.getSearchTime());
        return dto;
    }

    /**
     * 히스토리 정보를 저장할 때 DTO나 Model을 모두 처리할 수 있도록 수정
     */
    public boolean addHistory(PosHistoryDto historyDto) {
        PosHistoryModel historyModel = convertDtoToModel(historyDto);
        return saveHistoryToDatabase(historyModel);
    }

    public boolean addHistory(PosHistoryModel historyModel) {
        return saveHistoryToDatabase(historyModel);
    }

    /**
     * 히스토리 정보를 데이터베이스에 저장하는 공통 메서드
     */
    private boolean saveHistoryToDatabase(PosHistoryModel history) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnect();
            String sql = "INSERT INTO History (user_lat, user_lnt, search_time) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, history.getUserLat());
            pstmt.setDouble(2, history.getUserLnt());
            pstmt.setTimestamp(3, Timestamp.valueOf(history.getSearchTime()));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(null, pstmt, conn);
        }
    }

    /**
     * 히스토리를 조회하여 DTO 리스트로 반환
     */
    public List<PosHistoryDto> getHistoryAsDto() {
        List<PosHistoryDto> historyList = new ArrayList<>();
        List<PosHistoryModel> modelList = getHistoryAsModel();
        for (PosHistoryModel model : modelList) {
            historyList.add(convertModelToDto(model));
        }
        return historyList;
    }

    /**
     * 히스토리를 조회하여 Model 리스트로 반환
     */
    public List<PosHistoryModel> getHistoryAsModel() {
        List<PosHistoryModel> historyList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnect();
            String sql = "SELECT user_lat, user_lnt, search_time FROM History";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PosHistoryModel history = new PosHistoryModel();
                history.setUserLat(rs.getDouble("user_lat"));
                history.setUserLnt(rs.getDouble("user_lnt"));
                history.setSearchTime(rs.getTimestamp("search_time").toLocalDateTime());
                historyList.add(history);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs, pstmt, conn);
        }
        return historyList;
    }
}
