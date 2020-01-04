package ch.bfh.swos.equipment.controller;

import ch.bfh.swos.equipment.model.Mount;
import ch.bfh.swos.equipment.service.MountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mounts")
public class MountController {

    private MountService mountService;

    public MountController(MountService mountService) {
        this.mountService = mountService;
    }

    @GetMapping
    public List<Mount> findAll() {
        return mountService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Mount> findMount(@PathVariable Long id) {
        return mountService.findMountById(id);
    }
}
