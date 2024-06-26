package site.lawmate.manage.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.lawmate.manage.domain.dto.UserStatsDto;
import site.lawmate.manage.service.ManageService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ManageController {

    private final ManageService manageService;


    @Scheduled(cron = "0 0 0 * * ?")
    @GetMapping("/save")
    public void saveUserStats() {
        log.info("saveUserStats");
        manageService.saveUserStats();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserStatsDto>> findAll() {
        log.info("findAll");
        return ResponseEntity.ok(manageService.findAll());
    }

    @GetMapping("/findByMonth")
    public ResponseEntity<List<UserStatsDto>> findByMonth() {
        log.info("findByMonth");
        return ResponseEntity.ok(manageService.findByMonth());
    }

}
