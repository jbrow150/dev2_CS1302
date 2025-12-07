package edu.westga.cs1302.comic_collection_app.viewmodel;

import edu.westga.cs1302.comic_collection_app.model.Comic;
import edu.westga.cs1302.comic_collection_app.model.ComicCollection;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ViewModel for finding a comic by title and issue number.
 */
public class FindComicViewModel {

    private StringProperty searchTitle;
    private IntegerProperty searchIssueNumber;
    private ObjectProperty<Comic> result;

    private Map<String, Comic> comicMap;

    public FindComicViewModel(List<ComicCollection> collections) {
        this.searchTitle = new SimpleStringProperty();
        this.searchIssueNumber = new SimpleIntegerProperty();
        this.result = new SimpleObjectProperty<>();

        // Build a map for searching (key = title#issue)
        this.comicMap = new HashMap<>();
        for (ComicCollection col : collections) {
            for (Comic c : col.getComics()) {
                String key = buildKey(c.getTitle(), c.getIssueNumber());
                this.comicMap.put(key, c);
            }
        }
    }

    private String buildKey(String title, int issueNumber) {
        return title.trim().toLowerCase() + "#" + issueNumber;
    }

    public void searchComic() {
        String key = buildKey(this.searchTitle.get(), this.searchIssueNumber.get());
        Comic found = this.comicMap.get(key);
        this.result.set(found);
    }

    public StringProperty searchTitleProperty() {
        return this.searchTitle;
    }

    public IntegerProperty searchIssueNumberProperty() {
        return this.searchIssueNumber;
    }

    public ReadOnlyObjectProperty<Comic> resultProperty() {
        return this.result;
    }
}