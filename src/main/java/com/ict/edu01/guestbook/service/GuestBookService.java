package com.ict.edu01.guestbook.service;

import com.ict.edu01.guestbook.vo.GuestBookVO;
import java.util.List;

public interface GuestBookService {
    List<GuestBookVO> getList();

    GuestBookVO getDetail(int gb_idx);
}