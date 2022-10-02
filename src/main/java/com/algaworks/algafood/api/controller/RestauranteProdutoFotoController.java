package com.algaworks.algafood.api.controller;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestParam MultipartFile arquivo) {
		
		String nomeArquivo = UUID.randomUUID().toString() + "_" + arquivo.getOriginalFilename();
	
		var arquivoFoto = Path.of("/home/felipe/Desktop/upload", nomeArquivo);
		
		System.out.println(arquivo);
		System.out.println(arquivo.getContentType());
		
		try {
			arquivo.transferTo(arquivoFoto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
