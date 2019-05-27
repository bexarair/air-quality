package com.bexarair.demo.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.bexarair.demo.models.CityHospitalRecord;

public class CityItemProcessor implements ItemProcessor<CityHospitalRecord, CityHospitalRecord> {

    private static final Logger log = LoggerFactory.getLogger(CityItemProcessor.class);

    @Override
    public CityHospitalRecord process(final CityHospitalRecord record) throws Exception {

        final CityHospitalRecord transformedRecord = new CityHospitalRecord(record.getYear(),record.getZipCode(), record.getPediAsthmaCases(),record.getPediPopulation(),record.getPediAsthmaRate());

        log.info("Converting (" + record + ") into (" + transformedRecord + ")");

        return transformedRecord;
    }

}