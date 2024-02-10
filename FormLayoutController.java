package jse.metaco.project;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jse.metaco.project.dao.BooksDao;
import jse.metaco.project.model.Book;
import jse.metaco.project.model.Books;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;


public class FormLayoutController {
    @FXML
    public TextField tfDataset;
    @FXML
    public Button bDatabase, bJson, bChooseFile;
    @FXML
    public Label lmaxRate, lminRate, lmaxRateCount, lauthor;


    public void convertJSON(ActionEvent actionEvent) {
        try {
            File file = new File(tfDataset.getText());
            String name = file.getName();
            String str = name.substring(0, name.length() - 3);
            FileWriter fileWriter = new FileWriter("D:\\" + str + "json");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Books books = (Books) jaxbUnmarshaller.unmarshal(file);
            List<Book> list = books.getBook();
            Gson gson = new Gson();
            String jsonText = gson.toJson(list);
            bufferedWriter.write(jsonText);
            bufferedWriter.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Converted to JSON");
            alert.show();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //************************************************************************************************
    public void convertDB(ActionEvent actionEvent) {
        BooksDao booksDao = new BooksDao();
        boolean done = booksDao.addBook(tfDataset.getText());
        if (done) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Added To Database Table");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Not Added To Database Table");
            alert.show();
        }
    }

    //************************************************************************************************
    public void buttonClicked() {
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        Button button = new Button(tfDataset.getText());
        final File[] selectedFile = {new File(tfDataset.getText())};
        button.setOnAction(e -> {
            selectedFile[0] = fileChooser.showOpenDialog(primaryStage);
        });
        if (selectedFile[0].exists()) {
            displayValue();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("File not found!");
            alert.show();
        }
    }

    //*************************************************************************************************
    public String maxRating() {
        try {
            double max = 3.57;
            String name = "";
            Map<String, Double> map = new HashMap<>();
            File file = new File(tfDataset.getText());
            JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Books books = (Books) jaxbUnmarshaller.unmarshal(file);
            List<Book> list = books.getBook();
            for (Book book : list) {
                map.put(book.getTitle(), Double.parseDouble(book.getRating()));
            }
            for (String key : map.keySet()) {
                Double rating = map.get(key);
                if (rating > max) {
                    max = rating;
                    name = key;
                }
            }
            return name;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    //***************************************************************************************************
    public String minRating() {
        try {
            double min = 4.40004;
            String name = "";
            Map<String, Double> map = new HashMap<>();
            File file = new File(tfDataset.getText());
            JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Books books = (Books) jaxbUnmarshaller.unmarshal(file);
            List<Book> list = books.getBook();
            for (Book book : list) {
                map.put(book.getTitle(), Double.parseDouble(book.getRating()));
            }
            for (String key : map.keySet()) {
                Double rating = map.get(key);
                if (rating < min) {
                    min = rating;
                    name = key;
                }
            }
            return name;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    //***************************************************************************************************
    public String maxRatingCount() {
        try {
            Long max = 1000L;
            String name = "";
            Map<String, Long> map = new HashMap<>();
            File file = new File(tfDataset.getText());
            JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Books books = (Books) jaxbUnmarshaller.unmarshal(file);
            List<Book> list = books.getBook();
            for (Book book : list) {
                map.put(book.getTitle(), Long.parseLong(book.getRatings_count()));
            }
            for (String key : map.keySet()) {
                Long ratingCount = map.get(key);
                if (ratingCount > max) {
                    max = ratingCount;
                    name = key;
                }
            }
            return name;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    //*****************************************************************************************************
    public String authorOfMaxRating() {
        try {
            double max = 3.57;
            String name = "";
            Map<String, Double> map = new HashMap<>();
            File file = new File(tfDataset.getText());
            JAXBContext jaxbContext = JAXBContext.newInstance(Books.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Books books = (Books) jaxbUnmarshaller.unmarshal(file);
            List<Book> list = books.getBook();
            for (Book book : list) {
                map.put(book.getAuthors(), Double.parseDouble(book.getRating()));
            }
            for (String key : map.keySet()) {
                Double rating = map.get(key);
                if (rating > max) {
                    max = rating;
                    name = key;
                }
            }
            return name;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    //***************************************************************************************************
    public void displayValue() {
        lmaxRate.setText(maxRating());
        lminRate.setText(minRating());
        lmaxRateCount.setText(maxRatingCount());
        lauthor.setText(authorOfMaxRating());
    }

}




