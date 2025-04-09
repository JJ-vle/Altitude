package edu.marques.altitude.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import edu.marques.altitude.AltitudeApp;
import edu.marques.altitude.R;

/**
 * Adapter personnalisé pour afficher une liste d'actualités.
 * Cet adapter est utilisé pour lier les données des actualités (titre et contenu) à une vue ListView.
 */
public class NewsAdapter extends BaseAdapter {
    private final List<News> news; // Liste des actualités à afficher.
    private final LayoutInflater mInflater; // LayoutInflater pour créer les vues des éléments.

    /**
     * Constructeur de la classe NewsAdapter.
     * @param news Liste des actualités à afficher.
     */
    public NewsAdapter(List<News> news) {
        this.news = news;
        mInflater = LayoutInflater.from(AltitudeApp.getContext());
    }

    /**
     * Obtient le nombre d'éléments dans la liste.
     * @return Le nombre d'éléments dans la liste.
     */
    public int getCount() {
        return news.size();
    }
    /**
     * Obtient l'élément à la position spécifiée dans la liste.
     * @param position La position de l'élément dans la liste.
     * @return L'élément à la position spécifiée.
     */
    public Object getItem(int position) {
        return news.get(position);
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
        layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.layout_news, parent, false);


        //(2) : Récupération des éléments.
        TextView title = layoutItem.findViewById(R.id.title);
        TextView content = layoutItem.findViewById(R.id.news);

        //(3) : Renseignement des valeurs.
        title.setText(news.get(position).getTitle());
        content.setText(news.get(position).getContent());

        //On retourne l'item créé.
        return layoutItem;
    }


}




