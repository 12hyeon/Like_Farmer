package likelion.Spring_Like_Farmer.record.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.record.domain.Record;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class RecordDto {

    @Getter
    public static class SaveRecord {
        String who;
        String what;
        String size;
        String work;
        LocalDate date;
    }

    @Getter
    public static class UpdateRecord {
        int recordId;
        String work;
        LocalDate date;
    }

    @Getter
    public static class RecordResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        private List<Record> records;

        public RecordResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }

        public RecordResponse(ExceptionCode exceptionCode, List<Record> records) {
            super(exceptionCode);
            this.records = records;
        }
    }

    @Getter
    public static class RecordListResponse extends ResponseType{

        List<Record> recordList;
        int total;

        public RecordListResponse(ExceptionCode exceptionCode, List<Record> recordList) {
            super(exceptionCode);
            this.total = recordList.size();
            this.recordList = recordList;
        }
    }
}
