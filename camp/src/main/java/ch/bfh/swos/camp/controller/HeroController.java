package ch.bfh.swos.camp.controller;

import ch.bfh.swos.camp.exceptions.HeroNotFoundException;
import ch.bfh.swos.camp.exceptions.InvalidHeroException;
import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// localhost:8080/rest/heroes/

@RestController
@RequestMapping("/heroes")
public class HeroController {

    @Autowired
    HeroService heroService;

    @GetMapping
    public @ResponseBody List<Hero> findAllHeroes() {
        return this.heroService.findAllHeroes();
    }

    @GetMapping("/{id}")
    public @ResponseBody Hero findHeroById(@PathVariable String id) throws HeroNotFoundException {
        return this.heroService.findHeroById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String createHero(@RequestBody Hero hero){
        return this.heroService.createHero(hero);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateHero(@RequestBody Hero hero) throws InvalidHeroException, HeroNotFoundException {
        this.heroService.updateHero(hero);
    }

    @DeleteMapping("/{heroId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHero(@PathVariable String heroId) throws HeroNotFoundException {
        this.heroService.deleteHero(heroId);
    }

}
