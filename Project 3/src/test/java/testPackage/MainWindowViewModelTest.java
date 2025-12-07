package testPackage;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.comic_collection_app.model.Comic;
import edu.westga.cs1302.comic_collection_app.model.ComicCollection;
import edu.westga.cs1302.comic_collection_app.viewmodel.MainWindowViewModel;

class MainWindowViewModelTest {

	 private MainWindowViewModel vm;
	    private ComicCollection marvel;

	    @BeforeEach
	    void setup() {
	        this.vm = new MainWindowViewModel();
	        this.marvel = new ComicCollection("Marvel");
	        this.vm.getCollections().add(this.marvel);
	        this.vm.setSelectedCollection(this.marvel);
	    }

	    // --------------------------------------------------------
	    // Collection Tests
	    // --------------------------------------------------------

	    @Test
	    void testAddCollectionSuccess() {
	        this.vm.getNewCollectionName().set("DC");
	        this.vm.addCollection();

	        assertEquals(2, this.vm.getCollections().size());
	        assertEquals("DC", this.vm.getCollections().get(1).getName());
	    }

	    @Test
	    void testAddCollectionRejectsEmptyName() {
	        this.vm.getNewCollectionName().set("");
	        assertThrows(IllegalArgumentException.class, () -> this.vm.addCollection());
	    }

	    @Test
	    void testAddCollectionRejectsBlankName() {
	        this.vm.getNewCollectionName().set("   ");
	        assertThrows(IllegalArgumentException.class, () -> this.vm.addCollection());
	    }

	    @Test
	    void testRemoveCollectionSuccess() {
	        this.vm.setSelectedCollection(this.marvel);
	        this.vm.removeCollection();

	        assertEquals(0, this.vm.getCollections().size());
	        assertTrue(this.vm.getComics().isEmpty());
	    }

	    @Test
	    void testRemoveCollectionWhenNoneSelectedDoesNothing() {
	        this.vm.setSelectedCollection(null);
	        this.vm.removeCollection();

	        assertEquals(1, this.vm.getCollections().size());
	    }

	    // --------------------------------------------------------
	    // Comic Tests
	    // --------------------------------------------------------

	    @Test
	    void testAddComicSuccess() {
	        this.vm.getNewComicTitle().set("Spider-Man");
	        this.vm.getNewComicIssue().set(1);

	        this.vm.addComic();

	        assertEquals(1, this.marvel.getComics().size());
	        Comic comic = this.marvel.getComics().get(0);
	        assertEquals("Spider-Man", comic.getTitle());
	        assertEquals(1, comic.getIssueNumber());
	    }

	    @Test
	    void testAddComicDoesNothingIfNoCollectionSelected() {
	        this.vm.setSelectedCollection(null);

	        this.vm.getNewComicTitle().set("Batman");
	        this.vm.getNewComicIssue().set(5);

	        this.vm.addComic();

	        assertTrue(this.vm.getComics().isEmpty());
	    }

	    @Test
	    void testAddComicRejectsInvalidComic() {
	        this.vm.getNewComicTitle().set("");
	        this.vm.getNewComicIssue().set(0);

	        assertThrows(IllegalArgumentException.class, () -> this.vm.addComic());
	    }

	    @Test
	    void testRemoveComicSuccess() {
	        Comic comic = new Comic("Thor", 3);
	        this.marvel.addComic(comic);
	        this.vm.getComics().add(comic);
	        this.vm.getSelectedComic().set(comic);

	        this.vm.removeComic();

	        assertTrue(this.marvel.getComics().isEmpty());
	        assertTrue(this.vm.getComics().isEmpty());
	    }

	    @Test
	    void testRemoveComicDoesNothingWhenNoneSelected() {
	        Comic comic = new Comic("Thor", 3);
	        this.marvel.addComic(comic);
	        this.vm.getComics().add(comic);

	        this.vm.getSelectedComic().set(null); // selecting nothing

	        this.vm.removeComic();

	        assertEquals(1, this.vm.getComics().size());
	    }

	    @Test
	    void testSetSelectedCollectionUpdatesComicList() {
	        Comic comic = new Comic("Hulk", 2);
	        this.marvel.addComic(comic);

	        // refresh list
	        this.vm.setSelectedCollection(this.marvel);

	        assertEquals(1, this.vm.getComics().size());
	        assertEquals("Hulk", this.vm.getComics().get(0).getTitle());
	    }

	    @Test
	    void testSetSelectedCollectionClearsNonNull() {
	        this.vm.setSelectedCollection(null);
	        assertTrue(this.vm.getComics().isEmpty());
	    }
	}