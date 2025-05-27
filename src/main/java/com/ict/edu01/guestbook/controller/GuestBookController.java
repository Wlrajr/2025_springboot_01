package com.ict.edu01.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.edu01.guestbook.service.GuestBookService;
import com.ict.edu01.common.DataVO;
import com.ict.edu01.guestbook.vo.GuestBookVO;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/guestbook")
public class GuestBookController {

    @Autowired
    private GuestBookService guestBookService;

    @GetMapping("list")
    public DataVO getList() {
        DataVO dataVO = new DataVO();

        try {
            List<GuestBookVO> guestBookList = guestBookService.getList();

            if (guestBookList == null || guestBookList.isEmpty()) {
                dataVO.setSuccess(false);
                dataVO.setMessage("불러오기 실패");
                dataVO.setData(null);
            } else {
                dataVO.setSuccess(true);
                dataVO.setMessage("불러오기 성공");
                dataVO.setData(guestBookList);
            }

        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류 : " + e.getMessage());
        }
        return dataVO;
    }

    @GetMapping("/guestbookdetail")
    public DataVO getDetail(@RequestParam("gb_idx") int gb_idx){
        DataVO dataVO = new DataVO();

        try {
            GuestBookVO guestBookVO = guestBookService.getDetail(gb_idx);

            if (guestBookVO == null) {
                dataVO.setSuccess(false);
                dataVO.setMessage("불러오기 실패");
                dataVO.setData(null);
            } else {
                dataVO.setSuccess(true);
                dataVO.setMessage("불러오기 성공");
                dataVO.setData(guestBookVO);
            }
        } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage("서버 오류 : " + e.getMessage());
        }  
        return dataVO;
    } 
}