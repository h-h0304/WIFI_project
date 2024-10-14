package wifi.model;

public class WifiInfoDetailModel {

    private String wifiMgrNo;      // 와이파이 관리 번호 (PK)

    // 기본 생성자
    public WifiInfoDetailModel() {}

    // 모든 필드를 포함하는 생성자
    public WifiInfoDetailModel(String wifiMgrNo) {
        this.wifiMgrNo = wifiMgrNo;
    }

    // getter와 setter 메서드
    public String getWifiMgrNo() {
        return wifiMgrNo;
    }

    public void setWifiMgrNo(String wifiMgrNo) {
        this.wifiMgrNo = wifiMgrNo;
    }
}
