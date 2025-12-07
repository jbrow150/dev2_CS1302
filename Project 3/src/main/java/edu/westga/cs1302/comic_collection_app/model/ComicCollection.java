package edu.westga.cs1302.comic_collection_app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores a collection of comics.
 * 
 * @author CS
 * @version Fall 2025
 */
public class ComicCollection {

    private String name;
    private List<Comic> comics;

    public ComicCollection(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Collection name cannot be empty");
        }
        this.name = name;
        this.comics = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Comic> getComics() {
        return comics;
    }

    public void addComic(Comic comic) {
        comics.add(comic);
    }

    public void removeComic(Comic comic) {
        comics.remove(comic);
    }

    @Override
    public String toString() {
        return name;
    }
}
