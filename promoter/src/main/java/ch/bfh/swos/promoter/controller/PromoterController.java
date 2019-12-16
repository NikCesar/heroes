package ch.bfh.swos.promoter.controller;

import ch.bfh.swos.promoter.service.PromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// localhost:4444/promoter/

@RestController
@RequestMapping("/promoter")
public class PromoterController {

    @Autowired
    private PromoterService promoterService;

    // To test if promoter-service is working
    @GetMapping
    public String promotorServiceStatus(){
        return "Promoter-Service is working";
    }

    @GetMapping(value = "/promoteFight")
    public String promoteFight() {
        String result = promoterService.promoteFight();
        return "The Promoter is proud to proclaim the following result of today's battle: "+result;
    }
}
