package ch.bfh.swos.equipment.controller;

import ch.bfh.swos.equipment.exception.ArmorNotFoundException;
import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.model.Armor;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.service.ArmorService;
import ch.bfh.swos.equipment.service.impl.DefaultArmorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/armors")
public class ArmorController {


    private ArmorService armorService;

    public ArmorController(ArmorService armorService) {
        this.armorService = armorService;
    }

    @GetMapping
    public List<Armor> findAll() {
        return armorService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Armor> findArmor(@PathVariable Long id) throws ArmorNotFoundException {
        return armorService.findArmorById(id);
    }

    @PutMapping("/{heroId}/equip/{armorId}")
    public Hero equipArmor(@PathVariable String heroId, @PathVariable Long armorId) throws ArmorNotFoundException, HeroNotFoundException, InvalidHeroException {
        return armorService.equip(heroId, armorId);
    }

    @PutMapping("/{heroId}/unequip")
    public Hero unequipArmor(@PathVariable("heroId") String heroId) throws ArmorNotFoundException, HeroNotFoundException, InvalidHeroException {
        return armorService.unequip(heroId);
    }
}
