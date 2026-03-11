package com.gravitino.project.ldlockhart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GravitinoController {

    private final GravitinoLogisticsService service;

    public GravitinoController(GravitinoLogisticsService service) {
        this.service = service;
    }

    @GetMapping("/api/setup-data-lake")
    public String setupDataLake() {
        return service.initializeLogisticsMetadata();
    }
}
