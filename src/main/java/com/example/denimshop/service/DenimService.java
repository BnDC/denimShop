package com.example.denimshop.service;

import com.example.denimshop.entity.Denim;
import com.example.denimshop.repository.DenimRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DenimService {

    private final DenimRepository denimRepository;

    public void registerDenim(DenimRegisterRequest denimRegisterRequest) {
        return ;
    }

    public List<Denim> findAll() {
        return denimRepository.findAll();
    }
}
