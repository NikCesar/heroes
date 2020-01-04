package ch.bfh.swos.equipment.service;

import ch.bfh.swos.equipment.exception.ArmorNotFoundException;
import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.model.Armor;

import java.util.List;
import java.util.Optional;

public interface ArmorService {

    Armor saveArmor(Armor armor);
    Optional<Armor> findArmorById(Long id) throws ArmorNotFoundException;
    List<Armor> findAll();
    void deleteArmor(Long id) throws ArmorNotFoundException;
    void deleteAll();
    void equip(String heroId, Long armorId) throws HeroNotFoundException, ArmorNotFoundException;
    void deequip(String heroId, Long armorId) throws HeroNotFoundException, ArmorNotFoundException;
}
