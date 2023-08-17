package likelion.Spring_Like_Farmer.job.service;

import likelion.Spring_Like_Farmer.file.FileService;
import likelion.Spring_Like_Farmer.job.domain.Job;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import likelion.Spring_Like_Farmer.job.repository.JobRepository;
import likelion.Spring_Like_Farmer.job.dto.JobDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;

    private final FileService fileService;

    public Object registerJob(JobDto.RegisterJob regJob, MultipartFile file) {

        Job job = Job.builder()
                .registerJob(regJob)
                .build();

        if(regJob.isForeigner()) { // 외국인이면(foreigner 값: 1이면)
            if (file != null && !file.isEmpty()) {
                String image = fileService.saveFile(job.getJobId(), file, "document");
                job.setDocument(image);

                jobRepository.save(job);
                return new JobDto.JobResponse(ExceptionCode.JOB_SAVE_OK);
            } else {
                job.setDocument(null);
                return new JobDto.JobResponse(ExceptionCode.FILE_NOT_FOUND);
            }
        } else { // 외국인이 아니면(foreigner 값: 2이면)
            job.setDocument(null);

            jobRepository.save(job);
            return new JobDto.JobResponse(ExceptionCode.JOB_SAVE_OK);
        }
    }

    public List<Job> findAllJobs() {
        return jobRepository.findAllByOrderByCreatedAtDesc();
    }
}

