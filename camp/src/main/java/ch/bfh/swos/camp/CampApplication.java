package ch.bfh.swos.camp;

import ch.bfh.swos.camp.model.Party;
import ch.bfh.swos.camp.service.HeroService;
import ch.bfh.swos.camp.service.PartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CampApplication implements ApplicationRunner {

	@Autowired
	HeroService heroService;

	@Autowired
	PartyService partyService;

	private static final Logger LOGGER=LoggerFactory.getLogger(CampApplication.class);

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

		nonOptionArgs.forEach(nonOption -> LOGGER.info("## Non Option Args : "+nonOption));
		optionNames.forEach(option -> LOGGER.info("## Option Names    : "+option));
		Arrays.stream(sourceArgs).forEach(srcArgs ->LOGGER.info("## Source Args     : "+srcArgs));
		LOGGER.info("## Option Value of --optionalArg1 : "+args.getOptionValues("optionalArg1"));
		LOGGER.info("## Option Value of --optionalArg2 : "+args.getOptionValues("optionalArg2"));



		Party p = partyService.createParty("Hero Party");
		int herosWithAtkGreaterThan = heroService.countHeroByAtkGreaterThan(50);
		System.err.println(p);
		System.err.println("Number of Heroes with ATK > 50: " + herosWithAtkGreaterThan);

	}

}
