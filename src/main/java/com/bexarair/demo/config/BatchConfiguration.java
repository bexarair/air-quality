package com.bexarair.demo.config;

import com.bexarair.demo.models.CityHospitalRecord;
import com.bexarair.demo.models.StateHospitalRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.api.chunk.ItemReader;
import javax.batch.api.chunk.ItemWriter;
import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public FlatFileItemReader<CityHospitalRecord> reader() {
        FlatFileItemReader<CityHospitalRecord> reader = new FlatFileItemReader<CityHospitalRecord>();
        reader.setResource(new ClassPathResource("cityHospital.csv"));
        reader.setLineMapper(new DefaultLineMapper<CityHospitalRecord>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName","email","age" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<CityHospitalRecord>() {{
                setTargetType(CityHospitalRecord.class);
            }});
        }});
        return reader;
    }

    @Bean
    public CityItemProcessor processor() {
        return new CityItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<CityHospitalRecord> writer() {
        JdbcBatchItemWriter<CityHospitalRecord> writer = new JdbcBatchItemWriter<CityHospitalRecord>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CityHospitalRecord>());
//        writer.setSql("INSERT INTO person (first_name, last_name,email,age) VALUES (:firstName, :lastName,:email,:age)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<CityHospitalRecord, CityHospitalRecord> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}

