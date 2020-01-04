package ch.bfh.swos.equipment.controller;

import ch.bfh.swos.equipment.model.Armor;
import ch.bfh.swos.equipment.service.ArmorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Optional<Armor> findArmor(@PathVariable Long id) {
        return armorService.findArmorById(id);
    }
}
