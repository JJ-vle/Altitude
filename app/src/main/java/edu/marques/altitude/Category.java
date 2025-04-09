package edu.marques.altitude;

/**
 * Enumération représentant les catégories de produits.
 * Chaque catégorie correspond à un type de produit.
 */
public enum Category {
        ACCESSORIES,
        BACKPACK,
        JACKET,
        PANTS,
        SHOES,
        TENT;

        /**
         * Méthode permettant de transformer un string en Category.
         * @param categoryName (String ayant le nom d'une catégorie).
         */
        public static Category getCategory(String categoryName) {
                try {
                        return Category.valueOf(categoryName.toUpperCase());
                } catch (IllegalArgumentException e) {
                        return null; // Si le nom de la catégorie n'est pas trouvé, retourne null.
                }
        }
}

