package ch.bfh.swos.camp.service;

import ch.bfh.swos.camp.model.Hero;
import ch.bfh.swos.camp.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    @Autowired
    HeroRepository repository;

    public Hero createHero(Hero hero) {
        return repository.save(hero);
    }

    public List<Hero> getAll() {
        return repository.findAll();
    }

    public Optional<Hero> getHero(Long id) {
        return repository.findById(id);
    }
}
