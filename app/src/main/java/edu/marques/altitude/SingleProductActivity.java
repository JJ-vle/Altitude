package edu.marques.altitude;

import static edu.marques.altitude.AltitudeApp.CHANNEL_ID;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

/**
 * Activité affichant les détails d'un produit individuel.
 * Cette activité affiche les détails d'un produit, y compris son nom, son image, sa description, sa notation et son prix.
 * Elle permet également d'envoyer une notification lorsqu'un bouton est cliqué.
 *
 * la classe contient également un objectAnimator permettant un mouvement de l'image du produit afin d'attirer l'oeil du client.
 */
public class SingleProductActivity extends AppCompatActivity {
    private int notificationId = 0;
    private int productIndex;

    /**
     * Méthode invoquée lors de la création de l'activité.
     * Une fois la transition sur cette activité effectuée, l'image du produit se met à vibrer pendant quelques secondes.
     * Cette animation permet d'attirer l'attention de l'utilisateur sur le produit dès l'ouverture de la page du produit.
     * @param savedInstanceState Les données fournies lors de la restauration de l'état de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        // Récupérer les éléments de l'interface utilisateur.
        TextView productNameTextView = findViewById(R.id.nomProduit); // Le nom du produit.
        ImageView productImageView = findViewById(R.id.imageProduit); // L'image du produit.
        RatingBar productRatingBar = findViewById(R.id.noteProduit); // La note du produit.
        TextView productDescTextView = findViewById(R.id.descTexte); // La description du produit.
        Button priceButton = findViewById(R.id.boutonAchat); // Le bouton d'achat.

        // Récupérer les données du produit envoyées via Intent.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productIndex = extras.getInt(getString(R.string.PRODUCT_KEY));
            Product product = ProductList.getProduct(productIndex);

            // Afficher les informations du produit.
            productNameTextView.setText(product.getName()); // On actualise le nom du produit.
            productImageView.setImageResource(product.getPicture()); // On actualise l'image du produit.
            productDescTextView.setText(product.getDescription()); // On actualise la description du produit.
            productRatingBar.setRating(product.getValue()); // On actualise la note du produit.
            String priceStr = product.getPrice() + ""; // On récupère le nouveau prix.
            priceButton.setText(priceStr+" €"); // On actualise le prix du produit.

            priceButton.setOnClickListener(v -> {
                String title = "Altitude";
                String message = "Merci pour l'achat du produit suivant : " + product.getName();
                sendNotificationOnChannel(title, message, CHANNEL_ID, NotificationCompat.PRIORITY_DEFAULT);
            });

        }
        // Un objectAnimator est instancié pour générer une animation de l'image de chaque produit.
        ObjectAnimator animator_image_product = (ObjectAnimator) AnimatorInflater.loadAnimator(AltitudeApp.getContext(), R.animator.animation);
        animator_image_product.setTarget(productImageView);
        animator_image_product.start();
    }

    /**
     * Méthode quand le bouton de retour en arrière est activé.
     */
    @Override
    public void onBackPressed() {
        // Appel de la méthode pour mettre à jour la note du produit avant de quitter l'activité.
        updateProductRating();
        super.onBackPressed();
    }

    /**
     * Méthode pour mettre à jour la note du produit dans la liste des produits.
     */
    private void updateProductRating() {
        // Récupérer la nouvelle valeur de la note du produit depuis la RatingBar.
        RatingBar productRatingBar = findViewById(R.id.noteProduit);
        float newRating = productRatingBar.getRating();

        // Mettre à jour la note du produit dans la liste des produits.
        ProductList.getProduct(productIndex).setValue(newRating);

        // Notifier à l'adaptateur de la liste que les données ont changé.
        ProductActivity.getAdapter().notifyDataSetChanged();
    }

    /**
     * Méthode pour envoyer une notification sur un canal spécifié
     * (dans ce projet qu'un seul canal n'est créé et utilisé).
     * @param title Le titre de la notification.
     * @param message Le message de la notification.
     * @param channelId L'identifiant du canal de notification.
     * @param priority La priorité de la notification.
     */
    private void sendNotificationOnChannel(String title, String message, String channelId, int priority){
        NotificationCompat.Builder notification = new NotificationCompat.Builder( AltitudeApp.getContext(), channelId)
                .setSmallIcon (R.drawable.ic_mountain_empty)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(priority);
        AltitudeApp.getNotificationManager().notify(++notificationId, notification.build());
    }
}