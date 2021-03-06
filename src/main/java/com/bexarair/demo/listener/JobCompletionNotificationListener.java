package com.bexarair.demo.listener;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bexarair.demo.models.CityHospitalRecord;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<CityHospitalRecord> results = jdbcTemplate.query("SELECT year,zipcode,pedi_population,pedi_asthma_rate FROM city_hospital_records", new RowMapper<CityHospitalRecord>() {
                @Override
                public CityHospitalRecord mapRow(ResultSet rs, int row) throws SQLException {
                    return new CityHospitalRecord(rs.getString(1),rs.getString(2), rs.getInt(3),rs.getInt(4),rs.getString(5));
                }
            });

            for (CityHospitalRecord item : results) {
                log.info("Found <" + item + "> in the database.");
            }

        }
    }
}