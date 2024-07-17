package com.dio.santander.Bankline.api.cnab.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class CnabService {

    private final Path TargetLocation;
    private final JobLauncher jobLauncher;
    private final Job job;

    public CnabService(@Value("${path.location-dir}") String targetLocation,
                       @Qualifier("asyncJobLauncher") JobLauncher jobLauncher,
                       Job job) {
        TargetLocation = Paths.get(targetLocation);
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void uploadCnabfile(MultipartFile file) throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        var filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        var targetLocation = TargetLocation.resolve(filename);
        file.transferTo(targetLocation);

        var jobParameters = new JobParametersBuilder()
                .addJobParameter("cnab",file.getOriginalFilename(),String.class,true)
                .addJobParameter("cnabfiles","file:"+targetLocation.toString(),String.class,false)
                .toJobParameters();

        jobLauncher.run(job,jobParameters);
    }
}
