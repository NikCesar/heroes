package ch.bfh.swos.equipment.service.impl;

import ch.bfh.swos.equipment.client.CampClient;
import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.exception.WeaponNotFoundException;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.model.Rarity;
import ch.bfh.swos.equipment.model.Weapon;
import ch.bfh.swos.equipment.repository.WeaponRepository;
import ch.bfh.swos.equipment.service.WeaponService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultWeaponService implements WeaponService {

    private WeaponRepository weaponRepository;
    private CampClient campClient;

    public DefaultWeaponService(WeaponRepository weaponRepository, CampClient campClient) {
        this.weaponRepository = weaponRepository;
        this.campClient = campClient;
    }

    @Override
    public Weapon saveWeapon(Weapon weapon) {
        return weaponRepository.save(weapon);
    }

    @Override
    public Weapon createWeapon(String name, Rarity rarity, int atk, double critChance) {
        Weapon weapon = new Weapon();
        weapon.setName(name);
        weapon.setRarity(rarity);
        weapon.setAtk(atk);
        weapon.setCritChance(critChance);
        return weaponRepository.save(weapon);
    }

    @Override
    public Optional<Weapon> findWeaponById(Long id) {
        return weaponRepository.findById(id);
    }

    @Override
    public List<Weapon> findAll() {
        return weaponRepository.findAll();
    }

    @Override
    public void deleteWeapon(Long id) {
        weaponRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        weaponRepository.deleteAll();
    }

    @Override
    public Hero equip(String heroId, Long weaponId) throws HeroNotFoundException, WeaponNotFoundException, InvalidHeroException {
        Hero hero = campClient.findHeroById(heroId).getContent();
        Weapon equippedWeapon = weaponRepository.findById(hero.getWeaponId()).get();
        Weapon newWeapon = weaponRepository.findById(weaponId).get();

        if (equippedWeapon == null || newWeapon == null) {
            throw new WeaponNotFoundException("Armor does not exist");
        }

        if (hero.getArmorId() != null) {
            hero.setAtk(hero.getAtk() - equippedWeapon.getAtk());
            hero.setCritChance(hero.getCritChance() - equippedWeapon.getCritChance());
        }

        hero.setAtk(hero.getAtk() + newWeapon.getAtk());
        hero.setCritChance(hero.getCritChance() + newWeapon.getCritChance());
        hero.setWeaponId(weaponId);
        campClient.updateHero(hero);
        return hero;
    }

    @Override
    public Hero unequip(String heroId, Long weaponId) throws HeroNotFoundException, InvalidHeroException {
        Hero hero = campClient.findHeroById(heroId).getContent();
        Weapon weapon = weaponRepository.findById(weaponId).get();

        if (weapon == null) {
            if (hero.getArmorId() != null) {
                hero.setAtk(hero.getAtk() - weapon.getAtk());
                hero.setCritChance(hero.getCritChance() - weapon.getCritChance());
            }
            hero.setWeaponId(null);
        }
        campClient.updateHero(hero);
        return hero;
    }
}
