package com.chaitanya.utils;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.beans.BeanUtils;


public class AppUtils {

	private AppUtils() {
	}

	public static String randomID() {
		return UUID.randomUUID().toString().substring(0, 5);
	}

	public static <K, T> K convertToDTO(T src, K trgt) {
		BeanUtils.copyProperties(src, trgt);
		return trgt;
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}
