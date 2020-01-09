package ch.bfh.swos.equipment.service;

import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.exception.WeaponNotFoundException;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.model.Rarity;
import ch.bfh.swos.equipment.model.Weapon;

import java.util.List;
import java.util.Optional;

public interface WeaponService {

    Weapon saveWeapon(Weapon weapon);
    Weapon createWeapon(String name, Rarity rarity, int atk, double critChance);
    Optional<Weapon> findWeaponById(Long id) throws WeaponNotFoundException;
    List<Weapon> findAll();
    void deleteWeapon(Long id) throws WeaponNotFoundException;
    void deleteAll();
    Hero equip(String heroId, Long armorId) throws HeroNotFoundException, WeaponNotFoundException, InvalidHeroException;
    Hero unequip(String heroId, Long armorId) throws HeroNotFoundException, WeaponNotFoundException, InvalidHeroException;
}
