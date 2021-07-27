package com.chaitanya.domain.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ChaitanyaAnkam
 * JobDetails - This class wraps all the necessary job information
 */
@Slf4j
@Getter
@Setter
public class JobDetails {
	
	private String fileId;
	
	/*
	 * parameters - parameters in the form of Key and value pairs
	 * */
	private Map<String, String> parameters;
	
	public JobParameters getJobParameters() {
		JobParametersBuilder builder = new JobParametersBuilder();
		if(Objects.isNull(parameters))
			parameters = new HashMap<>();
		parameters
			.entrySet()
			.forEach(entry -> {
				log.info("key {} value {} ", entry.getKey(), entry.getValue());
				builder.addString(entry.getKey(), entry.getValue());
			});
		return builder.toJobParameters();
	}
}
