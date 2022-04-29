package br.com.biblioteca.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
	
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}else {
				throw new RuntimeException("Arquivo não pode ser lido");
			}
		}catch(MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
	
	public Stream<Path> listarTodosArquivos(){
		try {
			return Files.walk(this.root, 1).filter(path-> !path.equals(this.root)).map(this.root::relativize);
		}catch(IOException e) {
			throw new RuntimeException("Não conseguimos ler os arquivos");
		}
	}
}
