package jse.metaco.project.dao;

import jse.metaco.project.MyConnectionPool;
import jse.metaco.project.model.Book;
import jse.metaco.project.model.Books;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class BooksDao {
    public boolean addBook(String text) {
        try {
            File file = new File(text);
            List<Book> bookList = convertXmlToObject(file);
            ResourceBundle rb = ResourceBundle.getBundle("databaseInfo");
            String table = rb.getString("database.table.name");
            String sql = "insert into " + table + " values(?,?,?,?,?,?,?)";
            Connection connection = MyConnectionPool.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            for (Book book : bookList) {
                ps.setString(1, book.getBook_id());
                ps.setString(2, book.getBooks_count());
                ps.setString(3, book.getIsbn());
                ps.setString(4, book.getAuthors());
                ps.setString(5, book.getTitle());
                ps.setString(6, book.getRating());
                ps.setString(7, book.getRatings_count());
                ps.executeUpdate();
            }
            ps.close();
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    //***************************************************************************************
    public List<Book> convertXmlToObject(File file) {
        try {
            List<Book> bookList = new ArrayList<>();
            JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Books books = (Books) jaxbUnmarshaller.unmarshal(file);
            bookList = books.getBook();
            return bookList;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
