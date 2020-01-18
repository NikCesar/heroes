package ch.bfh.swos.camp;

import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.model.HeroType;
import ch.bfh.swos.camp.service.HeroService;
import ch.bfh.swos.camp.service.PartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
// Enables this microservice to register at Eureka-server (== registry-module)
// Config-data for registering is in Application.properties
@EnableEurekaClient
public class CampApplication implements ApplicationRunner {

    @Autowired
    HeroService heroService;

    @Autowired
    PartyService partyService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CampApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CampApplication.class, args);
    }

    // Is executed on startup
    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("EXECUTING : Run method of Application Runner");
        final List<String> nonOptionArgs = args.getNonOptionArgs();
        final String[] sourceArgs = args.getSourceArgs();
        final Set<String> optionNames = args.getOptionNames();

        nonOptionArgs.forEach(nonOption -> LOGGER.info("## Non Option Args : " + nonOption));
        optionNames.forEach(option -> LOGGER.info("## Option Names    : " + option));
        Arrays.stream(sourceArgs).forEach(srcArgs -> LOGGER.info("## Source Args     : " + srcArgs));
        LOGGER.info("## Option Value of --optionalArg1 : " + args.getOptionValues("optionalArg1"));
        LOGGER.info("## Option Value of --optionalArg2 : " + args.getOptionValues("optionalArg2"));

        // Clear DB and initialize it with fresh, random heroes:
        initializeHeroes();
    }

    public void initializeHeroes(){
        try {
            // Clear Database:
            this.heroService.deleteAllHeroes();
            // Populate database with 20 randomly created heroes:
            System.err.println("\nCreated Heroes:");

            // Create 20 heroes for each hero-type:
            List<Hero> heroes = new ArrayList<>();
            for (HeroType hType : HeroType.values()) {
                heroes.addAll(this.heroService.createRandomHeroes(20, hType));
            }

            // Create a random party and print it out:
            System.err.println(partyService.createParty("\n\nMonster Party"));
            // Calculate how many of the heroes in the database have an ATK > 50:
            System.err.println("Number of Heroes with ATK > 50: " + heroService.countHeroByAtkGreaterThan(50));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
