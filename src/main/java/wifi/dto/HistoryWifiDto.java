package wifi.dto;

public class HistoryWifiDto {

    private int historyId;       // 히스토리 ID (FK)
    private String wifiMgrNo;    // 와이파이 관리 번호 (FK)

    // 기본 생성자
    public HistoryWifiDto() {}

    // 모든 필드를 포함하는 생성자
    public HistoryWifiDto(int historyId, String wifiMgrNo) {
        this.historyId = historyId;
        this.wifiMgrNo = wifiMgrNo;
    }

    // getter와 setter 메서드
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public String getWifiMgrNo() {
        return wifiMgrNo;
    }

    public void setWifiMgrNo(String wifiMgrNo) {
        this.wifiMgrNo = wifiMgrNo;
    }
}
