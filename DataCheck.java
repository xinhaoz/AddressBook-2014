import java.awt.*;
import javax.swing.*;

/**
 * This program checks to see if the user entered valid phone numbers and emails.
 * 
 * @author Xin Hao Zhang
 * @version 1 March 3, 2014
 */

public class DataCheck
{
  
  /**
   * This method checks to see if the phone number was entered correctly, composing of 10 digits, returning a verdict.
   * <p>
   * If 1) Checks to see if the phone number is 12 characters long, and if so, if it is in the proper format.
   * If 2) Checks to see if the phone number is 10 characters long.
   * <p>
   * The try catch block attempts to parse the String into an integer, to check if the string is composed of only numbers.
   * If it isn't, the catch block will catch the exception to prevent a crash.
   * 
   * @param phone (String) The phone number entered.
   * @return (boolean) Returns true if the phone number is valid and false if it isn't.
   * @throws NumberFormatException If an invalid long was detected.
   */
  public static boolean isPhone (String phone)
  {
    if (phone.length() == 12 && ((phone.charAt (3) == ' ' && phone.charAt (7) == ' ') || (phone.charAt (3) == '-' && phone.charAt (7) == '-')))
      phone = phone.substring (0,3) + phone.substring (4,7) + phone.substring (8);
    if (phone.length() == 10)
    {
      try
      {
 Long.parseLong (phone);
 return true;
      }
      catch (NumberFormatException e)
      {
      }
    }
    return false;
  }
  
  /**
   * This method checks to see if the email entered is an email, returning a verdict.
   * <p>
   * if 1) If the character at index x is alphanumeric, a period or an @ symbol, add 1 to the counter.
   * else 1) Break the for loop.
   * if 2) If the email contains only letters and numbers, only one @ symbol, contains at least one '.' after the @, does not start with '@', and does not end with @ or '.', return true.
   * <p>
   * for loop) The for loop goes through each character in the email to see if it meets all requirements of the email format.
   *
   * @param counter (int) How many characters in the email passed the alphanumeric test.
   * @param x (int) Counter used in the for loop to cycle through all characters in the email.
   * @param email (String) The email entered.
   * @return (boolean) Returns true if it is a valid email and false if it is not.
   */
  public static boolean isEmail (String email)
  {
    int counter = 0;
    for (int x = 0; x < email.length(); x++)
    {
      if (Character.isLetterOrDigit (email.charAt(x)) || email.charAt (x) == '.' || email.charAt (x) == '@')
 counter++;
      else
 break;
    }
    if (counter == email.length() && email.contains ("@") && email.indexOf ("@") == email.lastIndexOf ("@") && email.charAt(0)!= '@' && email.charAt(email.length()-1) != '@'  && email.lastIndexOf (".") > email.indexOf ("@") + 1 && email.charAt(email.length()-1) != '.')
      return true;
    return false;
  }
  
}

