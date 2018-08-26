/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import businessObjects.Customer;
import businessObjects.Receipt_Item;
import businessObjects.Stock_Item;
import businessObjects.Stock_On_Hand;
import businessObjects.receipt;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import businessObjects.Stock_On_Hand;
import businessObjects.stock_request;
import businessObjects.stock_request_item;

/**
 *
 * @author Alex
 */
public class FXMLDocumentFXML_StockOptions_MainController implements Initializable
{

    public FXMLDocumentFXML_StockOptions_MainController() throws Exception
    {
        this.custlist = Customer.getCustomers();
        this.stockOnHandList = Stock_On_Hand.getStockOnHand();
    }
    @FXML
    AnchorPane mainWindow;

    //Add Customer Fields and Labels
    @FXML
    private Label custNamelbl;
    @FXML
    private Label custCompanylbl;
    @FXML
    private TextField custNametxt;
    @FXML
    private TextField custCompanytxt;
    @FXML
    private Button addCustBtn;

    //Customer TableView Components
    @FXML
    private TableView<Customer> custTable;
    @FXML
    private TableColumn<Customer, Integer> custIDColumn;
    @FXML
    private TableColumn<Customer, String> custNameColumn;
    @FXML
    private TableColumn<Customer, String> custCompanyColumn;
    public ObservableList<Customer> custlist;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //STOCK ON HAND TAB
    @FXML
    private TableView<Stock_On_Hand> stockOnHandTable;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandIDColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandNameColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, Integer> stockOnHandQuantityColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandCustomerColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, LocalDate> stockOnHandTransactioDateColoumn;
    ObservableList<Stock_On_Hand> stockOnHandList = FXCollections.observableArrayList();

    @FXML
    private RadioButton searchByItem;
    @FXML
    private RadioButton searchByCustomer;
    @FXML
    private RadioButton searchByReceipt;
    @FXML
    private RadioButton searchByDate;
    ToggleGroup searchButtonGroup;

    @FXML
    private TextField searchStockTxt;
    @FXML
    private DatePicker datePicker;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //RECEIPTS TAB
    ObservableList<Receipt_Item> newReceiptList = FXCollections.observableArrayList();
    receipt newReceipt;

    //Buttons
    @FXML
    private ComboBox customersCB;
    @FXML
    private ComboBox categoryCB;
    @FXML
    private VBox receiveNewStockOptions;
    @FXML
    private TextField newStockItemTxt;
    @FXML
    private HBox stockReceiptTables;

    //Receipts Table Views
    @FXML
    private TextField newItemQuantityTxt;

    //All Stock Items
    @FXML
    private TableView<Stock_Item> receiptsSelectTable;
    @FXML
    private TableColumn<Stock_Item, String> receiptsStockIDColoumn;
    @FXML
    private TableColumn<Stock_Item, String> receiptsStockNameColoumn;
    @FXML
    private TableColumn<Stock_Item, String> receiptsStockCategoryColoumn;

    //New Receipt Table
    @FXML
    private TableView<Stock_On_Hand> newReceiptTable;
    @FXML
    private TableColumn<Stock_On_Hand, String> newReceiptStockIDColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> newReceiptStockNameColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, Integer> newReceiptStockQuantityColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> newReceiptStockCustomerColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, LocalDate> newReceiptStockTransactioDateColoumn;
    ObservableList<Stock_On_Hand> newReceiptTableList = FXCollections.observableArrayList();

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stock Out Components
    ObservableList<stock_request_item> newRequestList = FXCollections.observableArrayList();
    @FXML
    private TableView<Stock_On_Hand> stockOnHandOutTable;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandOutIDColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandNameOutColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, Integer> stockOnHandQuantityOutColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> stockOnHandCustomerOutColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, LocalDate> stockOnHandTransactioDateOutColoumn;
    ObservableList<Stock_On_Hand> newStockRequestList = FXCollections.observableArrayList();

    @FXML
    private TableView<Stock_On_Hand> newStockRequestTable;
    @FXML
    private TableColumn<Stock_On_Hand, String> newStockRequestIDColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> newStockRequestNameColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, Integer> newStockRequestQuantityColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, String> newStockRequestCustomerColoumn;
    @FXML
    private TableColumn<Stock_On_Hand, LocalDate> newStockRequestTransactioDateColoumn;

    @FXML
    private HBox stockOutTables;
    @FXML
    private ComboBox stockOutCustomersCB;
    @FXML
    private TextField newStockRequestTxt;

    private stock_request newStockRequest;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CUSTOMER TAB EVENTS
    @FXML
    public void changeCustNameCellEvent(CellEditEvent edittedCell) throws Exception
    {
        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();
        Customer.alterCustomerName(selectedCustomer.getCustName(), (String) edittedCell.getNewValue());

        custlist = Customer.getCustomers();
        custTable.setItems(custlist);
    }

    @FXML
    public void changeCustCompanyCellEvent(CellEditEvent edittedCell) throws Exception
    {
        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();
        Customer.alterCompanyName(selectedCustomer.getCustCompany(), (String) edittedCell.getNewValue());
        //selectedCustomer.setCustCompany((String) edittedCell.getNewValue());
        custlist = Customer.getCustomers();
        custTable.setItems(custlist);
    }

    @FXML
    public void NewCustomerButtonPushed()
    {
        custNamelbl.setVisible(true);
        custCompanylbl.setVisible(true);
        custNametxt.setVisible(true);
        custCompanytxt.setVisible(true);
        addCustBtn.setVisible(true);
    }

    @FXML
    public void addCustomerButtonPushed() throws Exception
    {
        Customer newCustomer = new Customer("CUS" + Customer.getKey(), custNametxt.getText(), custCompanytxt.getText());
        Customer.addCustomer(newCustomer.getCustCode(), newCustomer.getCustName(), newCustomer.getCustCompany());
        this.custlist = Customer.getCustomers();
        custTable.setItems(custlist);

        custNamelbl.setVisible(false);
        custCompanylbl.setVisible(false);
        custNametxt.clear();
        custNametxt.setVisible(false);
        custCompanytxt.clear();
        custCompanytxt.setVisible(false);
        addCustBtn.setVisible(false);

        //Populate Combobox
        for (Customer cust : custlist)
        {
            customersCB.getItems().add(cust.getCustCode() + " " + cust.getCustCompany());
        }
    }

    @FXML
    public void removeCustomerButtonPushed() throws Exception
    {
        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();
        Customer.deleteCustomer(selectedCustomer.getCustCode());
        this.custlist = Customer.getCustomers();
        custTable.setItems(custlist);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stock on Hand
    @FXML
    public void searchByButtonSelected()
    {
        if (this.searchButtonGroup.getSelectedToggle().equals(this.searchByDate))
        {
            datePicker.setVisible(true);
        } else
        {
            datePicker.setVisible(false);
        }
    }

    @FXML
    public void searchStockButtonPushed()
    {
        if (this.searchButtonGroup.getSelectedToggle().equals(this.searchByItem))
        {
            String searchItem = searchStockTxt.getText();
            stockOnHandList = Stock_On_Hand.searchTableByItem(searchItem);

        } else if (this.searchButtonGroup.getSelectedToggle().equals(this.searchByReceipt))
        {
            String searchItem = searchStockTxt.getText();
            stockOnHandList = Stock_On_Hand.searchByReceipt(searchItem);

        } else if (this.searchButtonGroup.getSelectedToggle().equals(this.searchByCustomer))
        {
            String searchItem = searchStockTxt.getText();
            stockOnHandList = Stock_On_Hand.searchByCustomer(searchItem);

        } else if (this.searchButtonGroup.getSelectedToggle().equals(this.searchByDate))
        {
            LocalDate searchItem = datePicker.getValue();
            stockOnHandList = Stock_On_Hand.searchByDate(searchItem);

        }
        stockOnHandTable.setItems(stockOnHandList);

    }

    @FXML
    public void allStockButtonPushed()
    {
        this.stockOnHandList = Stock_On_Hand.getStockOnHand();
        stockOnHandTable.setItems(stockOnHandList);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Receipts Tab Events
    @FXML
    public void recieveNewStockButtonPushed() throws Exception
    {
        receiveNewStockOptions.setVisible(true);
        stockReceiptTables.setVisible(true);
        customersCB.setVisible(true);
        categoryCB.setVisible(true);
        //StockList - Receipts
        receiptsStockIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Item, String>("stockID"));
        receiptsStockNameColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Item, String>("stockName"));
        receiptsStockCategoryColoumn.setCellValueFactory(new PropertyValueFactory<Stock_Item, String>("category"));

        ObservableList<Stock_Item> stockItemByCustomer = Stock_Item.getStockItems();
        receiptsSelectTable.setItems(stockItemByCustomer);
    }

    @FXML
    public String comboBoxSelection() throws Exception
    {

        String selectedCust;
        String custID;
        selectedCust = customersCB.getValue().toString();
        custID = selectedCust.substring(0, selectedCust.indexOf((" ")));

        return custID;
    }

    @FXML
    public String categoryCBSelection() throws Exception
    {
        String category = categoryCB.getValue().toString();
        String itemCategory;
        switch (category)
        {
            case "General":
                itemCategory = "GEN";
                receiptsSelectTable.setItems(Stock_Item.getStockItems(category));
                break;
            case "Perishable":
                itemCategory = "PRB";
                receiptsSelectTable.setItems(Stock_Item.getStockItems(category));
                break;
            case "Fragile":
                itemCategory = "FRG";
                receiptsSelectTable.setItems(Stock_Item.getStockItems(category));
                break;
            case "Medical":
                itemCategory = "MED";
                receiptsSelectTable.setItems(Stock_Item.getStockItems(category));
                break;
            case "Extra Large Items":
                itemCategory = "XLI";
                receiptsSelectTable.setItems(Stock_Item.getStockItems(category));
                break;
            case "All":
                itemCategory = "";
                receiptsSelectTable.setItems(Stock_Item.getStockItems());
                break;
            default:
                throw new AssertionError();
        }
        //receiptsSelectTable.setItems(Stock_Item.getStockItems(category));

        return itemCategory;
    }

    @FXML
    public void addNewStockButtonPushed() throws Exception
    {
        String newItemName = newStockItemTxt.getText();
        Stock_Item.addNewStockItem(categoryCBSelection(), newItemName, categoryCB.getValue().toString());
        receiptsSelectTable.setItems(Stock_Item.getStockItems());
    }

    @FXML
    public void addStockToReceiptButtonPushed() throws Exception
    {
        Stock_Item selectedStockItem = receiptsSelectTable.getSelectionModel().getSelectedItem();

        System.out.println("selectedStockItem.getStockID(): " + selectedStockItem.getStockID());
        System.out.println("comboBoxSelection(): " + comboBoxSelection());
        System.out.println("Integer.parseInt(newItemQuantityTxt.getText()): " + Integer.parseInt(newItemQuantityTxt.getText()));
        System.out.println("selectedStockItem.getStockName(): " + selectedStockItem.getStockName());
        System.out.println("Customer.getCustomer(comboBoxSelection()).getCustCompany(): " + Customer.getCustomer(comboBoxSelection()).getCustCompany());
        System.out.println("Local Date");

        newReceiptTableList.add(new Stock_On_Hand(selectedStockItem.getStockID(), comboBoxSelection(), Integer.parseInt(newItemQuantityTxt.getText()), selectedStockItem.getStockName(), Customer.getCustomer(comboBoxSelection()).getCustCompany(), LocalDate.now()));

        newReceiptStockIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("stockID"));
        newReceiptStockNameColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("stockName"));
        newReceiptStockQuantityColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, Integer>("stockAvailableQuantity"));
        newReceiptStockCustomerColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("customerCompanyName"));
        newReceiptStockTransactioDateColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, LocalDate>("transactionDate"));

        newReceiptTable.setItems(newReceiptTableList);
    }

    @FXML
    public void removeStockFromReceiptButtonPushed()
    {
        Stock_On_Hand selectedStockItem = newReceiptTable.getSelectionModel().getSelectedItem();
        newReceiptList.remove(selectedStockItem);
        newReceiptTable.setItems(newReceiptTableList);
    }

    @FXML
    public void clearStock()
    {
        newReceiptList.clear();
        newReceiptTableList.clear();
        newReceiptTable.setItems(newReceiptTableList);
    }

    @FXML
    public void CompleteReceiptButtonPushed() throws Exception
    {
        //String ReceiptItemName, String ReceiptID, String StockID, Integer quantity

        newReceipt = new receipt(Stock_Item.getKey("RCT"), Customer.getCustomer(comboBoxSelection()).getCustCode(), Customer.getCustomer(comboBoxSelection()).getCustCompany(), LocalDate.now());

        for (Stock_On_Hand item : newReceiptTableList)
        {
            newReceiptList.add(new Receipt_Item(item.getStockName(), newReceipt.getReceiptID(), item.getStockID(), item.getStockAvailableQuantity()));
        }

        receipt.insertReceiptTable(newReceipt);
        Receipt_Item.insertReceiptItemsTable(newReceiptList);
        clearStock();
        this.stockOnHandList = Stock_On_Hand.getStockOnHand();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Stock Out Tab
    @FXML
    public void NewStockRequestButtonPushed() throws Exception
    {

        stockOutTables.setVisible(true);
        stockOutCustomersCB.setVisible(true);
        //StockList - Receipts
        stockOnHandOutIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("StockID"));
        stockOnHandNameOutColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("StockName"));
        stockOnHandQuantityOutColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, Integer>("stockAvailableQuantity"));
        stockOnHandCustomerOutColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("customerCompanyName"));
        stockOnHandTransactioDateOutColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, LocalDate>("transactionDate"));
        stockOnHandOutTable.setItems(stockOnHandList);

        ObservableList<Stock_Item> stockItemByCustomer = Stock_Item.getStockItems();
        receiptsSelectTable.setItems(stockItemByCustomer);
    }

    @FXML
    public String stockOutCustomersCBSelection() throws Exception
    {
        String selectedCust;
        String custID;
        selectedCust = stockOutCustomersCB.getValue().toString();
        custID = selectedCust.substring(0, selectedCust.indexOf((" ")));
        stockOnHandList = Stock_On_Hand.getStockOnHand(custID);
        stockOnHandOutTable.setItems(stockOnHandList);
        return custID;
    }

    @FXML
    public void addStockToStockRequestButtonPushed() throws Exception
    {
        Stock_On_Hand selectedStockItem = stockOnHandOutTable.getSelectionModel().getSelectedItem();

        newStockRequestList.add(new Stock_On_Hand(selectedStockItem.getStockID(), selectedStockItem.getCustomerID(), Integer.parseInt(newStockRequestTxt.getText()), selectedStockItem.getStockName(), selectedStockItem.getCustomerCompanyName(), LocalDate.now()));

        newStockRequestIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("stockID"));
        newStockRequestNameColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("stockName"));
        newStockRequestQuantityColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, Integer>("stockAvailableQuantity"));
        newStockRequestCustomerColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("customerCompanyName"));
        newStockRequestTransactioDateColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, LocalDate>("transactionDate"));

        newStockRequestTable.setItems(newStockRequestList);
    }

    @FXML
    public void removeStockFromStockRequestButtonPushed()
    {
        Stock_On_Hand selectedStockItem = newStockRequestTable.getSelectionModel().getSelectedItem();
        newStockRequestList.remove(selectedStockItem);
        newStockRequestTable.setItems(newStockRequestList);
    }

    @FXML
    public void clearStockRequestTable()
    {
        newStockRequestList.clear();
        newReceiptTable.setItems(newStockRequestList);
    }

    @FXML
    public void CompleteNewStockRequestListButtonPushed() throws Exception
    {
        //(String requestID, String customerName, String customerID, LocalDate transactionDate)
        Stock_On_Hand selectedItem = stockOnHandOutTable.getSelectionModel().getSelectedItem();
        newStockRequest = new stock_request(Stock_Item.getKey("REQ"), selectedItem.getCustomerCompanyName(), selectedItem.getCustomerID(), LocalDate.now());

        for (Stock_On_Hand item : newStockRequestList)
        {
            newRequestList.add(new stock_request_item(item.getStockName(), newStockRequest.getRequestID(), item.getStockID(), item.getStockAvailableQuantity()));
        }

        //newStockRequestList
        stock_request.insertStockRequestTable(newStockRequest);
        stock_request_item.insertRequestItemsTable(newRequestList);
        clearStock();
        this.stockOnHandList = Stock_On_Hand.getStockOnHand();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        //Add Customer Names and Fields
        custNamelbl.setVisible(false);
        custCompanylbl.setVisible(false);
        custNametxt.setVisible(false);
        custCompanytxt.setVisible(false);
        addCustBtn.setVisible(false);

        //Customer Table 
        custIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("custCode"));
        custNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("custName"));
        custCompanyColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("custCompany"));
        //Get Data From DB
        custTable.setItems(custlist);
        //Allow Table Editing
        custTable.setEditable(true);

        custNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        custCompanyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Receipts Tab
        receiveNewStockOptions.setVisible(false);
        stockReceiptTables.setVisible(false);
        customersCB.setVisible(false);
        categoryCB.setVisible(false);

        //Populate Combobox
        for (Customer cust : custlist)
        {
            customersCB.getItems().add(cust.getCustCode() + " " + cust.getCustCompany());
            stockOutCustomersCB.getItems().add(cust.getCustCode() + " " + cust.getCustCompany());
        }

        for (String category : Stock_Item.categories)
        {
            categoryCB.getItems().add(category);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Stock On Hand Page
        //Table
        stockOnHandIDColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("StockID"));
        stockOnHandNameColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("StockName"));
        stockOnHandQuantityColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, Integer>("stockAvailableQuantity"));
        stockOnHandCustomerColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, String>("customerCompanyName"));
        stockOnHandTransactioDateColoumn.setCellValueFactory(new PropertyValueFactory<Stock_On_Hand, LocalDate>("transactionDate"));
        stockOnHandTable.setItems(stockOnHandList);

        //Radio Buttons
        searchButtonGroup = new ToggleGroup();
        this.searchByDate.setToggleGroup(searchButtonGroup);
        this.searchByReceipt.setToggleGroup(searchButtonGroup);
        this.searchByCustomer.setToggleGroup(searchButtonGroup);
        this.searchByItem.setToggleGroup(searchButtonGroup);

        datePicker.setVisible(false);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Stock Out Tab
        stockOutCustomersCB.setVisible(false);
        stockOutTables.setVisible(false);
        
        

    }

}
