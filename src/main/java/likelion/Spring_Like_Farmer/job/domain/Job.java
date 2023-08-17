package likelion.Spring_Like_Farmer.job.domain;

import jakarta.persistence.*;

import likelion.Spring_Like_Farmer.config.BaseEntity;
import likelion.Spring_Like_Farmer.job.dto.JobDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Job")
public class Job extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private boolean foreigner;

    @Column(nullable = false)
    private boolean koreanLanguage;

    @Column(nullable = true)
    private String document;

    @Column(nullable = false)
    private String contact;

    // builder
    @Builder
    public Job(JobDto.RegisterJob registerJob) {
        this.name = registerJob.getName();
        this.age = registerJob.getAge();
        this.foreigner = registerJob.isForeigner();
        this.koreanLanguage = registerJob.isKoreanLanguage();
        //this.document = document;
        this.contact = registerJob.getContact();
    }
}
