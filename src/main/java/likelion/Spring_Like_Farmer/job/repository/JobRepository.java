package likelion.Spring_Like_Farmer.job.repository;

import likelion.Spring_Like_Farmer.job.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    // Job save(Job job);
    List<Job> findAllByOrderByCreatedAtDesc();
}

