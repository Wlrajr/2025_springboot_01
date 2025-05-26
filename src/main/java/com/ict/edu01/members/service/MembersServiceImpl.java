package com.ict.edu01.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.edu01.members.mapper.MembersMapper;
import com.ict.edu01.members.vo.MembersVO;

@Service
public class MembersServiceImpl implements MembersService{

    @Autowired
    private MembersMapper membersMapper;

    @Override
    public MembersVO getLogin(MembersVO mvo) {
        try {
            // 이 System.out.println은 원래 로그인 로직에서 사용되던 것이므로 유지하거나 제거해도 무방합니다.
            System.out.println("로그인 시도: " + mvo.getM_id());
            System.out.println("비밀번호: " + mvo.getM_pw());
            return membersMapper.getLogin(mvo);
        } catch (Exception e) {
            System.err.println("로그인 서비스 중 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 로그인 오류도 확인하기 위해 추가
            return null;
        }
    }

    @Override
    public MembersVO addMember(MembersVO mvo) {
        try {
            System.out.println("회원가입 서비스 진입 - 요청 ID: " + mvo.getM_id()); // 추가 로깅

            // DB에 회원 정보 삽입 시도
            int rowsAffected = membersMapper.addMember(mvo);
            System.out.println("데이터베이스에 삽입된 행 수 (rowsAffected): " + rowsAffected); // 추가 로깅

            if (rowsAffected > 0) { // 삽입 성공 시
                // m_idx가 MembersVO 객체에 제대로 채워졌는지 확인 (keyProperty="m_idx" 덕분)
                System.out.println("삽입 후 MembersVO의 m_idx 값: " + mvo.getM_idx()); // 추가 로깅

                if (mvo.getM_idx() > 0) { // m_idx는 int 타입이므로 0보다 큰지 확인
                    // 자동 생성된 m_idx를 사용하여 DB에서 회원 전체 정보를 다시 조회
                    MembersVO registeredMember = membersMapper.getMemberByIdx(mvo.getM_idx());
                    System.out.println("m_idx로 조회된 회원 정보: " + registeredMember); // 추가 로깅
                    return registeredMember;
                } else {
                    // m_idx가 0이거나 유효하지 않은 경우 (keyProperty 설정 문제 또는 DB 문제)
                    System.err.println("오류: 회원 등록은 되었으나, m_idx 값이 올바르게 채워지지 않았습니다."); // 추가 로깅
                    return null;
                }
            } else {
                // rowsAffected가 0인 경우 (삽입 실패)
                System.err.println("오류: 데이터베이스에 새로운 회원이 삽입되지 않았습니다. (rowsAffected = 0)"); // 추가 로깅
                return null;
            }
        } catch (Exception e) {
            // !!! 이 부분이 가장 중요합니다. 실제 발생한 예외를 콘솔에 출력합니다.
            System.err.println("회원 등록 중 치명적인 오류 발생: " + e.getMessage());
            e.printStackTrace(); // 스택 트레이스 전체를 출력하여 정확한 원인 파악
            return null; // 예외 발생 시 null 반환
        }
    }
}