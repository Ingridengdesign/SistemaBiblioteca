package br.com.biblioteca.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

	private final Path root = Paths.get("upload");
	
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		}catch(Exception e) {
			throw new RuntimeException("Não foi possível gravar o arquivo! " + e.getMessage());
		}
	}
}
