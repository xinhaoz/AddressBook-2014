import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.filechooser.*;

/**This class allows the user to save, open, and create files and creates menu bar with these options. It calls the AddressBook class.
  * 
  * @author Xin Hao Zhang
  * @version 1 March 3, 2014
  */

public class DataBaseApp extends JFrame implements ActionListener
{
  /**
   * (reference) Creates an instance of and points to the AddressBook class.
   */
  AddressBook a = new AddressBook ();
  
  /**
   * (reference) The file to be specified by the user.
   */
  File fileName;
  
  /**
   * Class constructor that calls the parent constructor, creates an instance of AddressBook, adds the panel and
   * creates and adds menu items, a menu and the menubar. It also sets the window size.
   * 
   * @param a (reference) Points to the AddressBook class so an AddressBook object can be created and accessed.
   * @param quitItem (reference) Variable used to create and allow access to the menu item Quit.
   * @param newItem (reference) Variable used to create and allow access to the menu item New.
   * @param openItem (reference) Variable used to create and allow access to the menu item Open.
   * @param saveAsItem (reference) Variable used to create and allow access to the menu item Save As.
   * @param saveItem (reference) Variable used to create and allow access to the menu item Save.
   * @param fileMenu (reference) Variable used to create and allow access tothe File menu.
   * @param myMenus (reference) Variable used to create and allow access to the menu bar.
   * @param DO_NOTHING_ON_CLOSE (final integer) Does nothing when user presses the close button.
   */
  public DataBaseApp()
  {
    super ("Data Base");
    add (a);
    JMenuItem quitItem = new JMenuItem ("Quit"), newItem = new JMenuItem ("New"), openItem = new JMenuItem ("Open"), 
      saveAsItem = new JMenuItem ("Save As"), saveItem = new JMenuItem ("Save");
    JMenu fileMenu = new JMenu ("File");
    fileMenu.add (newItem);
    fileMenu.add (openItem);
    fileMenu.add (saveAsItem);
    fileMenu.add (saveItem);
    fileMenu.add (quitItem);
    JMenuBar myMenus = new JMenuBar ();
    myMenus.add (fileMenu);
    setJMenuBar (myMenus);
    
    quitItem.addActionListener (this);
    newItem.addActionListener (this);
    openItem.addActionListener (this);
    saveAsItem.addActionListener (this);
    saveItem.addActionListener (this);
    
    
    setSize (550, 500);
    setVisible (true);
    setResizable (false);
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
  }
  
  /**
   * This method handles saving and opening files, and the JFileChooser.
   * while loop: Loops the showing of the file chooser dialog until a file is either successfully saved/opened or the user cancels.
   * <p>
   * if 1) The user chose open, save as, or save on a data base, show the file chooser dialog window.
   * if 1a) The user chose cancel, exit the window.
   * if 1b) The file doesn't end with the extension .xin, so add it.
   * if 2) The user chose save as, but a file with that name already exists. Ask the user if they want to overwrite.
   * if 3) If the file has a blank name, is over 255 characters, or contains an illegal character, display an error message.
   * else if 3a) The user chose to open a file that does not exist. Display an error message.
   * else if 3b) The user chose to save or overwrite a file. Write the records to fileName.
   * else) if 3c) The user chose to open a file. Read the records from that file and display them.
   * <p>
   * for loop 1) Start: 0 Stop: PersonRecord.totalRecords This loop goes through elements in the persons array in order to
   * print its contents to the file.
   * for loop 2) Start: 0 Stop: PersonRecord.totalRecords This loop goes through 4 lines of the file per loop in order to
   * save use them to create a new record in the persons array.
   * for loop 3) Start: PersonRecord.totalRecords Stop: 50 Clears the remaining unused elements in persons by setting the elements to null.
   * <p>
   * try 1) Try to write to fileName.
   * catch 1) Catch any file output errors and display an error message.
   * try 2) Try to read from fileName and parse a string into an integer (the total number of records).
   * catch 2a) Catch number format errors.
   * catch 2b) Catch any file input errors.
   * 
   * @param dialog (String) The type of action the method is performing and the word to display on the accept button on the file chooser dialog window.
   * @param answer (int) The integer returned by the Overwrite? JOptionPane dialog.
   * @param result (int) The integer returned by the file chooser dialog window.
   * @param filter (reference) References the FileNameExtensionFilter class, it represents the type of files to allow.
   * @param fileChooser (reference) References the JFileChooser class, allows users to view files in directories.
   * @param x (int) The variable used as ac ounter in for loops to cycle through elements of an array.
   * @param input (reference) Allows the BufferedReader class to be accessed, allowing files to be read.
   * @param output (reference) Allows the PrintWriter class to be accessed, allowing files to be written.
   * @param CANCEL_OPTION (final int) The integer returned by pressing cancel on the file chooser dialog window.
   * @param YES_NO_OPTION (final int) The type of options the confirm dialog window should display.
   * @param NO_OPTION (final int) The integer returned by pressing no on the confirm dialog window.
   * @param ERROR_MESSAGE (final int) The type of message dialog window.
   * @param tempFile (reference) Has the previous file in case the user enters a file name, but an error occurs and the user chooses to cancel.
   * @throws IOException If a problem was encountered during the reading or writing of a file.
   * @throws NumberFormatException If the second line of the file could not be parsed into an integer.
   */
  public void file (String dialog)
  {
    File tempFile = fileName;
    int answer = -2, result = 0;
    FileNameExtensionFilter filter = new FileNameExtensionFilter ("Record Program Files", "xin");
    JFileChooser fileChooser = new JFileChooser (fileName);
    
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setFileSelectionMode (JFileChooser.FILES_ONLY);
    fileChooser.setFileFilter (filter);
    while (true)
    {
      answer = -2;
      if (dialog.equals ("Open") || dialog.equals ("Save As") || (dialog.equals ("Save") && fileName == null))
      {
        result = fileChooser.showDialog (this, dialog);
        if (result == fileChooser.CANCEL_OPTION)
          break;
        fileName = fileChooser.getSelectedFile ();
        if (!fileName.getName().endsWith(".xin"))
        {
          fileName = new File (fileName + ".xin");
        }
      }
      if (fileName.exists() && dialog.equals ("Save As"))
      {
        answer = JOptionPane.showConfirmDialog (this, "File exists. Overwrite?", "File Exists", JOptionPane.YES_NO_OPTION);
      }
      if (fileName.getName().trim().equals(".xin") || fileName.getName ().length () > 255 || fileName.getName().contains ("|")
            ||fileName.getName().contains("?") || fileName.getName().contains("<") || fileName.getName().contains(">") ||
          fileName.getName().contains("*") || fileName.getName().contains("/") || fileName.getName().contains("\\") ||
          fileName.getName().contains("\""))
      {
        JOptionPane.showMessageDialog (this, "Invalid File Name", "Invalid File Name", JOptionPane.ERROR_MESSAGE);
      }
      else if (!fileName.exists() && dialog.equals ("Open"))
      {
        JOptionPane.showMessageDialog (this, "This file doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
      }
      else if ((dialog.equals("Save") || dialog.equals ("Save As")) && answer != JOptionPane.NO_OPTION && answer != -1)
      {
        PrintWriter output;
        try
        {
          output = new PrintWriter (new FileWriter (fileName));
          output.println ("Phone book by Xin Hao");
          output.println (PersonRecord.totalRecords);
          for (int x = 0; x < PersonRecord.totalRecords; x++)
          {
            output.println (a.persons [x].getFirstName ());
            output.println (a.persons [x].getLastName());
            output.println (a.persons[x].getPhone());
            output.println (a.persons[x].getEmail());
          }
          output.close();
          a.changed = false;
          break;
        }
        catch (IOException e)
        {
          JOptionPane.showMessageDialog (this, "A problem was encountered while saving.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
      else
      {
        if (dialog.equals ("Open"))
        {
          BufferedReader input;
          try
          {
            input = new BufferedReader (new FileReader (fileName));
            if (input.readLine().equals ("Phone book by Xin Hao"))
            {
              PersonRecord.totalRecords = Integer.parseInt (input.readLine());
              for (int x = 0; x< PersonRecord.totalRecords; x++)
              {
                a.persons [x] = new PersonRecord (input.readLine(), input.readLine(), input.readLine(), input.readLine());
              }
              for (int x = PersonRecord.totalRecords; x <= 49; x++)
              {
                a.persons[x] = null;
              }
              a.currentRecord = 0;
              a.recordField.setText ("1/" + PersonRecord.totalRecords);
              a.displayData();
              break;
            }
          }
          catch (NumberFormatException e)
          {
          }
          catch (IOException e)
          {
          }
          JOptionPane.showMessageDialog (this, "A problem was encountered while opening.", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
      fileName = tempFile;
    }
  }
  
  /**
   * This method creates a new data base.
   * The for loop sets each element of the PersonRecord array in AddressBook to null.
   * 
   * @param x (int) Counter used to cycle through elements in the array.
   */
  public void newDataBase ()
  {
    fileName = null;
    for (int x = 0; x < 50; x++)
      a.persons [x] = null;
    a.currentRecord = 0;
    PersonRecord.totalRecords = 0;
    a.firstNameField.setText ("");
    a.lastNameField.setText("");
    a.phoneField.setText ("");
    a.emailField.setText("");
    a.recordField.setText ("No records yet.");
  }
  
  
  /**
   * Determines what to do based on the menu item selected by the user.
   * <p>
   * If 1) The user selected quit, open, or new, but has unsaved changes. A dialog box will open and ask them if they'd like to save.
   * <p>
   * if 1a) The user chose yes, so the file method will be called, starting the save function and then continuing to the original chosen function.
   * else if 1b) The user chose no, so change changed to false and continue with normal operations.
   * else 1c) The user canceled, exit the method.
   * <p>
   * if 2) The user chose Quit, so exit the program.
   * else if 2a) The user chose new, so call the method newDataBase
   * else if 2b) The user chose open, so call the file method and send "Open" in.
   * else if 2c) The user chose save as, and is allowed to save. Call the file method and send "Save As" in.
   * else) if 2d) The user chose save and is allowed to save, so call the file method and send "Save" in.
   * 
   * @param ae (reference) The action that occured.
   * @param answer (integer) The value returned by the JOptionPane dialog.
   * @param YES_NO_OPTION (final int) The type of options the confirm dialog window should display.
   * @param NO_OPTION (final int) The integer returned by pressing no on the confirm dialog window.
   * @param YES_OPTION (final int) The integer returned by pressing yes on the confirm dialog window.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if ((ae.getActionCommand().equals ("Quit") || ae.getActionCommand().equals ("Open") || ae.getActionCommand().equals ("New")) && a.changed == true)
    {
      int answer = JOptionPane.showConfirmDialog (this, "You have unsaved changes. Save before continuing?", "Hold it!", JOptionPane.YES_NO_CANCEL_OPTION);
      if (answer == JOptionPane.YES_OPTION)
        file ("Save");
      else if (answer == JOptionPane.NO_OPTION)
      {
        a.changed = false;
      }
      else
      {
        return;
      }
    }
    if (ae.getActionCommand().equals("Quit"))
    {
      System.exit (0);
    }
    else if (ae.getActionCommand().equals("New"))
    {
      newDataBase();
    }
    else if (ae.getActionCommand().equals ("Open"))
    {
      file("Open");
    }
    else if (ae.getActionCommand().equals ("Save As") && a.persons[0] != null)
      file ("Save As");
    else
    {
      if (ae.getActionCommand().equals ("Save") && a.persons[0] != null)
        file ("Save");
    }
  }
  
  
  /**
   * The main method creates an instance of this class and controls program flow.
   * 
   * @param String[]args     (String array) allows String type command line arguments.
   */
  public static void main (String [] args)
  {
    new DataBaseApp ();
  }
}