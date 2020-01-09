package ch.bfh.swos.equipment.service.impl;

import ch.bfh.swos.equipment.client.CampClient;
import ch.bfh.swos.equipment.exception.HeroNotFoundException;
import ch.bfh.swos.equipment.exception.InvalidHeroException;
import ch.bfh.swos.equipment.exception.MountNotFoundException;
import ch.bfh.swos.equipment.model.Hero;
import ch.bfh.swos.equipment.model.Mount;
import ch.bfh.swos.equipment.model.Rarity;
import ch.bfh.swos.equipment.repository.MountRepository;
import ch.bfh.swos.equipment.service.MountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultMountService implements MountService {

    private MountRepository mountRepository;
    private CampClient campClient;

    public DefaultMountService(MountRepository mountRepository, CampClient campClient) {
        this.mountRepository = mountRepository;
        this.campClient = campClient;
    }

    @Override
    public Mount saveMount(Mount mount) {
        return mountRepository.save(mount);
    }

    @Override
    public Mount createMount(String name, Rarity rarity, double hp, int initiative) {
        Mount mount = new Mount();
        mount.setName(name);
        mount.setRarity(rarity);
        mount.setHp(hp);
        mount.setInitiative(initiative);
        return mountRepository.save(mount);
    }

    @Override
    public Optional<Mount> findMountById(Long id) {
        return mountRepository.findById(id);
    }

    @Override
    public List<Mount> findAll() {
        return mountRepository.findAll();
    }

    @Override
    public void deleteMount(Long id) {
        mountRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        mountRepository.deleteAll();
    }

    @Override
    public void equip(String heroId, Long mountId) throws HeroNotFoundException, MountNotFoundException, InvalidHeroException {
        Hero hero = campClient.findHeroById(heroId).getContent();
        Mount equippedMount = mountRepository.findById(hero.getMountId()).get();
        Mount newMount = mountRepository.findById(mountId).get();

        if (equippedMount == null || newMount == null) {
            throw new MountNotFoundException("Armor does not exist");
        }

        if (hero.getArmorId() != null) {
            hero.setHp(hero.getHp() - equippedMount.getHp());
            hero.setInitiative(hero.getInitiative() - equippedMount.getInitiative());
        }

        hero.setHp(hero.getHp() + newMount.getHp());
        hero.setInitiative(hero.getInitiative() + newMount.getInitiative());
        hero.setMountId(mountId);
        campClient.updateHero(hero);
    }

    @Override
    public void deequip(String heroId, Long armorId) throws HeroNotFoundException, InvalidHeroException {
        Hero hero = campClient.findHeroById(heroId).getContent();
        Mount mount = mountRepository.findById(armorId).get();

        if (mount == null) {
            if (hero.getArmorId() != null) {
                hero.setHp(hero.getHp() - mount.getHp());
                hero.setInitiative(hero.getInitiative() - mount.getInitiative());
            }

            hero.setMountId(null);
        }
        campClient.updateHero(hero);
    }
}
