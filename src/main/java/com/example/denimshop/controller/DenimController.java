package com.example.denimshop.controller;

import com.example.denimshop.entity.Denim;
import com.example.denimshop.service.DenimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/denims")
@RequiredArgsConstructor
public class DenimController {

    private final DenimService denimService;

    @GetMapping("/list")
    public List<Denim> getDenims() {
        return denimService.findAll();
    }
}
