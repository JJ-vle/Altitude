package edu.marques.altitude;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import java.util.Objects;

/**
 * Classe représentant l'application Altitude.
 * Cette classe est utilisée pour initialiser divers composants de l'application,
 * comme le contexte ou le canal de notification.
 *
 * /!\ NOTIFICATIONS :
 * S'assurer que le téléphone ne soit pas en "Ne pas déranger"
 * et que les notifications sont activées pour l'application.
 */

public class AltitudeApp extends Application {

    /// CONTEXT
    private static Context context;

    public static Context getContext() {
        return context;
    }

    /// GESTION DES NOTIFICATONS
    public static final String CHANNEL_ID = "channelAltitude";
    private static NotificationManager notificationManager;

    public static NotificationManager getNotificationManager(){
        return notificationManager;
    }

    /**
     * Méthode de création de channel de notifications.
     * Dans notre cas, il n'y a qu'un seul cannal d'importance par défaut ( créé dans le onCreate() ).
     * @param name Le nom du channel.
     * @param description La description du channel.
     * @param importance l'importance du channel dans lequel on transmet la notification (ici à 0 par défaut).
     **/
    private void createNotificationChannel(String name, String description, int importance) {
        //Si API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Ne peut pas être changé plus tard.
            notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

    /**
     * <p>Méthode appelée lors du lancement de l'application qui crée :
     *    <ul>
     *        <li>le channel de notifications</li>
     *        <li>le contexte de l'application</li>
     *    </ul>
     * </p>
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        createNotificationChannel("Altitude channel", "Channel de notification d'Altitude", NotificationManager.IMPORTANCE_DEFAULT);
    }
}
