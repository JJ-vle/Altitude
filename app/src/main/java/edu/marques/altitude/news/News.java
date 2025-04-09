package edu.marques.altitude.news;

/**
 * Représente une actualité avec un titre et un contenu.
 */
public class News {
    private String title;
    private String content;

    /**
     * Constructeur pour initialiser une actualité avec un titre et un contenu.
     * @param title   Le titre de l'actualité.
     * @param content Le contenu de l'actualité.
     */
    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Récupère le titre de l'actualité.
     * @return Le titre de l'actualité.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Récupère le contenu de l'actualité.
     * @return Le contenu de l'actualité.
     */
    public String getContent() {
        return content;
    }
}
