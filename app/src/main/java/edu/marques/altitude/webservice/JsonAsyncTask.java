package edu.marques.altitude.webservice;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.marques.altitude.Product;
import edu.marques.altitude.ProductList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * JsonAsyncTask est une classe chargée de récupérer des données JSON à partir d'une URL.
 * Cette classe étend AsyncTask pour exécuter des opérations réseau de manière asynchrone.
 */
public class JsonAsyncTask extends AsyncTask<String, Void, String> {

    /**
     * Méthode exécutée en arrière-plan pour récupérer les données JSON depuis une URL.
     * @param urls Les URL à partir desquelles récupérer les données JSON.
     * @return Les données JSON sous forme de chaîne de caractères, ou null en cas d'erreur.
     */
    @Override
    protected String doInBackground(String... urls) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urls[0]).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Méthode appelée après l'exécution de la tâche en arrière-plan.
     * @param jsonString Les données JSON récupérées depuis l'URL.
     */
    @Override
    protected void onPostExecute(String jsonString) {
        if (jsonString != null) {
            try {
                List<Product> products = ProductList.getAllProducts();
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray productsArray = jsonObject.getJSONArray("products");
                for (int i = 0; i < productsArray.length(); i++) {
                    JSONObject productJson = productsArray.getJSONObject(i);
                    String name = productJson.getString("nomProduit");
                    String description = productJson.getString("descProduit");
                    String idPic = productJson.getString("id");
                    float price = (float) productJson.getDouble("prix");
                    float value = (float) productJson.getDouble("valeur");
                    String category = productJson.getString("categorie");
                    products.add(new Product(name, description, idPic, price, value, category));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}