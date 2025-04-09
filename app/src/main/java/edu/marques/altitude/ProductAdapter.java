package edu.marques.altitude;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;
import java.util.List;

/**
 * <p>Adapter personnalisé pour afficher une liste de produits.</p>
 * <p>Cet adapter est utilisé pour lier les données de produits à une vue ListView.</p>
 */
public class ProductAdapter extends BaseAdapter {
    private final List<Product> products; // Liste des produits à afficher.
    private final LayoutInflater mInflater; // LayoutInflater pour créer les vues des éléments.
    private final Clickable callBackActivity; // Activité appelante pour gérer les événements.

    /**
     * Constructeur de la classe ProductAdapter.
     * @param products Liste des produits à afficher.
     * @param callBackActivity Activité appelante pour gérer les événements.
     */
    public ProductAdapter(List<Product> products, Clickable callBackActivity) {
        this.products = products;
        this.callBackActivity = callBackActivity;
        mInflater = LayoutInflater.from(AltitudeApp.getContext());
    }

    /**
     * Obtient le nombre d'éléments dans la liste.
     * @return Le nombre d'éléments dans la liste.
     */
    public int getCount() {
        return products.size();
    }

    /**
     * Obtient l'élément à la position spécifiée dans la liste.
     * @param position La position de l'élément dans la liste.
     * @return L'élément à la position spécifiée.
     */
    public Object getItem(int position) {
        return products.get(position);
    }

    /**
     * Obtient l'ID de l'élément à la position spécifiée dans la liste.
     * @param position La position de l'élément dans la liste.
     * @return L'ID de l'élément à la position spécifiée.
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Obtient la vue qui représente un élément de la liste.
     * @param position La position de l'élément dans la liste.
     * @param convertView La vue à réutiliser si possible.
     * @param parent Le parent de la vue convertie.
     * @return La vue qui représente l'élément de la liste.
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;

        //(1) : Réutilisation des layouts impossible (mémorisation des valeurs du ratingBar).
        //layoutItem = (ConstraintLayout) (convertView == null ? mInflater.inflate(R.layout.character_layout, parent, false) : convertView);
        layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.layout_product, parent, false);


        //(2) : Récupération des éléments.
        TextView name = layoutItem.findViewById(R.id.name);
        TextView price = layoutItem.findViewById(R.id.price);
        TextView grade = layoutItem.findViewById(R.id.valeur);
        ImageView picture = layoutItem.findViewById(R.id.picture);
        RatingBar ratingBar = layoutItem.findViewById(R.id.noteProduit);

        //(3) : Renseignement des valeurs.
        name.setText(products.get(position).getName());
        String priceStr = products.get(position).getPrice()+ " €";
        price.setText(priceStr);
        grade.setText((new DecimalFormat("##.##")).format( products.get(position).getValue()) ) ;
        picture.setImageResource(products.get(position).getPicture());
        ratingBar.setRating(products.get(position).getValue());

        ratingBar.setOnRatingBarChangeListener((ratingBar1, value, b) -> callBackActivity.onRatingBarChange(position,value));

        layoutItem.setOnClickListener( click -> callBackActivity.onClicItem(position));
        //On retourne l'item créé.
        return layoutItem;
    }
}




