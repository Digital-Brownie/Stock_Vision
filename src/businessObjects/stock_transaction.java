/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessObjects;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Alex
 */
public class stock_transaction
{
    SimpleStringProperty receiptID, customerName,  customerID;
    LocalDate transactionDate;
}
