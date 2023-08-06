package likelion.Spring_Like_Farmer.user.domain;

import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Quest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questId;

    private QuestInfo questInfo;

    public enum QuestInfo {
        ONE(1,"프로필 사진 올리기"),
        TWO(2,"글 올리기"),
        THREE(3,"퀘스트를 완료하였습니다!");

        private int code;
        private String message;

        QuestInfo(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
