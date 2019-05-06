package com.darrelbott.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import com.darrelbott.finalproject.Adapter.CollectionSearchAdapter;
import com.darrelbott.finalproject.Database.Database2;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

// Not finished/implemented, but produced for assignment purposes
public class CollectionActivity extends  AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CollectionSearchAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    Database2 database;

    //used for inserting card into decks database
    DB_Controller2 controller;
    TextView card_name, type, rarity, artist;

    Button addCollectionButton;

    //@Nullable
    //@Override
    //public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //    return inflater.inflate(R.layout.fragment_search, container, false);
    //}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);
        //init View
        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_bar);
        //materialSearchBar.inflateMenu(R.layout.activity_main);
        //init DB
        database = new Database2(this);

        //setup search bar
        //materialSearchBar.setHint("Search");
        //materialSearchBar.setCardViewElevation(10);
        loadSuggestList();

        //This is used when typing in search bar suggesting words like those that have been typed
        materialSearchBar.addTextChangeListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            // method for when user types in search bar, return suggested strings
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int after) {

                List<String> suggest = new ArrayList<>();
                for (String search : suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase()))
                        suggest.add(search);
                }
                materialSearchBar.setLastSuggestions(suggest);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        //This is used when user has typed the word(s) needed to search and hits the search icon
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {

            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled)
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        //init Adapter default set all result
        //adapter = new SearchAdapter(this, database.getFriends());
        adapter = new CollectionSearchAdapter(this, database.getCards());
        recyclerView.setAdapter(adapter);
    }


    private void startSearch(String text) {
        //adapter = new SearchAdapter(this, database.getFriendByName(text));
        adapter = new CollectionSearchAdapter(this, database.getCardsByName(text));
        recyclerView.setAdapter(adapter);
    }


    //for populating suggestion list when search bar has been activated
    private void loadSuggestList() {
        suggestList = database.getNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }

}

