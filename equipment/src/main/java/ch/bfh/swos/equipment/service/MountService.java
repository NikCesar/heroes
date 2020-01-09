package ch.bfh.swos.equipment.service;

import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.exception.MountNotFoundException;
import ch.bfh.swos.equipment.model.Mount;
import ch.bfh.swos.equipment.model.Rarity;

import java.util.List;
import java.util.Optional;

public interface MountService {

    Mount saveMount(Mount mount);
    Mount createMount(String name, Rarity rarity, double hp, int initiative);
    Optional<Mount> findMountById(Long id) throws MountNotFoundException;
    List<Mount> findAll();
    void deleteMount(Long id) throws MountNotFoundException;
    void deleteAll();
    void equip(String heroId, Long armorId) throws HeroNotFoundException, MountNotFoundException, InvalidHeroException;
    void deequip(String heroId, Long armorId) throws HeroNotFoundException, MountNotFoundException, InvalidHeroException;
}
