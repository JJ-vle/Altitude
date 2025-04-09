package edu.marques.altitude.news;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.marques.altitude.R;

/**
 * Une activité pour afficher les actualités.
 */
public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ListView newsListView = findViewById(R.id.listeActus);
        TextView titleTextView = findViewById(R.id.titre);

        // Formater la date d'aujourd'hui.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());

        // Insérer le titre avec la date d'aujourd'hui dans le TextView.
        titleTextView.setText("Actualités " + currentDate);

        // Modifier la déclaration de la liste de nouvelles.
        List<News> displayedNews = new ArrayList<>();

        // Dans la méthode onCreate, remplacer les produits par des nouvelles.
        for (int i = 0; i < getResources().getStringArray(R.array.names_news).length; i++) {
            String title = getResources().getStringArray(R.array.names_news)[i];
            String content = getResources().getStringArray(R.array.descriptions_news)[i];
            displayedNews.add(new News(title, content));
        }

        // Créer et initialiser l'Adapter avec les nouvelles.
        NewsAdapter adapter = new NewsAdapter(displayedNews);
        newsListView.setAdapter(adapter);
    }
}
