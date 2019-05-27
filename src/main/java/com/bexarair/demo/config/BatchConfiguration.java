package com.bexarair.demo.config;

import com.bexarair.demo.listener.JobCompletionNotificationListener;
import com.bexarair.demo.models.CityHospitalRecord;
import com.bexarair.demo.processor.CityItemProcessor;
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
import org.springframework.core.io.FileSystemResource;

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

    public static final String uploadingDir = System.getProperty("user.dir") + "/src/main/resources/static/files/cityHospital.csv";

    @Bean
    public FlatFileItemReader<CityHospitalRecord> reader() {
        FlatFileItemReader<CityHospitalRecord> reader = new FlatFileItemReader<CityHospitalRecord>();
        reader.setResource(new FileSystemResource(uploadingDir));
        reader.setLineMapper(new DefaultLineMapper<CityHospitalRecord>() {{
            setLineTokenizer(new DelimitedLineTokenizer(",") {{
                setNames(new String[] { "year","zipCode", "pediAsthmaCases","pediPopulation","pediAsthmaRate" });
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
            writer.setSql("INSERT INTO city_hospital_records (year,zipCode,pediAsthmaCases,pediPopulation,pediAsthmaRate) VALUES (:year,:zipCode,:pediAsthmaCases,:pediPopulation,:pediAsthmaRate)");
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

