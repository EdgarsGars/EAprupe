/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Users;

/**
 *
 * @author User
 */
public class MedicalFacility {
    private final String id;
    private final String name;
    private final String address;
    private final String telephoneNumber;
    
    public MedicalFacility(String id,String name,String address,String telephoneNumber){
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getTelephoneNumber() {
        return telephoneNumber;
    }
}
