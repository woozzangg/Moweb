package com.a507.moweb.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketMessage {
    private int room_no; //방번호
    private String user_name; //보내는 사람
    private String chat_msg; //메시지 내용
    private boolean status; //준비상태
    private boolean all_ready; //전부 준비 되어있는가
    private String[] user_names; //유저 이름 배열(레이어 순서로 정렬)
    private int bg_no;  //가상 배경 번호
    private int shot_cnt;   //촬영횟수
    private int action; //액션 번호(0:알림, 1:채팅, 2:레디, 3:레디취소, 4:촬영대기로 입장, 5:레이어순서, 6:가상배경 선택, 7:촬영횟수)
}
