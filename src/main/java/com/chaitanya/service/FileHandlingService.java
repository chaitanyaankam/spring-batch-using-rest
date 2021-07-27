package com.chaitanya.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.chaitanya.domain.entity.BatchFile;
import com.chaitanya.domain.model.FileImportDetails;
import com.chaitanya.exception.FileImportExcepiton;

public interface FileHandlingService {

	FileImportDetails saveFile(MultipartFile multipartFile, String sourceSystemId) throws FileImportExcepiton;

	void saveBatchFile(BatchFile batchFile) throws IOException, FileImportExcepiton;

	void cleanup() throws Exception;
}
