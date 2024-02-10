package jse.metaco.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement
public class Books {

    private List<Book> book;

    public Books() {
    }

    public Books(List<Book> book) {
        super();
        this.book = book;
    }

    @XmlElement
    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Books{" +
                "book=" + book +
                '}';
    }
}
