package cafe94.CustomerScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import cafe94.DBManager;
import cafe94.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author MANVENDRA
 */


public class PersonOverviewController {

    @FXML private TableView<Order> orderHistoryView;
    @FXML private TableColumn<Order, LocalDateTime> orderDateColumn;
    @FXML private TableColumn<Order, Integer> orderNoColumn;
    @FXML private TableColumn<Order, String> orderTypeColumn;
    @FXML private TableColumn<Order, String> itemColumn;
    @FXML private Label userNameLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label customerIDLabel;

    Connection connection = null;
    ResultSet rsOrders = null;
    PreparedStatement pst = null;

    /**
     * The following function can be used to go to the home screen.
     * @param event Used to get information in current scene.
     * @throws IOException Throws if input fails.
     */
    public void homeButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/cafe94/CustomerScreen/CustomerHomeScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    /**
     * The following function can be used to go to the booking screen.
     * @param event Used to get information in current scene.
     * @throws IOException Throws if input fails.
     */
    public void bookingButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/cafe94/CustomerScreen/CustomerCreateBooking.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    /**
     * The following function can be used to go to the menu screen.
     * @param event Used to get information in current scene.
     * @throws IOException Throws if input fails.
     */
    public void menuButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/cafe94/CustomerScreen/menu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    /**
     * The following function can be used to sign out of the system.
     * @param event Used to get information in current scene.
     * @throws IOException Throws if input fails.
     */
    public void logoutButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/cafe94/Login.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * Finds the customer details from the users database.
     * @throws SQLException Throws if SQLite query fails.
     */
    public void initialize() throws SQLException {
        int userID = cafe94.UserDetails.getInstance().getUserID();

        cafe94.UserDetails.getInstance().getUserID();
        userNameLabel.setText(cafe94.UserDetails.getInstance().getUsern());
        firstNameLabel.setText(cafe94.UserDetails.getInstance().getUserFirst());
        lastNameLabel.setText(cafe94.UserDetails.getInstance().getUserLast());
        customerIDLabel.setText("" + userID);


        String query = "SELECT orderNo, itemName, orderType FROM orders WHERE customerID =" + userID;
        connection = DBManager.DBConnection();
        pst = connection.prepareStatement(query);
        rsOrders = pst.executeQuery();
        ObservableList<Order> itemList = getOrderList(rsOrders);
        itemColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("itemName"));
        orderNoColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderNo"));
        orderTypeColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("orderType"));
        orderHistoryView.setItems(itemList);
    }

    /**
     * Gets the list of orders from specific customer and adds it to the database.
     * @param rsOrders Result of the query.
     * @return Result of the query.
     * @throws SQLException Throws if SQLite query fails.
     */
    private ObservableList<Order> getOrderList(ResultSet rsOrders) throws SQLException {
        ObservableList<Order> tempItemList = FXCollections.observableArrayList();
        while(rsOrders.next()){
            Order temp = new Order();
            temp.setOrderNo(rsOrders.getInt("orderNo"));
            temp.setItemName(rsOrders.getString("itemName"));
            temp.setOrderType(rsOrders.getString("orderType"));
            tempItemList.add(temp);
        }
        return tempItemList;
    }
}
