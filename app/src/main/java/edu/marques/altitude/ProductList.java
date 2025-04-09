package edu.marques.altitude;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ProductList est une classe singleton chargée de gérer la liste des produits dans l'application.</p>
 *
 * <p>Raisons d'utiliser un singleton plutôt que d'autres technologies comme Parcelable :</p>
 * <ol><li> Centralisation des données : ProductList centralise les données des produits dans une seule classe, rendant la gestion des données plus cohérente et le maintien du code plus facile.</li>
 * <li> Accessibilité globale : Avec un singleton, les données des produits sont accessibles globalement à partir de n'importe quelle partie de l'application, simplifiant l'accès aux données et évitant le besoin de passer des données entre différentes parties de l'application.</li>
 * <li> Facilité de mise à jour : Les données des produits peuvent être mises à jour en un seul endroit avec un singleton, assurant la cohérence des données et évitant les incohérences potentielles qui pourraient survenir si les données étaient dispersées dans toute l'application.</li>
 * <li> Performance : Les singletons offrent généralement de meilleures performances en termes d'accès aux données par rapport à la sérialisation et à la désérialisation des objets Parcelable. Utiliser un singleton évite également le coût de la création et de la suppression répétées d'objets.</li>
 * <li> Simplicité : Implémenter et utiliser un singleton est généralement plus simple que d'implémenter et de gérer des objets Parcelable. Un singleton offre une solution plus directe et plus simple pour la gestion des données dans ce cas d'utilisation.</li>
 *</ol>
 * <p>En résumé, l'utilisation d'un singleton est préférable à l'utilisation de Parcelable dans ce cas car elle offre une solution plus simple, plus cohérente et plus performante pour la gestion des données de produits dans l'application.</p>
 */

public class ProductList {
    private final static List<Product> allProducts = new ArrayList<>();
    private static ProductList instance = null;

    // Constructeur privé pour empêcher l'instanciation directe de la classe.
    private ProductList(){
        // Initialisation de la liste de produits à partir des ressources.
        for(String name : AltitudeApp.getContext().getResources().getStringArray(R.array.mountain_products)){
            Product product = new Product(name);
            allProducts.add(product);
        }
    }

    /**
     * Méthode pour récupérer toutes les instances de produits.
     * @return Liste de tous les produits.
     */
    public static List<Product> getAllProducts(){
        if (instance == null) {
            instance = new ProductList();
        }
        return allProducts;
    }

    /**
     * Méthode pour récupérer un produit spécifique à partir de son index.
     * @param index Index du produit dans la liste.
     * @return Produit correspondant à l'index spécifié.
     */
    public static Product getProduct(int index){
        if (instance == null) {
            instance = new ProductList();
        }
        return allProducts.get(index);
    }

}
