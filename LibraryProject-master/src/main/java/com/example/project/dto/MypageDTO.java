package com.example.project.dto;

import com.example.project.entity.MemberEntity;
import com.example.project.entity.MypageEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MypageDTO {

    private Long id;
    private String memberId;
    private String qrCode;
    private String latitude;
    private String longitude;


    public static MypageDTO toMypageDTO(MypageEntity mypageEntity) {
        MypageDTO mypageDTO = new MypageDTO();
        mypageDTO.setId(mypageEntity.getId());
        mypageDTO.setMemberId(mypageEntity.getMemberId());
        mypageDTO.setQrCode(mypageEntity.getQrCode());
        mypageDTO.setLatitude(mypageEntity.getLatitude());
        mypageDTO.setLongitude(mypageEntity.getLongitude());
        return mypageDTO;
    }

//    public static MypageDTO toMypageMapDTO(MypageEntity mypageEntity) {
//        MypageDTO mypageDTO = new MypageDTO();
//        mypageDTO.setId(mypageEntity.getId());
//        mypageDTO.setMemberId(mypageEntity.getMemberId());
//        mypageDTO.setLatitude(mypageEntity.getLatitude());
//        mypageDTO.setLongitude(mypageEntity.getLongitude());
//        return mypageDTO;
//    }

}