package com.chaitanya.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.chaitanya.domain.entity.BatchFile;
import com.chaitanya.domain.model.FileImportDetails;
import com.chaitanya.exception.FileImportExcepiton;
import com.chaitanya.repository.BatchFileRepository;
import com.chaitanya.utils.AppUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileHandlingServiceImpl implements FileHandlingService {

	@Value("${app.batch.file.landing-zone}")
	private String landingZone;
	
	@Autowired
	private FileStorageService fileSystemStorageService;
	
	@Autowired
	private BatchFileRepository batchFileRepository;

	@PostConstruct
	public void init() throws IOException {
		fileSystemStorageService.makeDirIfNotExists(landingZone);
	}

	@Override	
	public FileImportDetails saveFile(MultipartFile file, String sourceSystemId) throws FileImportExcepiton {
		try {
			fileSystemStorageService.requiresNoFile(landingZone, file.getOriginalFilename());
			BatchFile batchFile = BatchFile.builder()
				.id(AppUtils.randomID())
				.name(file.getOriginalFilename())
				.sourceSystemId(sourceSystemId)
				.size(file.getSize())
				.startTime(AppUtils.currentTimestamp())
				.build();
			
			fileSystemStorageService.copyFiletoDestination(file, landingZone);
			batchFile.setEndTime(AppUtils.currentTimestamp());
			saveBatchFile(batchFile);
			return AppUtils.convertToDTO(batchFile, new FileImportDetails());
		} catch(Exception e) {
			throw new FileImportExcepiton("Unable to save file", e);
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
	public void saveBatchFile(BatchFile batchFile) throws IOException, FileImportExcepiton {
		try {
			batchFileRepository.save(batchFile);
		} catch(Exception e) {
			fileSystemStorageService.deletePath(landingZone, batchFile.getName());
			throw new FileImportExcepiton("Unable to save file information to Database", e);
		}
	}

	@Override
	public void cleanup() throws Exception {
		log.info("Cleaning up landing zone  {}; backup operations not added yet", landingZone);
		fileSystemStorageService.clean(landingZone);
	}
}
