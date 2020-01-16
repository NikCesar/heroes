package ch.bfh.swos.equipment.controller;

import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.exception.MountNotFoundException;
import ch.bfh.swos.equipment.exception.WeaponNotFoundException;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.model.Mount;
import ch.bfh.swos.equipment.service.MountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mounts")
public class MountController {

    private MountService mountService;

    public MountController(MountService mountService) {
        this.mountService = mountService;
    }

    @GetMapping
    public List<Mount> findAll() {
        return mountService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Mount> findMount(@PathVariable Long id) throws MountNotFoundException {
        return mountService.findMountById(id);
    }

    @PutMapping("/{heroId}/equip/{mountId}")
    public Hero equipWeapon(@PathVariable String heroId, @PathVariable Long mountId) throws MountNotFoundException, HeroNotFoundException, InvalidHeroException {
        return mountService.equip(heroId, mountId);
    }

    @PutMapping("/{heroId}/unequip")
    public Hero unequipArmor(@PathVariable String heroId) throws MountNotFoundException, HeroNotFoundException, InvalidHeroException {
        return mountService.unequip(heroId);
    }
}
