import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This program allows users to store up to 50 records with information on a person's full name, phone and email and view them.
 * 
 * @author Xin Hao Zhang
 * @version 1 March 3, 2014
 */

public class AddressBook extends JPanel implements ActionListener
{
  /**
   *(boolean) Whether or not the user has made a change. 
   */
  boolean changed;
  
  /**
   * (Final integer) The maximum number of records that can be stored.
   */
  final int MAX_RECORD = 50;
  
  /**
   * (static integer) Represents the record the user is on/viewing.
   */
  static int currentRecord;
  
  /**
   * (object array) An array of records that are each capable of storing a person's first and last name, phone and email, with maximum 50.
   */
  PersonRecord persons [] = new PersonRecord [MAX_RECORD];
  
  /**
   * (reference) The field where the first name is inputted.
   */
  JTextField firstNameField = new JTextField (15);
  
  /**
   * (reference) The field where the last name is inputted.
   */
  JTextField lastNameField = new JTextField (15);
  
  /**
   * (reference) The field where the phone number is inputted.
   */
  JTextField phoneField = new JTextField (10);
  
  /**
   * (reference) The field where current and total records is displayed.
   */
  JTextField recordField = new JTextField ("No records yet.");
  
  /**
   * (reference) The field where the email is inputted.
   */
  JTextField emailField = new JTextField (20);
  
  /**
   * Class constructor calls the askData method to add all components on the screen when the object is created.
   */
  public AddressBook ()
  {
    askData();
  }
  
  /**
   * This method sets the layout into a grid and creates JLabels, and adds these labels as well as text fields to the panel.
   * 
   * @param firstNameLabel (reference) References the JLabel class and allows the label for a first name to be created.
   * @param lastNameLabel (reference) References the JLabel class and allows the label for a last name to be created.
   * @param phoneLabel (reference) References the JLabel class and allows the label for a phone number to be created.
   * @param emailLabel (reference) References the JLabel class and allows the label for an email to be created.
   * @param recordLabel (reference) References the JLabel class and allows the label for the current and total record number to be created.
   * @param submit (reference) References the JButton class and allows a button for a submitting information to be created.
   * @param newRecord (reference) References the JButton class and allows a button for creating a new record to be created.
   * @param back (reference) References the JButton class and allows a button for a going to the previous record to be created.
   * @param next (reference) References the JButton class and allows a button for a going to the next record to be created.
   * @param g (reference) References the GridLayout layout manager so the layout may be set to GridLayout.
   */
  public void askData()
  {
    JLabel firstNameLabel = new JLabel ("First Name:"), lastNameLabel = new JLabel ("Last name:"), phoneLabel = new JLabel ("Phone Number:"),
      emailLabel = new JLabel ("Email:"), recordLabel = new JLabel ("Record Number:");
    JButton submit = new JButton ("Submit"), newRecord = new JButton ("New Record"), back = new JButton ("Back"), next = new JButton ("Next");
    GridLayout g = new GridLayout(7, 2);
    setLayout (g);
    
    this.add (firstNameLabel);
    this.add (firstNameField);
    this.add (lastNameLabel);
    this.add (lastNameField);
    this.add (phoneLabel);
    this.add (phoneField);
    this.add (emailLabel);
    this.add (emailField);
    this.add (submit);
    this.add (newRecord);
    this.add (back);
    this.add (next);
    this.add (recordLabel);
    this.add (recordField);
    
    submit.addActionListener (this);
    newRecord.addActionListener (this);
    back.addActionListener (this);
    next.addActionListener (this);
  }
  
  /**
   * Displays the data from PersonRecord in the appropriate text fields.
   */
  public void displayData ()
  {
    firstNameField.setText (persons [currentRecord].getFirstName ());
    lastNameField.setText (persons [currentRecord].getLastName ());
    phoneField.setText (persons [currentRecord].getPhone ());
    emailField.setText (persons [currentRecord].getEmail ());
  }
  
  /**
   * Stores data from each field into the current element of the persons array.
   * <p>
   * if 1) If currentRecord is larger than PersonRecord.totalRecords, create a new PersonRecord at the current element in the persons array.
   * if 2) If the first name field is not blank, store the first name.
   * if 3) If the last name field is not blank, store the last name.
   * if 4) If the phone field is not empty and is valid, store the phone number.
   * else) if 4a) The phone field is not empty but is invalid so display an error message and clear the field.
   * if 5) If the email field is not empty and is valid, store the email field.
   * else) if 5a) If the email field is not empty but is invalid display an error message and clear the field.
   * 
   * @param ERROR_MESSAGE (final int) The type of message dialog window.
   */
  public void storeData ()
  {
    if (persons[currentRecord] ==null)
    {
      persons [currentRecord] = new PersonRecord ();
    }
    if (!firstNameField.getText().trim().equals(""))
    {
      persons [currentRecord].setFirst(firstNameField.getText());
    }
    if (!lastNameField.getText().trim().equals(""))
    {
      persons [currentRecord].setLast (lastNameField.getText());
    }
    if (!phoneField.getText().trim().equals("") && DataCheck.isPhone(phoneField.getText()))
    {
      persons [currentRecord].setPhone (phoneField.getText());
    }
    else
    {
      if (!phoneField.getText().trim().equals("") && !DataCheck.isPhone(phoneField.getText()))
      {
        JOptionPane.showMessageDialog (this, "Phone number was invalid and was not stored.", "Input Error", JOptionPane.ERROR_MESSAGE);
        phoneField.setText("");
      }
    }
    if (!emailField.getText().trim().equals("") && DataCheck.isEmail(emailField.getText()))
    {
      persons [currentRecord].setEmail (emailField.getText());
    }
    else
    {
      if (!emailField.getText().trim().equals("") && !DataCheck.isEmail(emailField.getText()))
      {
        JOptionPane.showMessageDialog (this, "Email was invalid and was not stored.", "Input Error", JOptionPane.ERROR_MESSAGE);
        emailField.setText ("");
      }
    }
    changed = true;
  }
  
  /** This method determines what to do based on the button that was pressed.
    * If 1) The user clicked submit with at least one field entered. Call the method storeData()
    * else if 1a) The user clicked new record so set all fields to blank and go to the next new record.
    * else if 1b) The user clicked next so display the record with displayData()
    * if 1b a) The user is on the highest record, and will go back to the start.
    * else 1b b) Go to the nect record by increasing currentRecord.
    * else) if 1c) The user clicked back so display the appropriate record.
    * if 1c a) The user is on the first record, so go to the highest record.
    * else 1c b) Go to the last record by decreasing currentRecord.
    * 
    * @param ae (reference) The action that occured.
    */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand().equals ("Submit") && (!firstNameField.getText().trim().equals("") || !lastNameField.getText().trim().equals("") || 
                                                                           DataCheck.isPhone(phoneField.getText()) || DataCheck.isEmail(emailField.getText())))
    {
      storeData();
    }
    else if (ae.getActionCommand().equals ("New Record") && PersonRecord.totalRecords < 50 && currentRecord < PersonRecord.totalRecords && persons[currentRecord] != null)
    {
      firstNameField.setText("");
      lastNameField.setText("");
      phoneField.setText("");
      emailField.setText("");
      currentRecord = PersonRecord.totalRecords;
    }
    else if (ae.getActionCommand().equals ("Next") && persons [0] != null)
    {
      if (currentRecord == PersonRecord.totalRecords || currentRecord == PersonRecord.totalRecords - 1)
        currentRecord = 0;
      else
        currentRecord++;
      displayData();
    }
    else
    {
      if (ae.getActionCommand().equals ("Back") && persons [0] != null)
      {
        if (currentRecord == 0)
          currentRecord = PersonRecord.totalRecords-1;
        else
          currentRecord--;
        displayData();
      }
    }
    recordField.setText ((currentRecord + 1) +"/" + (PersonRecord.totalRecords));
  }
}