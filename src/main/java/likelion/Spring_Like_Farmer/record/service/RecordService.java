package likelion.Spring_Like_Farmer.record.service;

import likelion.Spring_Like_Farmer.record.domain.Record;
import likelion.Spring_Like_Farmer.record.dto.RecordDto;
import likelion.Spring_Like_Farmer.record.repository.RecordRepository;
import likelion.Spring_Like_Farmer.security.UserPrincipal;
import likelion.Spring_Like_Farmer.user.domain.User;
import likelion.Spring_Like_Farmer.user.repository.UserRepository;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public Object saveRecord(UserPrincipal userPrincipal, RecordDto.SaveRecord saveRecord) {

        User user = userRepository.findByUserId(userPrincipal.getUserId()).get();

        Record record = Record.builder()
                .saveRecord(saveRecord)
                .user(user)
                .build();
        recordRepository.save(record);

        return new RecordDto.RecordResponse(ExceptionCode.RECORD_SAVE_OK);
    }

    public Object findRecords(UserPrincipal recordPrincipal, Long recordId) {

        List<Record> records = recordRepository.findAllByUserUserId(recordPrincipal.getUserId());

        return new RecordDto.RecordResponse(ExceptionCode.RECORD_GET_OK, records);
    }

    public Object updateRecord(UserPrincipal userPrincipal, Long recordId, RecordDto.SaveRecord saveRecord) {

        Optional<Record> findRecord = recordRepository.findByRecordId(recordId);
        if (findRecord.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.RECORD_NOT_FOUND);
        }
        Record record = findRecord.get();

        record.updateRecord(saveRecord);
        recordRepository.save(record);

        return new RecordDto.RecordResponse(ExceptionCode.RECORD_UPDATE_OK);
    }

    public Object deleteRecord(UserPrincipal recordPrincipal, Long recordId) {

        Optional<Record> findRecord = recordRepository.findByRecordId(recordId);
        if (findRecord.isEmpty()) {
            return new RecordDto.RecordResponse(ExceptionCode.RECORD_NOT_FOUND);
        }
        Record record = findRecord.get();

        recordRepository.delete(record);

        return new RecordDto.RecordResponse(ExceptionCode.RECORD_DELETE_OK);
    }
}