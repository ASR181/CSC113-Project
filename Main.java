package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    // Hotel Object
    private Hotel velora;

    public void init() {
        // Loading data
        velora = new Hotel("Velora Resorts", 500);
        velora.loadFromFile("HotelData.dat");
        System.out.println("Data loaded into GUI.");
    }

    public void stop() {
        // Saves data
        velora.saveToFile("HotelData.dat");
        System.out.println("Data saved from GUI. Goodbye!");
    }

    public void start(Stage primaryStage) {
        
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Title
        Label lblTitle = new Label("Velora Resorts Management");
        lblTitle.getStyleClass().add("title-label");

        // Add reservation
        VBox addBox = createSectionBox("Add New Reservation");
        GridPane addGrid = new GridPane();
        addGrid.setVgap(10); addGrid.setHgap(10);
        
        TextField txtRoomNum = new TextField(); txtRoomNum.setPromptText("Room Number");
        TextField txtGuestName = new TextField(); txtGuestName.setPromptText("Guest Name");
        TextField txtGuestAge = new TextField(); txtGuestAge.setPromptText("Guest Age");
        TextField txtDays = new TextField(); txtDays.setPromptText("Number of Days");
        
        ComboBox<String> cbRoomType = new ComboBox<>();
        cbRoomType.getItems().addAll("StandardRoom", "Suite", "PentHouse");
        cbRoomType.setValue("StandardRoom"); // Default

        Button btnAdd = new Button("Add Reservation");
        btnAdd.getStyleClass().add("navy-button");
        btnAdd.setMaxWidth(Double.MAX_VALUE); 

        addGrid.add(new Label("Room Number:"), 0, 0); addGrid.add(txtRoomNum, 1, 0);
        addGrid.add(new Label("Guest Name:"), 0, 1); addGrid.add(txtGuestName, 1, 1);
        addGrid.add(new Label("Guest Age:"), 0, 2); addGrid.add(txtGuestAge, 1, 2);
        addGrid.add(new Label("Staying Days:"), 0, 3); addGrid.add(txtDays, 1, 3);
        addGrid.add(new Label("Room Type:"), 0, 4); addGrid.add(cbRoomType, 1, 4);
        
        addBox.getChildren().addAll(addGrid, btnAdd);

        // Remove reservation
        VBox removeBox = createSectionBox("Remove Reservation");
        HBox removeHBox = new HBox(10);
        removeHBox.setAlignment(Pos.CENTER_LEFT);
        
        TextField txtRemoveRoom = new TextField(); txtRemoveRoom.setPromptText("Room Number");
        Button btnRemove = new Button("Remove Room");
        btnRemove.getStyleClass().add("navy-button");
        
        removeHBox.getChildren().addAll(new Label("Room Number:"), txtRemoveRoom, btnRemove);
        removeBox.getChildren().add(removeHBox);

        // Revenue
        VBox revBox = createSectionBox("Financials");
        HBox revHBox = new HBox(10);
        revHBox.setAlignment(Pos.CENTER_LEFT);
        
        TextField txtRevRoom = new TextField(); txtRevRoom.setPromptText("Room Num for Rev");
        Button btnRoomRev = new Button("Room Revenue");
        btnRoomRev.getStyleClass().add("navy-button");
        Button btnTotalRev = new Button("Total Hotel Revenue");
        btnTotalRev.getStyleClass().add("navy-button");
        
        revHBox.getChildren().addAll(txtRevRoom, btnRoomRev, btnTotalRev);
        revBox.getChildren().add(revHBox);

        // adding section to root
        root.getChildren().addAll(lblTitle, addBox, removeBox, revBox);

        // button clicking

        btnAdd.setOnAction(e -> {
            try {
                int roomNum = Integer.parseInt(txtRoomNum.getText());
                String name = txtGuestName.getText();
                int age = Integer.parseInt(txtGuestAge.getText());
                int days = Integer.parseInt(txtDays.getText());
                String type = cbRoomType.getValue();

                if(name.isEmpty()) {
                    showResultFrame("Error", "Guest name cannot be empty.");
                    return;
                }

                Guest guest = new Guest(name, age);
                boolean success = velora.addReservation(roomNum, days, guest, type);
                
                if(success) {
                    showResultFrame("Success", "Reservation added successfully for " + name + " in Room " + roomNum);
                    txtRoomNum.clear(); txtGuestName.clear(); txtGuestAge.clear(); txtDays.clear();
                } else {
                    showResultFrame("Error", "Failed to add. Room " + roomNum + " is likely already booked!");
                }
            } catch (NumberFormatException ex) {
                showResultFrame("Input Error", "Please ensure Room Number, Age, and Days are valid numbers.");
            }
        });

        btnRemove.setOnAction(e -> {
            try {
                int roomNum = Integer.parseInt(txtRemoveRoom.getText());
                velora.removeReservation(roomNum);
                showResultFrame("Success", "Reservation for Room " + roomNum + " has been removed.");
                txtRemoveRoom.clear();
            } catch (NumberFormatException ex) {
                showResultFrame("Input Error", "Please enter a valid numeric Room Number.");
            } catch (RoomNotFoundException ex) {
                showResultFrame("Not Found", ex.getMessage());
            }
        });

        btnRoomRev.setOnAction(e -> {
            try {
                int roomNum = Integer.parseInt(txtRevRoom.getText());
                double rev = velora.getRoomRevnue(roomNum, 0);
                if (rev == 0) {
                    showResultFrame("Result", "Revenue for Room " + roomNum + " is 0.0\n(Room is empty or doesn't exist).");
                } else {
                    showResultFrame("Result", "Revenue for Room " + roomNum + " is: " + rev + " R.S.");
                }
            } catch (NumberFormatException ex) {
                showResultFrame("Input Error", "Please enter a valid numeric Room Number.");
            }
        });

        btnTotalRev.setOnAction(e -> {
            double total = velora.getTotalRevnue();
            showResultFrame("Total Revenue", "The Total Revenue for Velora Resorts is:\n" + total + " R.S.");
        });

        // first stage
        Scene scene = new Scene(root, 550, 600);
        
        // linking to CSS file
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        primaryStage.setTitle("Velora Resorts - Input Frame");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method
    private VBox createSectionBox(String title) {
        VBox box = new VBox(10);
        box.getStyleClass().add("section-box");
        Label lbl = new Label(title);
        lbl.getStyleClass().add("section-title");
        box.getChildren().add(lbl);
        return box;
    }

    // display result frame
    private void showResultFrame(String title, String message) {
        Stage resultStage = new Stage();
        resultStage.setTitle("Result: " + title);

        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        Label header = new Label(title);
        header.getStyleClass().add("title-label");
        header.setStyle("-fx-font-size: 18px;");

        TextArea txtDisplay = new TextArea(message);
        txtDisplay.setEditable(false);
        txtDisplay.setWrapText(true);
        txtDisplay.setPrefRowCount(4);
        txtDisplay.getStyleClass().add("result-text-area");

        Button btnClose = new Button("OK");
        btnClose.getStyleClass().add("navy-button");
        btnClose.setOnAction(e -> resultStage.close());

        root.getChildren().addAll(header, txtDisplay, btnClose);

        Scene scene = new Scene(root, 350, 200);
        
        // linking CSS file
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        
        resultStage.setScene(scene);
        resultStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}