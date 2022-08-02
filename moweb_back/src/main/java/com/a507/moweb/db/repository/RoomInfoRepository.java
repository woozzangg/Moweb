package com.a507.moweb.db.repository;

import com.a507.moweb.db.entity.RoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomInfoRepository extends JpaRepository<RoomInfo, Long> {
    RoomInfo findByUrlAndIs_active(String url, boolean is_active);     // url을 기준으로 활성화된 룸 정보를 반환
}
