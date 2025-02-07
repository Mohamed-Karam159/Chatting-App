package com.liqaa.client.controllers.FXMLcontrollers.components;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import com.liqaa.shared.models.entities.Category;
import javafx.util.Callback;

public class CategoryListController {

    @FXML
    private ListView<Category> categoryListView;

    @FXML
    public void initialize() {
        // Create fixed categories
        Category all = new Category(0, 0, "All");
        Category unread = new Category(1, 0, "Unread");
        Category groups = new Category(2, 0, "Groups");

        // Example of user-defined categories
        Category family = new Category(3, 1, "Family");
        Category work = new Category(4, 1, "Work");

        // Add all categories to the ListView
        categoryListView.getItems().addAll(all, unread, groups, family, work,family,work,family,work,family,work);

        // Add a listener to handle item selection
        categoryListView.setOnMouseClicked(this::handleCategoryClick);

        // Set the cell factory to only show the category name
        categoryListView.setCellFactory(new Callback<ListView<Category>, ListCell<Category>>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                return new ListCell<Category>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getCategoryName());
                        }
                    }
                };
            }
        });
    }

    private void handleCategoryClick(MouseEvent event) {
        Category selectedCategory = categoryListView.getSelectionModel().getSelectedItem();
        if (selectedCategory != null) {
            // Perform an action based on the selected category
            int categoryId = selectedCategory.getId();
            System.out.println("Category clicked: " + selectedCategory.getCategoryName());
            // Here you can perform any action based on categoryId
            // For example:
            if (categoryId == 3) {
                System.out.println("Perform action for Family category");
            } else if (categoryId == 4) {
                System.out.println("Perform action for Work category");
            }
            // You can add more conditions for other categories
        }
    }
}