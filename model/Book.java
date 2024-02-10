package jse.metaco.project.model;

public class Book {
    private String book_id;
    private String books_count;
    private String isbn;
    private String authors;
    private String title;
    private String rating;
    private String ratings_count;

    public Book() {
    }

    public Book(String book_id, String books_count, String isbn, String authors, String title, String rating, String ratings_count) {
        super();
        this.book_id = book_id;
        this.books_count = books_count;
        this.isbn = isbn;
        this.authors = authors;
        this.title = title;
        this.rating = rating;
        this.ratings_count = ratings_count;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBooks_count() {
        return books_count;
    }

    public void setBooks_count(String books_count) {
        this.books_count = books_count;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(String ratings_count) {
        this.ratings_count = ratings_count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id='" + book_id + '\'' +
                ", books_count='" + books_count + '\'' +
                ", isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", ratings_count='" + ratings_count + '\'' +
                '}';
    }
}