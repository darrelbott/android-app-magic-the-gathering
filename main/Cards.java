package com.darrelbott.finalproject.Model;

public class Cards {
    public int _id;
    public String card_name, type, rarity, artist;

    public Cards(int _id, String card_name, String type, String rarity, String artist) {
        this._id = _id;
        this.card_name = card_name;
        this.type = type;
        this.rarity = rarity;
        this.artist = artist;
    }

    public Cards() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return card_name;
    }

    public void setName(String card_name) {
        this.card_name = card_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}