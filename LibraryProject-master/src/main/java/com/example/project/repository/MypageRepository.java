package com.example.project.repository;


import com.example.project.dto.MypageDTO;
import com.example.project.entity.MypageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MypageRepository extends JpaRepository<MypageEntity, Long> {
    Optional<MypageEntity> findByMemberId (String memberId);
}