/**
 * This program stores data about a person's full name, phone and email and can access and change the data.
 * 
 * @author Xin Hao Zhang with code segments from Janice Dyke
 * @version 1 March 3, 2014
 */


public class PersonRecord
{
  /**
   * The inputted first name.
   */
  private String first = "";
  
  /**
   * The inputted email.
   */
  private String email = "";
  
  /**
   * The inputted last name.
   */
  private String last = "";
  
  /**
   * The inputted phone number.
   */
  private String phone = "";
  
  /**
   * The total number of records that have been submitted.
   */
  static int totalRecords;
  
  /**
   * This constructor allows a PersonRecord to be created with data about the person's name, phone and email to be sent in to the class.
   * 
   * @param first (String) The person's first name.
   * @param last (String) The person's last name.
   * @param phone (String) The person's phone number.
   * @param email (String) The person's email.
   */
  public PersonRecord (String first, String last, String phone, String email)
  { 
    this.first = first; 
    this.email = email; 
    this.last = last; 
    this.phone = phone; 
  } 
  
  /**
   * This constructor allows a PersonRecord to be created with data about the person's full name to be sent in.
   * 
   * @param first (String) The person's first name.
   * @param last (String) The person's last name.
   */
  public PersonRecord (String first, String last)
  {
    this.first = first;
    this.last = last;
  }
  
  /**
   * This constructor allows a PersonRecord to be created with data about the person's full name and email to be sent in.
   * 
   * @param first (String) The person's first name.
   * @param last (String) The person's last name.
   * @param email (String) The person's email.
   */
  public PersonRecord (String first, String last, String email)
  {
    this.first = first;
    this.last = last;
    this.email = email;
  }
  
  /**
   * This constructor allows a PersonRecord to be created with no information. It increases the number of records created.
   */
  public PersonRecord ()
  {
    totalRecords++;
  }
  
  /**
   * This constructor allows a PersonRecord to be created with data about the person's first name to be sent in.
   * 
   * @param first The person's first name.
   */
  public PersonRecord (String first)
  {
    this.first = first;
  }
  
  /**
   * Allows the first name to be changed.
   * 
   * @param newName The first name to be changed to.
   */
  public void setFirst (String newName) 
  { 
    first = formatName(newName); 
  }
  
  /**
   * Allows the last name to be changed.
   * 
   * @param newLast The last name to be changed to.
   */
  public void setLast (String newLast)
  {
    last = formatName(newLast);
  }
  
  /**
   * Allows the phone number to be changed.
   * 
   * @param newPhone The phone number to be changed to.
   */
  public void setPhone (String newPhone)
  {
    phone = formatPhone(newPhone);
  }
  
  /**
   * Allows the email to be changed.
   * 
   * @param newEmail The email to be changed to.
   */
  public void setEmail (String newEmail)
  {
    email = newEmail;
  }
  
  /**
   * Returns the person's last name.
   * 
   * @return (String) Returns person's last name.
   */
  public String getLastName ()
  { 
    return last; 
  }
  
  /**
   * Returns the person's first name.
   * 
   * @return (String) Returns person's first name.
   */
  public String getFirstName ()
  { 
    return first; 
  }
  
  /**
   * Returns the person's phone number.
   * 
   * @return (String) Returns person's phone number.
   */
  public String getPhone ()
  { 
    return phone; 
  }
  
  /**
   * Returns the person's email.
   * 
   * @return (String) Returns person's email.
   */
  public String getEmail ()
  { 
    return email; 
  }
  
  /**
   * Returns the person's correctly formatted name.
   * 
   * @param name (String) The person's unformatted name.
   * @return (String) Returns person's formatted name.
   */
  private String formatName (String name)
  {
    name = name.substring(0,1).toUpperCase() + name.substring (1).toLowerCase();
    return name;
  }
  
  /**
   * Returns the person's correctly formatted phone number.
   * 
   * @param name (String) The person's unformatted phone number.
   * @return (String) Returns person's formatted phone number.
   */
  private String formatPhone (String phone)
  {
    if (phone.length() == 12)
      phone = phone.substring (0,3) + phone.substring (4,7) + phone.substring (8);
    phone = phone.substring (0,3) +"-" + phone.substring(3,6) + "-" + phone.substring (6);
    return phone;
  }
}