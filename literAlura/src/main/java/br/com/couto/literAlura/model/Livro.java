package br.com.couto.literAlura.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_livros") // Cria a tabela tb_livros no banco de dados
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor; // Mapeamento muitos para um

    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private Double numeroDownloads;

    public Livro(){
    }

    // Atribui o Autor recebido como parâmetro à propriedade autor da Classe Livro
    public Livro(DadosLivro dados, Autor autor) {
        this.titulo = dados.titulo();
        this.autor = autor;
        this.idiomas = Idiomas.fromString(dados.idiomas().get(0)); // Pega o primeiro idioma da lista de idiomas
        this.numeroDownloads = dados.numeroDownloads();
    }

    // Itera sobre a lista results e, cria um novo livro para cada item e, atribui o Autor correspondente
    public Livro(List<DadosLivro> results) {
        for (DadosLivro dados : results) {
            Autor autor = new Autor(dados.autores()
                    .get(0).nome(), dados.autores()
                    .get(0).anoDeNascimento(), dados.autores()
                    .get(0).anoDeFalecimento()); // Pega o primeiro autor da lista de autores
            Livro livro = new Livro(dados, autor);
            // Adicione o livro à lista de livros do autor
            autor.getLivros().add(livro);
        }
    }

    // Cria um novo Autor para cada DadosAutor na lista, e atribui à propriedade Autor da Classe Livro
    public Livro(String tiulo, List<String> idiomas, Double numeroDownloads,  List<DadosAutor> autores) {
        this.titulo = tiulo;
        this.idiomas = Idiomas.fromString(idiomas.get(0)); // Pega o primeiro idioma da lista de idiomas
        this.numeroDownloads = numeroDownloads;
        Autor autor = new Autor(autores.get(0)
                .nome(), autores.get(0)
                .anoDeNascimento(), autores.get(0)
                .anoDeFalecimento()); // Pega o primeiro autor da lista de autores
        this.autor = autor;

    }

    // Construtor com dois parâmetros
    public Livro(Livro dados, Autor autor) {
        this.titulo = dados.titulo;
        this.autor = autor;
        this.idiomas = dados.idiomas;
        this.numeroDownloads = dados.numeroDownloads;
    }

    // Construtor padrão
    public Livro(Dados dados) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdioma(Idiomas idioma) {
        this.idiomas = idioma;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "titulo='" + titulo + '\'' +
                ", autores=" + autor +
                ", idioma='" + idiomas + '\'' +
                ", numeroDownloads=" + numeroDownloads;
    }

}