package ch.bfh.swos.equipment.controller;

import ch.bfh.swos.equipment.exception.WeaponNotFoundException;
import ch.bfh.swos.equipment.model.Weapon;
import ch.bfh.swos.equipment.service.WeaponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
