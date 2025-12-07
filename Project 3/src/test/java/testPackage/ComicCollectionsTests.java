package testPackage;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.comic_collection_app.model.Comic;
import edu.westga.cs1302.comic_collection_app.model.ComicCollection;

class ComicCollectionTest {


    @Test
    void testValidCollectionCreation() {
        ComicCollection col = new ComicCollection("Marvel");
        assertEquals("Marvel", col.getName());
        assertTrue(col.getComics().isEmpty());
    }

    @Test
    void testCollectionCreationWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComicCollection("");
        });
    }

    @Test
    void testCollectionCreationWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ComicCollection(null);
        });
    }

    @Test
    void testAddComic() {
        ComicCollection col = new ComicCollection("DC");
        Comic comic = new Comic("Batman", 1);

        col.addComic(comic);

        assertEquals(1, col.getComics().size());
        assertTrue(col.getComics().contains(comic));
    }

    @Test
    void testRemoveComic() {
        ComicCollection col = new ComicCollection("Indie");
        Comic comic = new Comic("Spawn", 1);

        col.addComic(comic);
        col.removeComic(comic);

        assertTrue(col.getComics().isEmpty());
    }

    @Test
    void testGetComicsReturnsLiveList() {
        ComicCollection col = new ComicCollection("Image");
        Comic comic = new Comic("Invincible", 1);

        List<Comic> listRef = col.getComics();
        listRef.add(comic);   // should modify internal list

        assertEquals(1, col.getComics().size());
    }

    @Test
    void testToStringReturnsName() {
        ComicCollection col = new ComicCollection("Dark Horse");
        assertEquals("Dark Horse", col.toString());
    }
}