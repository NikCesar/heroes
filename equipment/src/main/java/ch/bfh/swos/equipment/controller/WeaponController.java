package ch.bfh.swos.equipment.controller;

import ch.bfh.swos.equipment.exception.ArmorNotFoundException;
import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.exception.WeaponNotFoundException;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.model.Weapon;
import ch.bfh.swos.equipment.service.WeaponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weapons")
public class WeaponController {

    private WeaponService weaponService;

    public WeaponController(WeaponService weaponService) {
        this.weaponService = weaponService;
    }

    @GetMapping
    public List<Weapon> findAll() {
        return weaponService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Weapon> findWeapon(@PathVariable Long id) throws WeaponNotFoundException {
        return weaponService.findWeaponById(id);
    }

    @PutMapping("/{heroId}/equip/{weaponId}")
    public Hero equipWeapon(@PathVariable String heroId, @PathVariable Long weaponId) throws WeaponNotFoundException, HeroNotFoundException, InvalidHeroException {
        return weaponService.equip(heroId, weaponId);
    }

    @PutMapping("/{heroId}/unequip/{weaponId}")
    public Hero unequipWeapon(@PathVariable String heroId, @PathVariable Long weaponId) throws WeaponNotFoundException, HeroNotFoundException, InvalidHeroException {
        return weaponService.unequip(heroId, weaponId);
    }
}
