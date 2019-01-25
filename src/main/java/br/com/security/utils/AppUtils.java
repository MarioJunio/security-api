package br.com.security.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class AppUtils {

	public static String md5(Long id) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(StandardCharsets.UTF_8.encode(String.valueOf(id)));
		return String.format("%032x", new BigInteger(1, md5.digest()));
	}

	public static String getOnlyDigits(String s) {

		if (s != null && !s.isEmpty())
			return s.replaceAll("[^0-9]+", "");

		return s;
	}

	public static String getSmartDate(Date date) {
		Date now = new Date();
		long dif = (now.getTime() - date.getTime()) / (1000 * 60 * 60 * 24);
		return dif == 0 ? "Hoje" : (dif == 1 ? "Ontem" : String.valueOf(dif));
	}
}
