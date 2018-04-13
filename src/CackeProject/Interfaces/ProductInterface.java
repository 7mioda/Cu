package CackeProject.Interfaces;


import CackeProject.Entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public interface ProductInterface {
    
    public ObservableList<Product> PRODUCTLIST = FXCollections.observableArrayList();
}
