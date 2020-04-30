package br.com.security.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import br.com.security.rest.stub.CheckinFoto;

@Service
public class CheckinFotoStorage {

	public static final String IMAGE_DIRECTORY = "/usr/security/checkin-fotos/";

	public void save(CheckinFoto checkinFoto) throws IOException {

		byte[] decodedImg = Base64.decodeBase64(checkinFoto.getFoto());

		Path destinationFile = Paths.get(IMAGE_DIRECTORY, String.format("%d.jpg", checkinFoto.getCheckinId()));
		Files.write(destinationFile, decodedImg);
	}
	
	public byte[] read(Long checkinId) throws IOException {
		return Files.readAllBytes(Paths.get(IMAGE_DIRECTORY, String.format("%d.jpg", checkinId)));
	}
	
	public boolean hasFoto(Long checkinId) {
		return Files.exists(Paths.get(IMAGE_DIRECTORY, foto(checkinId)), LinkOption.NOFOLLOW_LINKS);
	}
	
	private String foto(Long checkinId) {
		return String.format("%d.jpg", checkinId);
	}
}
