package ch.bfh.swos.equipment.service.impl;

import ch.bfh.swos.equipment.client.CampClient;
import ch.bfh.swos.equipment.exception.ArmorNotFoundException;
import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.model.Armor;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.model.Rarity;
import ch.bfh.swos.equipment.repository.ArmorRepository;
import ch.bfh.swos.equipment.service.ArmorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultArmorService implements ArmorService {

    private ArmorRepository armorRepository;
    private CampClient campClient;

    public DefaultArmorService(ArmorRepository armorRepository, CampClient campClient) {
        this.armorRepository = armorRepository;
        this.campClient = campClient;
    }

    @Override
    public Armor saveArmor(Armor armor) {
        return armorRepository.save(armor);
    }

    @Override
    public Armor createArmor(String name, Rarity rarity, int def, double dodgeChance) {
        Armor armor = new Armor();
        armor.setName(name);
        armor.setRarity(rarity);
        armor.setDef(def);
        armor.setDodgeChance(dodgeChance);
        return armorRepository.save(armor);
    }

    @Override
    public Optional<Armor> findArmorById(Long id) {
        return armorRepository.findById(id);
    }

    @Override
    public List<Armor> findAll() {
        return armorRepository.findAll();
    }

    @Override
    public void deleteArmor(Long id) {
        armorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        armorRepository.deleteAll();
    }

    @Override
    public Hero equip(String heroId, Long armorId) throws HeroNotFoundException, ArmorNotFoundException, InvalidHeroException {
        Hero hero = campClient.findHeroById(heroId).getContent();
        Armor newArmor = armorRepository.findById(armorId).get();

        if (newArmor == null) {
            throw new ArmorNotFoundException("Armor does not exist");
        }

        if (hero.getArmorId() != null) {
            Armor equippedArmor = armorRepository.findById(hero.getArmorId()).get();
            hero.setDef(hero.getDef() - equippedArmor.getDef());
            hero.setDodgeChance(hero.getDodgeChance() - equippedArmor.getDodgeChance());
        }

        hero.setArmorId(armorId);
        hero.setDef(hero.getDef() + newArmor.getDef());
        hero.setDodgeChance(hero.getDodgeChance() + newArmor.getDodgeChance());
        campClient.updateHero(hero);
        return hero;
    }

    @Override
    public Hero unequip(String heroId) throws HeroNotFoundException, InvalidHeroException {
        Hero hero = campClient.findHeroById(heroId).getContent();

        if (hero.getArmorId() != null) {
            Armor armor = armorRepository.findById(hero.getArmorId()).get();

            if (armor != null) {
                hero.setDef(hero.getDef() - armor.getDef());
                hero.setDodgeChance((double)Math.round((hero.getDodgeChance() - armor.getDodgeChance()) * 1000d) / 1000d);
                hero.setArmorId(null);

                campClient.updateHero(hero);
            }
        }
        return hero;
    }
}
