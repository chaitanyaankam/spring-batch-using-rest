package com.chaitanya.domain.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileImportDetails {

	private String id;
	private String name;	
	private long size;
	private String sourceSystemId;	
	private Timestamp startTime;
	private Timestamp endTime;
}
