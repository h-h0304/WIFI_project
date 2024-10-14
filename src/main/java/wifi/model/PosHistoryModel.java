package wifi.model;

import java.time.LocalDateTime;

public class PosHistoryModel {

    private double userLat;       // 사용자가 입력한 위도
    private double userLnt;       // 사용자가 입력한 경도
    private LocalDateTime searchTime; // 조회 시점

    // 기본 생성자
    public PosHistoryModel() {}

    // 모든 필드를 포함하는 생성자
    public PosHistoryModel(double userLat, double userLnt, LocalDateTime searchTime) {
        this.userLat = userLat;
        this.userLnt = userLnt;
        this.searchTime = searchTime;
    }

    // getter와 setter 메서드
    public double getUserLat() {
        return userLat;
    }

    public void setUserLat(double userLat) {
        this.userLat = userLat;
    }

    public double getUserLnt() {
        return userLnt;
    }

    public void setUserLnt(double userLnt) {
        this.userLnt = userLnt;
    }

    public LocalDateTime getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(LocalDateTime searchTime) {
        this.searchTime = searchTime;
    }
}
