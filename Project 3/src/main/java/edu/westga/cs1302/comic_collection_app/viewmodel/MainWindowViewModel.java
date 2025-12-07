package edu.westga.cs1302.comic_collection_app.viewmodel;

import edu.westga.cs1302.comic_collection_app.model.Comic;
import edu.westga.cs1302.comic_collection_app.model.ComicCollection;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainWindowViewModel {

	private final StringProperty newCollectionName = new SimpleStringProperty();
	private final StringProperty newComicTitle = new SimpleStringProperty();
	private final IntegerProperty newComicIssue = new SimpleIntegerProperty();
	private final ObjectProperty<Comic> selectedComic = new SimpleObjectProperty<>();

	private final ObservableList<ComicCollection> collections = FXCollections.observableArrayList();
	private final ObservableList<Comic> comics = FXCollections.observableArrayList();

	private ComicCollection selectedCollection;

	// Collection properties
	public StringProperty getNewCollectionName() {
		return newCollectionName;
	}

	public ObservableList<ComicCollection> getCollections() {
		return collections;
	}

	// Comic properties
	public StringProperty getNewComicTitle() {
		return newComicTitle;
	}

	public IntegerProperty getNewComicIssue() {
		return newComicIssue;
	}

	public ObjectProperty<Comic> getSelectedComic() {
		return selectedComic;
	}

	public ObservableList<Comic> getComics() {
		return comics;
	}

	// Selected collection handling
	public void setSelectedCollection(ComicCollection collection) {
		this.selectedCollection = collection;
		if (collection != null) {
			this.comics.setAll(collection.getComics());
		} else {
			this.comics.clear();
		}
	}

	// Collection methods
	public void addCollection() {
		if (newCollectionName.get() == null || newCollectionName.get().isBlank()) {
			throw new IllegalArgumentException("Collection name cannot be empty");
		}
		ComicCollection collection = new ComicCollection(newCollectionName.get());
		collections.add(collection);
		newCollectionName.set("");
	}

	public void removeCollection() {
		if (selectedCollection != null) {
			collections.remove(selectedCollection);
			selectedCollection = null;
			comics.clear();
		}
	}

	// Comic methods
	public void addComic() {
		if (selectedCollection == null)
			return;
		Comic comic = new Comic(newComicTitle.get(), newComicIssue.get());
		selectedCollection.addComic(comic);
		comics.add(comic);
		newComicTitle.set("");
		newComicIssue.set(0);
	}

	public void removeComic() {
		if (selectedCollection == null || selectedComic.get() == null)
			return;
		selectedCollection.removeComic(selectedComic.get());
		comics.remove(selectedComic.get());
	}
}