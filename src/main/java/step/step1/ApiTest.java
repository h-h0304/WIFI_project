package step.step1;

public class ApiTest {

    /**
     * TODO: 1. Open API 테스트
     */
    public static void main(String[] args) {

        // OpenApi 클래스를 사용하여 API를 호출하고 결과를 받아 출력
        String result = OpenApi.get(1, 10);
        System.out.println(result); // 결과 출력
    }

}