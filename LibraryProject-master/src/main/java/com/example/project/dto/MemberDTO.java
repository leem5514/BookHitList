package com.example.project.dto;

import com.example.project.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    //데이터 교환
    private Long id;
    private String memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        return memberDTO;
    }
}

