package likelion.Spring_Like_Farmer.record.repository;

import likelion.Spring_Like_Farmer.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    Record save(Record record);
    Optional<Record> findByRecordId(Long recordId);
    List<Record> findAllByUserUserId(Long userUserId);
    boolean existsByRecordId(Long recordId);
}