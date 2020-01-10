package ch.bfh.swos.equipment;

import ch.bfh.swos.equipment.model.Rarity;
import ch.bfh.swos.equipment.repository.ArmorRepository;
import ch.bfh.swos.equipment.repository.MountRepository;
import ch.bfh.swos.equipment.repository.WeaponRepository;
import ch.bfh.swos.equipment.service.ArmorService;
import ch.bfh.swos.equipment.service.MountService;
import ch.bfh.swos.equipment.service.WeaponService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class EquipmentApplicationRunner implements ApplicationRunner {

    private ArmorRepository armorRepository;
    private MountRepository mountRepository;
    private WeaponRepository weaponRepository;

    private ArmorService armorService;
    private MountService mountService;
    private WeaponService weaponService;

    public EquipmentApplicationRunner(ArmorRepository armorRepository, MountRepository mountRepository, WeaponRepository weaponRepository, ArmorService armorService, MountService mountService, WeaponService weaponService) {
        this.armorRepository = armorRepository;
        this.mountRepository = mountRepository;
        this.weaponRepository = weaponRepository;
        this.armorService = armorService;
        this.mountService = mountService;
        this.weaponService = weaponService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (armorRepository.count() == 0) {
            armorService.createArmor("Cloth Armor", Rarity.COMMON, 10, 0.2);
            armorService.createArmor("Leather Armor", Rarity.RARE, 20, 0.15);
            armorService.createArmor("Iron Armor", Rarity.EPIC, 30, 0.1);
            armorService.createArmor("Titanium Armor", Rarity.LEGENDARY, 40, 0.25);
        }
        if (mountRepository.count() == 0) {
            mountService.createMount("Wayfaring Horse", Rarity.COMMON, 20, 2);
            mountService.createMount("Dallying Donkey", Rarity.RARE, 30, 4);
            mountService.createMount("Raging Pig", Rarity.EPIC, 40, 6);
            mountService.createMount("Meditating Alpaca", Rarity.LEGENDARY, 50, 10);
        }
        if (weaponRepository.count() == 0) {
            weaponService.createWeapon("Iron Dagger", Rarity.COMMON, 2, 0.05);
            weaponService.createWeapon("Fire Falchion", Rarity.RARE, 4, 0.1);
            weaponService.createWeapon("Enchanted Bastardsword", Rarity.EPIC, 6, 0.15);
            weaponService.createWeapon("Spellwoven Longsword", Rarity.LEGENDARY, 8, 0.2);
            weaponService.createWeapon("Sharpened stick", Rarity.COMMON, -4, 0.25);
        }
    }
}
