package ch.bfh.swos.equipment.service;

import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.WeaponNotFoundException;
import ch.bfh.swos.equipment.model.Weapon;

import java.util.List;
import java.util.Optional;

public interface WeaponService {

    Weapon saveWeapon(Weapon weapon);
    Optional<Weapon> findWeaponById(Long id) throws WeaponNotFoundException;
    List<Weapon> findAll();
    void deleteWeapon(Long id) throws WeaponNotFoundException;
    void deleteAll();
    void equip(String heroId, Long armorId) throws HeroNotFoundException, WeaponNotFoundException;
    void deequip(String heroId, Long armorId) throws HeroNotFoundException, WeaponNotFoundException;
}
