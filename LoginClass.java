/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationuser;

/**
 *
 * @author junio
 */
import static com.mycompany.registrationuser.Registrationuser.password;
import static com.mycompany.registrationuser.Registrationuser.username;
import java.util.regex.Pattern;


        
        
public class LoginClass {
    public LoginClass() {
    
    }

    public static boolean checkPasswordComplexity(String password) {
        boolean lengthValid = password.length() >= 8;
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&()].*");

        return lengthValid && hasUppercase && hasNumber && hasSpecialChar;
    }
    
   public String registerUser(String username, String password, String phone) {
        if (!isValidUsername(username)) {
            return "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "User has been registered successfully.";
        
        
    }
    
    public static boolean checkCellPhoneNumber(String cellphone) {
        String saCode = "+27";
        
        return cellphone.matches("^\\+27\\d{9}$");
    }
    public String returnLoginStatus(String username, String password){
        boolean ValidUsername = isValidUsername(username);
        boolean validPassword = checkPasswordComplexity(password);
        
    if(ValidUsername == true && validPassword == true){
             return "A successful login";
                     
    }else{
        return "Faild login";
    }
        
    }
    

    public static boolean isValidUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }
      public static boolean authenticate(String loginUser, String loginPass) {
        return loginUser.equals(username) && loginPass.equals(password);
      }
      


        
        
        
    
}
    

