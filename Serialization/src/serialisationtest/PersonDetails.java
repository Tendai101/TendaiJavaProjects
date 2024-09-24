/*  Person's information
*/

package serialisationtest;

import java.io.Serializable;

public class PersonDetails implements Serializable, ContactInfo {

    // instance variables aka fields/properties
    private String name;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String instagram;
    
        
    // no-arguments constructor
    public PersonDetails() {
        setName("");
        setAge(0);
        setGender("other");
        setEmail("");
        setPhone("");
        setInstagram("");
    }

    // constructor
    public PersonDetails(String name, int age, String gender, String email, String phone, String instagram) {
        setName(name);
        setAge(age);
        setGender(gender);
        setEmail(email);
        setPhone(phone);
        setInstagram(instagram);
    }

    // returns person's age
    public int getAge() {
        return age;
    }

    // sets person's age
    public void setAge(int age) {
        this.age = (age > 0 && age < 120) ? age : 0;
    }

    // returns person's name
    public String getName() {
        return name;
    }

    // sets person's name
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
     public String getEmail(){
     return email;
     }
     
    @Override
     public void setEmail(String email) {
          this.email = email;
     }
     
    @Override
     public String getPhone(){
     return phone;
     }
     
    @Override
     public void setPhone(String phone) {
          this.phone = phone;
     }
     
    @Override
     public String getInstagram(){
     return instagram;
     }
     
    @Override
     public void setInstagram(String instagram) {
          this.instagram = instagram;
     }

    // returns perosn's gender
    public String getGender() {
        return gender;
    }

    // sets person's gender
    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("other"))
            this.gender = gender;  
        else 
            this.gender = "other"; 
    }
}