package com.example.project.entity;

import com.example.project.dto.MemberDTO;
import com.example.project.dto.MypageDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "mypage_table")
public class MypageEntity {
    //디비 테이블
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String memberId;

    @Column
    private String qrCode;
    //위도
    @Column
    private String latitude;
    //경도
    @Column
    private String longitude;

    public static MypageEntity toMypageEntity(String memberId) {
        MypageEntity mypageEntity = new MypageEntity();
        mypageEntity.setMemberId(memberId);
        return mypageEntity;
    }

    public static MypageEntity toMypageEntity2(MypageDTO mypageDTO) {
        MypageEntity mypageEntity = new MypageEntity();
        mypageEntity.setId(mypageEntity.getId());
        mypageEntity.setMemberId(mypageEntity.getMemberId());
        mypageEntity.setQrCode(mypageEntity.getQrCode());
        mypageEntity.setLongitude(mypageEntity.getLongitude());
        mypageEntity.setLatitude(mypageEntity.getLatitude());
        return mypageEntity;
    }

    public void update(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }





//    public static MypageEntity toMapMypageEntity(MypageDTO mypageDTO) {
//        MemberEntity memberEntity = new MemberEntity();
//        memberEntity.setId(memberDTO.getId());
//        memberEntity.setMemberId(memberDTO.getMemberId());
//        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
//        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
//        memberEntity.setMemberName(memberDTO.getMemberName());
//        return memberEntity;
//    }
}