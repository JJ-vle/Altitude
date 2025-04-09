package edu.marques.altitude;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activité affichant une liste de produits filtrés par catégorie et évalués selon une valeur minimale spécifiée.
 */
public class ProductActivity extends AppCompatActivity implements Clickable {
    private static ProductAdapter adapter;
    private Category category;

    // Liste des produits triés.
    private final static List<Product> filteredProducts = new ArrayList<>();

    // Liste des produits affichés.
    private final List<Product> displayedProducts = new ArrayList<>();

    /**
     * Méthode invoquée lors de la création de l'activité.
     * @param savedInstanceState Les données fournies lors de la restauration de l'état de l'activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ListView listView = findViewById(R.id.listeProduits);

        // Récupérer la catégorie passée en paramètre de l'intent.
        category = (Category) getIntent().getSerializableExtra("category");

        // Filtrer les produits en fonction de la catégorie.
        for (Product product : ProductList.getAllProducts()) {
            if (product.getCategory() == category) {
                filteredProducts.add(product);
                displayedProducts.add(product);
            }
        }

        // Création et initialisation de l'Adapter avec les produits filtrés.
        adapter = new ProductAdapter(displayedProducts, this);

        // Initialisation de la liste avec les données filtrées.
        listView.setAdapter(adapter);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int seekBarValue, boolean b) {

                String SBvalStr = "" + seekBarValue;
                ((TextView)findViewById(R.id.valeur)).setText(SBvalStr);

                // Effacer les produits affichés actuels.
                displayedProducts.clear();

                // Filtrer à nouveau les produits en fonction de la valeur de la SeekBar.
                for(Product product : filteredProducts) {
                    if(product.getValue() >= seekBarValue && product.getCategory() == category) {
                        displayedProducts.add(product);
                    }
                }

                // Notifier à l'Adapter que les données ont changé.
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * Implémentation de Clickable.
     * Méthode invoquée lorsqu'un élément de la liste est cliqué.
     * @param itemIndex L'index de l'élément cliqué dans la liste.
     */
    @Override
    public void onClicItem(int itemIndex) {
        Intent intent = new Intent(ProductActivity.this, SingleProductActivity.class);
        intent.putExtra(getString(R.string.PRODUCT_KEY), findIndexInList(itemIndex));
        startActivity(intent);
    }

    /**
     * Implémentation de Clickable.
     * Méthode invoquée lorsqu'une évaluation est modifiée.
     * @param itemIndex L'index de l'élément dont l'évaluation a été modifiée dans la liste.
     * @param value La nouvelle valeur de l'évaluation.
     */
    @Override
    public void onRatingBarChange(int itemIndex, float value) {
        ProductList.getProduct(findIndexInList(itemIndex)).setValue(value);
        displayedProducts.get(itemIndex).setValue(value);
        adapter.notifyDataSetChanged();
    }

    /**
     * Méthode pour trouver l'index dans la liste.
     * @param index L'index de l'élément à rechercher dans la liste affichée.
     * @return L'index de l'élément dans la liste complète des produits.
     */
    private int findIndexInList(int index) {
        Product productToFind = displayedProducts.get(index);
        for (int i = 0; i < ProductList.getAllProducts().size(); i++) {
            if (ProductList.getProduct(i).equals(productToFind)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Obtient l'Adapter des produits.
     * @return L'Adapter des produits.
     */
    public static ProductAdapter getAdapter(){
        return adapter;
    }

}
