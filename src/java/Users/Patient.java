/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Users;

/**
 * Pacient object 
 * @author 
 */
public class Patient {
    private final String id;
    private final String name;
    private final String surname;
    private final String number;
    private final String email;
    private final String address;
    private final String familyDoctor;

    
    /**
     * Creates patient object
     * @param id patient id 111111-11111
     * @param name patients name
     * @param surname patients surname
     * @param number patients phone number
     * @param email patients e-mail
     * @param address patients home address
     * @param familyDoctor patients family doctor ID
     */
    public Patient(String id, String name, String surname, String number, String email, String address, String familyDoctor) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.address = address;
        this.familyDoctor = familyDoctor;
    }
    
    /**
     * @return patient ID
     */
    public String getId() {
        return id;
    }

    /** 
     * @return patient name
     */
    public String getName() {
        return name;
    }

    /**
     * @return patient surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return patient phone number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return patient e-mail address
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return patient home address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return family Doctors ID
     */
    public String getFamilyDoctor() {
        return familyDoctor;
    }
    
    
    
}
