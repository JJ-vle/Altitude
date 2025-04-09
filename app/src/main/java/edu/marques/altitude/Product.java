package edu.marques.altitude;

import java.util.Objects;
import java.util.Random;

/**
 * <p>Représente un produit de l'application Altitude.</p>
 * <p>Chaque produit a un nom, une description, une image, un prix, une valeur et une catégorie.</p>
 */
public class Product {
    private final String name;
    private final String description;
    private final int picture;
    private final float price;
    private float value;
    private final Category category;

    /**
     * Constructeur de la classe Product.
     * @param name (Le nom du produit).
     * @param description (La description du produit).
     * @param idPic (L'identifiant de l'image correspondante).
     * @param price (Le prix du produit).
     * @param value (La note du produit).
     * @param category (La catégorie du produit).
     */
    public Product(String name, String description, String idPic, float price, float value, String category) {
        this.name = name;
        this.description = description;
        this.picture = AltitudeApp.getContext().getResources().getIdentifier(idPic, "drawable", AltitudeApp.getContext().getPackageName());
        this.price = price;
        this.value = value;
        this.category = Category.getCategory(category);
    }

    /**
     * Constructeur de la classe Product.
     * @param name (Le nom du produit).
     */
    public Product(String name) {
        this.name = name;
        this.description = getDescriptionFromResources();
        this.picture = getPictureFromResources();
        this.price = getPriceFromResources();
        this.value = getValueFromResources();
        this.category = getCategoryFromResources();
    }

    /**
     * Récupère la catégorie liée au produit via un string array (product_categories) dans strings.xml.
     * @return Retourne une catégorie.
     */
    private Category getCategoryFromResources() {
        String[] categories = AltitudeApp.getContext().getResources().getStringArray(R.array.product_categories);
        int position = getPosition();
        if (position >= 0 && position < categories.length) {
            String categoryString = categories[position];
            return Category.valueOf(categoryString);
        }
        return null;
    }

    /**
     * Récupère la description liée au produit via un string array (product_descriptions) dans strings.xml.
     * @return Retourne une description.
     */
    private String getDescriptionFromResources() {
        String[] descriptions = AltitudeApp.getContext().getResources().getStringArray(R.array.product_descriptions);
        int position = getPosition();
        return position >= 0 && position < descriptions.length ? descriptions[position] : null;
    }

    /**
     * Récupère l'image liée au produit via un string array (product_pictures) dans strings.xml.
     * @return Retourne une image.
     */
    private int getPictureFromResources() {
        String[] picturesId = AltitudeApp.getContext().getResources().getStringArray(R.array.product_pictures);
        int position = getPosition();
        String id = position >= 0 && position < picturesId.length ? picturesId[position] : null;
        return AltitudeApp.getContext().getResources().getIdentifier(id, "drawable", AltitudeApp.getContext().getPackageName());
    }

    /**
     * Récupère le prix lié au produit via un string array (product_prices) dans strings.xml.
     * @return Retourne un prix (float).
     */
    private float getPriceFromResources() {
        String[] price = AltitudeApp.getContext().getResources().getStringArray(R.array.product_prices);
        int position = getPosition();
        if (position >= 0 && position < price.length) {
            try {
                return Float.parseFloat(price[position]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0.0f; // Valeur par défaut si la conversion échoue ou si la position est invalide
    }

    /**
     * Récupère la note liée au produit via un string array (product_values) dans strings.xml.
     * @return Retourne une note (float).
     */
    private float getValueFromResources() {
        String[] values = AltitudeApp.getContext().getResources().getStringArray(R.array.product_values);
        int position = getPosition();
        if (position >= 0 && position < values.length) {
            try {
                return Float.parseFloat(values[position]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return 0.0f; // Note par défaut si la conversion échoue ou si la position est invalide.
    }

    /**
     * Obtient la position du produit dans les ressources.
     * @return La position du produit.
     */
    private int getPosition(){
        String[] names = AltitudeApp.getContext().getResources().getStringArray(R.array.mountain_products);
        for (int i=0 ; i<names.length ; i++){
            if (names[i].equals(name)) return i;
        }
        return -1;
    }

    /// GETTERS

    /**
     * Obtient la description du produit.
     * @return La description du produit.
     */
    public String getDescription(){
       return description;
    }

    /**
     * Obtient le nom du produit.
     * @return Le nom du produit.
     */
    public String getName() {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * Obtient le prix du produit.
     * @return Le prix du produit.
     */
    public float getPrice(){
        return this.price;
    }

    /**
     * Obtient l'identifiant de l'image correspondante au produit.
     * @return L'identifiant de l'image.
     */
    public int getPicture(){ return picture; }

    /**
     * Obtient la note du produit.
     * @return La note du produit.
     */
    public float getValue(){
        return value;
    }

    /// SETTER

    /**
     * Définit la note du produit.
     * @param value La note à définir.
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Redéfinition de la méthode toString pour afficher le nom du produit.
     * @return Le nom du produit.
     */
    @Override
    public String toString(){
        return name;
    }

    /**
     * Obtient la catégorie du produit.
     * @return La catégorie du produit.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Redéfinition de la méthode equals pour comparer deux objets de type Product.
     * @param o L'objet à comparer.
     * @return True si les objets sont égaux, false sinon.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Float.compare(product.value, value) == 0 && picture == product.picture && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(category, product.category);
    }
}