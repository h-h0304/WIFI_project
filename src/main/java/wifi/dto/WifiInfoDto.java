package wifi.dto;

public class WifiInfoDto {

    private String wifiMgrNo;           // 와이파이 관리 번호
    private String wifiName;            // 와이파이 이름
    private String address1;            // 첫 번째 주소
    private String address2;            // 상세 주소
    private String floor;               // 설치된 층
    private String installType;         // 설치 유형
    private String serviceType;         // 서비스 유형
    private String communicationNetwork; // 통신사
    private int constructionYear;       // 설치 연도
    private String indoorOutdoor;       // 실내/실외 구분
    private double latitude;            // 위도
    private double longitude;           // 경도
    private String workDttm;            // 작업 일시
    private double radius;              // 탐색 반경 (추가된 필드)
    private int locationId;             // 위치 ID (FK)
    private int agencyId;               // 관리기관 ID (FK)

    // 기본 생성자
    public WifiInfoDto() {}

    // 모든 필드를 포함하는 생성자
    public WifiInfoDto(String wifiMgrNo, String wifiName, String address1, String address2, String floor, String installType,
                       String serviceType, String communicationNetwork, int constructionYear, String indoorOutdoor,
                       double latitude, double longitude, String workDttm, double radius, int locationId, int agencyId) {
        this.wifiMgrNo = wifiMgrNo;
        this.wifiName = wifiName;
        this.address1 = address1;
        this.address2 = address2;
        this.floor = floor;
        this.installType = installType;
        this.serviceType = serviceType;
        this.communicationNetwork = communicationNetwork;
        this.constructionYear = constructionYear;
        this.indoorOutdoor = indoorOutdoor;
        this.latitude = latitude;
        this.longitude = longitude;
        this.workDttm = workDttm;
        this.radius = radius;
        this.locationId = locationId;
        this.agencyId = agencyId;
    }

    // getter와 setter 메서드
    public String getWifiMgrNo() {
        return wifiMgrNo;
    }

    public void setWifiMgrNo(String wifiMgrNo) {
        this.wifiMgrNo = wifiMgrNo;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getInstallType() {
        return installType;
    }

    public void setInstallType(String installType) {
        this.installType = installType;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCommunicationNetwork() {
        return communicationNetwork;
    }

    public void setCommunicationNetwork(String communicationNetwork) {
        this.communicationNetwork = communicationNetwork;
    }

    public int getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getIndoorOutdoor() {
        return indoorOutdoor;
    }

    public void setIndoorOutdoor(String indoorOutdoor) {
        this.indoorOutdoor = indoorOutdoor;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getWorkDttm() {
        return workDttm;
    }

    public void setWorkDttm(String workDttm) {
        this.workDttm = workDttm;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }
}
