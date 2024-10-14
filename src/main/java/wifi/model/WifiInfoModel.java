package wifi.model;

public class WifiInfoModel {

    private String address1;             // 첫 번째 주소
    private String address2;             // 두 번째 주소
    private String serviceType;          // 서비스 유형
    private double userLat;              // 사용자의 위도
    private double userLnt;              // 사용자의 경도
    private double radius;               // 탐색 반경 (하버사인 공식을 이용한 근처 와이파이 찾기용)

    // 기본 생성자
    public WifiInfoModel() {}

    // 모든 필드를 포함하는 생성자
    public WifiInfoModel(String address1, String address2, String serviceType, double userLat, double userLnt, double radius) {
        this.address1 = address1;
        this.address2 = address2;
        this.serviceType = serviceType;
        this.userLat = userLat;
        this.userLnt = userLnt;
        this.radius = radius;
    }

    // getter와 setter 메서드
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
