package edu.marques.altitude;


import android.content.Context;

/**
 * Interface pour écouter les évènements.
 */
    public interface Clickable {
        void onClicItem(int itemIndex);
        void onRatingBarChange(int itemIndex, float value);

}
