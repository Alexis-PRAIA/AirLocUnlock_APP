package com.example.airlocunlockapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;


public class ReservationAdapter extends ArrayAdapter<Reservation> {
    public ReservationAdapter(Context context, int item_reservation, List<Reservation> reservations) {
        super(context, 0, reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reservation reservation = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_reservation, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageViewReservation);
        TextView textViewName = convertView.findViewById(R.id.textViewName);
        TextView textViewLocalisation = convertView.findViewById(R.id.textViewLocalisation);
        TextView textViewDate = convertView.findViewById(R.id.textViewDate);

        Picasso.get().load(reservation.getImage()).into(imageView);
        textViewName.setText(reservation.getName());
        textViewLocalisation.setText(reservation.getLocalisation());
        textViewDate.setText(reservation.getDate());

        return convertView;
    }
}

