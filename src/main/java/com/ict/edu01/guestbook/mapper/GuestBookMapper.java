package com.ict.edu01.guestbook.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ict.edu01.guestbook.vo.GuestBookVO;
import java.util.List;

@Mapper
public interface GuestBookMapper {
    List<GuestBookVO> guestbooklist();

    GuestBookVO guestbookDetail(int gb_idx);
}