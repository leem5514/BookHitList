package com.example.project.service;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.MemberDTO;
import com.example.project.dto.MypageDTO;
import com.example.project.dto.MypageMapDTO;
import com.example.project.entity.BoardEntity;
import com.example.project.entity.MemberEntity;
import com.example.project.entity.MypageEntity;
import com.example.project.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final MypageRepository mypageRepository;



    // 1. 회원가입시 마이페이지 엔티티 생성(멤버 아이디만 받기)
    // 2. 마이페이지에서 위치값 설정하기 (위도, 경도 저장)
    // 3. 마이페이지에서 qr코드 src값 설정하기 (qr코드 src값 저장)

    //회원가입시 외래키 저장
    public void save(String memberId) {

        MypageEntity mypageEntity = MypageEntity.toMypageEntity(memberId);
        mypageRepository.save(mypageEntity);
    }

    @Transactional
    public void updateMap(String memberId, MypageMapDTO mypageMapDTO) {
        MypageEntity mypageEntity1 = mypageRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("not found :"+ memberId));
        mypageEntity1.update(mypageMapDTO.getLatitude(), mypageMapDTO.getLongitude());
        mypageRepository.save(mypageEntity1);
    }

    @Transactional
    public MypageEntity findById(String memberId) {
        MypageEntity mypageEntity = mypageRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("not found :"+ memberId));
        return mypageEntity;
    }
}