package com.ict.edu01.members.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu01.members.service.MembersService;
import com.ict.edu01.common.DataVO;
import com.ict.edu01.members.vo.MembersVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/members")
public class MembersController {

    @Autowired
    private MembersService membersService;

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, SpringBoot";
    }

    @PostMapping("/login")
    public DataVO getLogin(@RequestBody MembersVO mvo) {
        System.out.println(mvo.getM_id());
        System.out.println(mvo.getM_pw());
        DataVO dataVO = new DataVO();

        try {

            // db 에 가서 m_id와 m_pw가 맞는지 확인한다.

            MembersVO membersVO = membersService.getLogin(mvo);

            // DataVO dataVO = new DataVO();
            /*
             * //맞으면
             * dataVO.setSuccess(true);
             * dataVO.setMessage("로그인 성공");
             * // 만약 정보 전달할 data가 하나이면
             * dataVO.setData(정보);
             * // 만약 정보 전달할 data가 여러개이면
             * Map<String, Object> result = new HashMap<>();
             * result.put("list", list);
             * result.put("membersVO", mvo);
             * result.put("totalCount", totalCount);
             * //맞지 않으면
             * dataVO.setSuccess(false);
             * dataVO.setMessage("로그인 실패");
             */
            if (membersVO == null) {
                dataVO.setSuccess(false);
                dataVO.setMessage("로그인 실패");
            } else {
                dataVO.setSuccess(true);
                dataVO.setMessage("로그인 성공");
            }

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류 : " + e.getMessage());
        }

        return dataVO;
    }

    @PostMapping("/register")
    public DataVO signUp(@RequestBody MembersVO mvo) {
        DataVO dataVO = new DataVO();

        try {
            MembersVO registeredMember = membersService.addMember(mvo);

            if (registeredMember != null) {
                dataVO.setSuccess(true);
                dataVO.setMessage("회원가입 성공");
                dataVO.setData(registeredMember);
            } else {
                dataVO.setSuccess(false);
                dataVO.setMessage("회원가입 실패");
            }
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류: " + e.getMessage());
        }
        return dataVO;
    }

    

}
