package com.bexarair.demo.config;

import com.bexarair.demo.models.StateHospitalRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.api.chunk.ItemReader;
import javax.batch.api.chunk.ItemWriter;

//@Configuration
//@EnableBatchProcessing
//public class BatchConfiguration {
//
//    @Bean
//    public Job job(JobBuilderFactory jobBuilderFactory,
//                   StepBuilderFactory stepBuilderFactory,
//                   ItemReader<StateHospitalRecord> itemReader,
//                   ItemProcessor<StateHospitalRecord, StateHospitalRecord> itemProcessor,
//                   ItemWriter<StateHospitalRecord> itemWriter
//    ){
//        Step step = stepBuilderFactory.get("file-load")
//                .<StateHospitalRecord, StateHospitalRecord> chunk(10)
//                .reader(itemReader)
//                .processor(itemProcessor)
//                .writer(itemWriter)
//                .build();
//    }
//}
