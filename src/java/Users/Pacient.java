/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Users;

/**
 * Pacient object 
 * @author Edgar
 */
public class Pacient {
    private final String id;
    private final String name;
    private final String surname;
    private final String number;
    private final String email;
    private final String address;
    private final String familyDoctor;

    public Pacient(String id, String name, String surname, String number, String email, String address, String familyDoctor) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.address = address;
        this.familyDoctor = familyDoctor;
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

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getFamilyDoctor() {
        return familyDoctor;
    }
    
    
    
}
