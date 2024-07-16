package br.com.couto.literAlura.model;

// Recebe os dados vindos da API
public enum Idiomas {
    PORTUGUESE("pt"),
    ESPANISH("es"),
    ENGLISH("en"),
    FRENCH("fr");
    private String idiomasGutendex;

    Idiomas(String idiomasGutendex) {
        this.idiomasGutendex = idiomasGutendex;
    }
    public static Idiomas fromString(String text) {
        for (Idiomas categoria : Idiomas.values()) {
            if (categoria.idiomasGutendex.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhum encontrado para a string fornecida: " + text);
    }
}