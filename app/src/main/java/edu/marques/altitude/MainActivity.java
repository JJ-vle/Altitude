package edu.marques.altitude;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import edu.marques.altitude.news.NewsActivity;
import edu.marques.altitude.webservice.JsonAsyncTask;
import edu.marques.altitude.webservice.ProductService;

/**
 * Activité principale de l'application.
 * Cette activité sert de menu pour choisir quelle catégorie de produit afficher.
 */
public class MainActivity extends AppCompatActivity {
    private ProductService productService;
    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    /**
     * Méthode invoquée lors de la création de l'activité.
     * Dès que le menu est affiché une fois que l'application a été lancée, l'image du logo se met à vibrer
     * pendant quelques secondes, cela permet de captiver l'attention de l'utilisateur sur le logo de l'application.
     * @param savedInstanceState Les données fournies lors de la restauration de l'état de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        // Appel et récupération des données dans le JSON distant.
        String jsonURL = "https://raw.githubusercontent.com/LucaVizio/FilRouge411/main/informations.json";
        new JsonAsyncTask().execute(jsonURL);
        */

        // Trouver les boutons dans le layout.
        ImageButton buttonAccessories = findViewById(R.id.boutonCategorie1_Accessoires);
        ImageButton buttonBackpacks = findViewById(R.id.boutonCategorie2_Sacs);
        ImageButton buttonJackets = findViewById(R.id.boutonCategorie3_Vestes);
        ImageButton buttonPants = findViewById(R.id.boutonCategorie4_Pantalons);
        ImageButton buttonShoes = findViewById(R.id.boutonCategorie5_Chaussures);
        ImageButton buttonTents = findViewById(R.id.boutonCategorie6_Tentes);
        Button buttonNews = findViewById(R.id.boutonActus);

        // Ajouter un écouteur de clic à chaque bouton.
        buttonAccessories.setOnClickListener(v -> openProductActivity(Category.ACCESSORIES));
        buttonBackpacks.setOnClickListener(v -> openProductActivity(Category.BACKPACK));
        buttonJackets.setOnClickListener(v -> openProductActivity(Category.JACKET));
        buttonPants.setOnClickListener(v -> openProductActivity(Category.PANTS));
        buttonShoes.setOnClickListener(v -> openProductActivity(Category.SHOES));
        buttonTents.setOnClickListener(v -> openProductActivity(Category.TENT));

        buttonNews.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            startActivity(intent);
        });

        ImageView logoAppli = (ImageView) findViewById(R.id.imageLogo);
        ObjectAnimator animator_logo = (ObjectAnimator) AnimatorInflater.loadAnimator(AltitudeApp.getContext(), R.animator.animation);
        animator_logo.setTarget(logoAppli);
        animator_logo.start();

        // Définir un OnClickListener pour la vue.
        logoAppli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Démarrer l'animation lorsque la vue est cliquée.
                animator_logo.start();
            }
        });
    }

    /**
     * Méthode pour ouvrir ProductActivity avec la catégorie spécifiée.
     * @param category La catégorie de produit à afficher.
     */
    private void openProductActivity(Category category) {
        Intent intent = new Intent(MainActivity.this, ProductActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}

