package com.darrelbott.finalproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.darrelbott.finalproject.DB_Controller;
import com.darrelbott.finalproject.Model.Cards;
import com.darrelbott.finalproject.R;

import java.util.List;


class SearchViewHolder extends RecyclerView.ViewHolder {

    public TextView card_name, type, rarity, artist;
    public Button addCollectionButton;

    public SearchViewHolder(@NonNull View itemView) {
        super(itemView);
        card_name = (TextView)itemView.findViewById(R.id.card_name);
        type = (TextView)itemView.findViewById(R.id.type);
        rarity = (TextView)itemView.findViewById(R.id.rarity);
        artist = (TextView)itemView.findViewById(R.id.artist);
    }
}




public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private static final String TAG = SearchAdapter.class.getSimpleName();
    private Context mContext;
    private List<Cards> cards;
    private final DB_Controller dbController;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView card_name, type, rarity, artist;
        public Button addCollectionButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            card_name = (TextView)itemView.findViewById(R.id.card_name);
            type = (TextView)itemView.findViewById(R.id.type);
            rarity = (TextView)itemView.findViewById(R.id.rarity);
            artist = (TextView)itemView.findViewById(R.id.artist);
            addCollectionButton = (Button)itemView.findViewById(R.id.button_collection);
        }
    }


    public SearchAdapter(Context context, List<Cards> cards) {
        this.mContext = context;
        dbController = new DB_Controller(context,"collection.db",null,1);
        this.cards = cards;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_item, parent, false);
        return new SearchViewHolder(itemView);
    }

    Button addCollectionButton;

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, final int position) {


        final Cards cardsData = cards.get(position);
        /*
        holder.card_name.setText(cards.get(position).getName());
        holder.type.setText(cards.get(position).getType());
        holder.rarity.setText(cards.get(position).getRarity());
        holder.artist.setText(cards.get(position).getArtist());
        // below is a better way of doing it
        */
        holder.card_name.setText(cardsData.getName());
        holder.type.setText(cardsData.getType());
        holder.rarity.setText(cardsData.getRarity());
        holder.artist.setText(cardsData.getArtist());

        //Couldn't figure out how to use button to trigger, however the view as a whole works
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "on click " + position);

                String cardName = cardsData.getName();
                String cardType = cardsData.getType();
                String cardRarity = cardsData.getRarity();
                String cardArtist = cardsData.getArtist();

                // add data as needed
                dbController.insert_card("Darrel",cardName, cardType, cardRarity, cardArtist);
                Toast.makeText(mContext, "ADDED TO COLLECTION: " + cardName, Toast.LENGTH_LONG).show(); //confirm card was added
            }
        });
        /*
        //functionality of button "ADD TO COLLECTION"
        final DB_Controller controller = new DB_Controller(this,"collection.db",null,1);
        addCollectionButton.setOnClickListener(new View.OnClickListener() {

            //when button clicked, insert into collection database
            @Override
            public void onClick(View view) {

                //String card_name1 = card_name.setText(cards.get(position).getName());
                //switch is used in case we need to add more buttons elsewhere in the app
                //switch(view.getId()){
                //    case R.id.button_collection:
                controller.insert_card("drl","drl", "drl", "drl", "drl");
                //Toast.makeText(getApplicationContext(), "ADDED TO COLLECTION", Toast.LENGTH_LONG).show(); //confirm card was added
                //break;
                //}
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }


}
