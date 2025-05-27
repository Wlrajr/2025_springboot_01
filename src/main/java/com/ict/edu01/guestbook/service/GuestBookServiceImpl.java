package com.ict.edu01.guestbook.service;

import com.ict.edu01.guestbook.mapper.GuestBookMapper;
import com.ict.edu01.guestbook.vo.GuestBookVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestBookServiceImpl implements GuestBookService{

    @Autowired
    private GuestBookMapper guestBookMapper;

    @Override
    public List<GuestBookVO> getList() {
        return guestBookMapper.guestbooklist();
    }

    @Override
    public GuestBookVO getDetail(int gb_idx) {
        return guestBookMapper.guestbookDetail(gb_idx);
    }
}