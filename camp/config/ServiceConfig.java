package config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import repositories.HeroRepository;
import service.impl.DefaultHeroService;
import service.impl.DefaultPartyService;

import java.io.IOException;

@Configuration
@EnableMongoRepositories(basePackages = "repositories")
public class ServiceConfig {

    @Autowired
    private HeroRepository heroRepository;

    @Bean
        public DefaultHeroService heroService(){
        return new DefaultHeroService(heroRepository);
    }

    @Bean
    public DefaultPartyService partyService() {
        return new DefaultPartyService(heroService());
    }

    @Bean
    @DependsOn("mongo")
    public MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "heroDatabase");
    }

    @Bean(destroyMethod = "close")
    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .version("3.4.17")
                .bindIp("localhost")
                .port(27017)
                .build();
    }
}
