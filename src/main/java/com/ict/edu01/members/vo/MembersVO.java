package com.ict.edu01.members.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembersVO {
    private int m_idx;
    private String m_id;
    private String m_pw;
    private String m_name;
    private String m_addr;
    private String m_addr2;
    private String m_email;
    private String m_phone;
    private String m_reg;
    private String m_active;
    private String m_active_reg;
    private String sns_email_naver;
    private String sns_email_kakao; 
    private String sns_provider;
}