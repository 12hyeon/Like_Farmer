package likelion.Spring_Like_Farmer.record.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.record.dto.RecordDto;
import likelion.Spring_Like_Farmer.user.domain.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Record")
public class Record extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long recordId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_user_id")
    private User user;

    String who;
    String what;
    String size;
    String work;
    LocalDate date;

    // builder
    @Builder
    public Record(RecordDto.SaveRecord saveRecord, User user) {
        this.user = user;
        this.who = saveRecord.getWho();
        this.what = saveRecord.getWhat();
        this.size = saveRecord.getSize();
        this.work = saveRecord.getWork();
        this.date = saveRecord.getDate();
    }

    public void updateRecord(RecordDto.SaveRecord saveRecord) {
        this.work = saveRecord.getWork();
        this.date = saveRecord.getDate();
    }

}
