package ch.bfh.swos.camp.controller;

import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "hero")
public class HeroController {

    @Autowired
    HeroService heroService;

    @GetMapping
    public List<Hero> getAll() {
        return heroService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Optional<Hero> getHero(@PathVariable Long id) {
        return heroService.getHero(id);
    }
}
