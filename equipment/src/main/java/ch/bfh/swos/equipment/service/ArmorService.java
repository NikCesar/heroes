package ch.bfh.swos.equipment.service;

import ch.bfh.swos.equipment.exception.ArmorNotFoundException;
import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.model.Armor;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.model.Rarity;

import java.util.List;
import java.util.Optional;

public interface ArmorService {

    Armor saveArmor(Armor armor);
    Armor createArmor(String name, Rarity rarity, int def, double dodgeChance);
    Optional<Armor> findArmorById(Long id) throws ArmorNotFoundException;
    List<Armor> findAll();
    void deleteArmor(Long id) throws ArmorNotFoundException;
    void deleteAll();
    Hero equip(String heroId, Long armorId) throws HeroNotFoundException, ArmorNotFoundException, InvalidHeroException;
    Hero unequip(String heroId, Long armorId) throws HeroNotFoundException, ArmorNotFoundException, InvalidHeroException;
}
