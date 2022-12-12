package com.example.denimshop.repository;

import com.example.denimshop.entity.Denim;
import com.example.denimshop.type.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DenimRepository extends JpaRepository<Denim, Long> {

    @Override
    Optional<Denim> findById(Long denimId);

    Optional<Denim> findAllByCategory(MaterialCategory category);

//    List<Denim> findByIdIn(List<Long> denimIdList);

    @Override
    List<Denim> findAll();
}

