package wifi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wifi.dto.PosHistoryDto;
import wifi.service.PosHistoryService;

import java.util.List;

@RestController
@RequestMapping("/history")
public class PosHistoryController {

    private final PosHistoryService posHistoryService;

    // 생성자 주입을 통한 서비스 의존성 주입
    public PosHistoryController(PosHistoryService posHistoryService) {
        this.posHistoryService = posHistoryService;
    }

    /**
     * 새로운 위치 히스토리 정보를 저장하는 API
     * @param posHistoryDto 저장할 위치 히스토리 정보
     * @return 성공 여부
     */
    @PostMapping("/save")
    public ResponseEntity<String> saveHistory(@RequestBody PosHistoryDto posHistoryDto) {
        boolean isSuccess = posHistoryService.addHistory(posHistoryDto);
        if (isSuccess) {
            return ResponseEntity.ok("History saved successfully.");
        } else {
            return ResponseEntity.status(500).body("Failed to save history.");
        }
    }

    /**
     * 저장된 모든 위치 히스토리 정보를 반환하는 API
     * @return 위치 히스토리 DTO 리스트
     */
    @GetMapping("/all")
    public ResponseEntity<List<PosHistoryDto>> getAllHistories() {
        List<PosHistoryDto> historyList = posHistoryService.getHistoryAsDto();
        return ResponseEntity.ok(historyList);
    }
}
