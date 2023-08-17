package likelion.Spring_Like_Farmer.job.controller;

import likelion.Spring_Like_Farmer.job.domain.Job;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import likelion.Spring_Like_Farmer.job.service.JobService;
import likelion.Spring_Like_Farmer.job.dto.JobDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {
    private final JobService jobService;


    @PostMapping()
    public ResponseEntity<Object> register(@ModelAttribute JobDto.RegisterJob regJob,
                                      @RequestPart(value= "file", required = false) MultipartFile file) {
        return new ResponseEntity<>(jobService.registerJob(regJob, file), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllJobs() {
        List<Job> jobs = jobService.findAllJobs();
        return new ResponseEntity<>(new JobDto.JobListResponse(ExceptionCode.JOB_GET_OK, jobs), HttpStatus.OK);
    }
}

