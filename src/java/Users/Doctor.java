/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Users;

/**
 * 
 * @author Visi
 */
public class Doctor {
    private final String id;
    private final String name;
    private final String surname;
    private final String telephoneNumber;
    private final String address;
    
    public Doctor(String id,String name,String surname,String address,String telephoneNumber){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getAddress() {
        return address;
    }
   
}
