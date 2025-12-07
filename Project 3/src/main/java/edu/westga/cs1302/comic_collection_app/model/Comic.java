package edu.westga.cs1302.comic_collection_app.model;

/**
 * Represents a Comic with a title and an issue number.
 * 
 * @author CS
 * @version Fall 2025
 */
public class Comic {

    private String title;
    private int issueNumber;

    public Comic(String title, int issueNumber) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (issueNumber <= 0) {
            throw new IllegalArgumentException("Issue number must be positive");
        }
        this.title = title;
        this.issueNumber = issueNumber;
    }

    public String getTitle() {
        return this.title;
    }

    public int getIssueNumber() {
        return this.issueNumber;
    }

    @Override
    public String toString() {
        return this.title + " #" + this.issueNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Comic)) return false;
        Comic other = (Comic) obj;
        return this.title.equals(other.title) && this.issueNumber == other.issueNumber;
    }

    @Override
    public int hashCode() {
        return this.title.hashCode() * 31 + this.issueNumber;
    }
}