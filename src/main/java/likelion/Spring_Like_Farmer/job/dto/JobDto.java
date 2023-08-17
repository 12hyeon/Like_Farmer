package likelion.Spring_Like_Farmer.job.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import likelion.Spring_Like_Farmer.config.ResponseType;
import likelion.Spring_Like_Farmer.job.domain.Job;
import likelion.Spring_Like_Farmer.post.domain.Post;
import likelion.Spring_Like_Farmer.validation.ExceptionCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
public class JobDto {

    @Getter
    @Setter
    public static class RegisterJob {
        String name;
        int age;
        boolean foreigner;
        boolean koreanLanguage;
        String document;
        String contact;
    }

    @Getter
    public static class JobResponse extends ResponseType {

        @JsonInclude(NON_NULL)
        private Job job;

        public JobResponse(ExceptionCode exceptionCode) {
            super(exceptionCode);
        }
        public JobResponse(ExceptionCode exceptionCode, Job job) {
            super(exceptionCode);
            this.job = job;
        }
    }

    @Getter
    @Setter
    public static class JobListResponse extends ResponseType {

        List<Job> jobList;
        public JobListResponse(ExceptionCode exceptionCode, List<Job> jobList) {
            super(exceptionCode);
            this.jobList = jobList;
        }
    }
}

