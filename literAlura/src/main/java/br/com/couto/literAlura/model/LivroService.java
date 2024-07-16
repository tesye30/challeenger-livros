package br.com.couto.literAlura.model;

import br.com.couto.literAlura.repository.AutorRepository;
import br.com.couto.literAlura.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public List<Object[]> obterDadosAutor(Long autorId) {
        return autorRepository.obterDadosAutor(autorId);
    }
}