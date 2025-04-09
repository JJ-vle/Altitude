package edu.marques.altitude.webservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import edu.marques.altitude.Product;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ProductService est une classe chargée de récupérer des produits à partir d'une URL distante.
 */
public class ProductService {
    private static final String URL = "https://raw.githubusercontent.com/LucaVizio/FilRouge411/main/informations.json";

    /**
     * Récupère une liste de produits à partir de l'URL spécifiée.
     * @return Une liste de produits.
     * @throws IOException Si une erreur d'entrée/sortie se produit lors de la récupération des données.
     */
    public List<Product> fetchProducts() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ObjectMapper mapper = new ObjectMapper();
            List<Product> products = mapper.readValue(response.body().string(), new TypeReference<List<Product>>() {});

            return products;
        }
    }
}
