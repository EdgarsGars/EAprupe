/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DB.AccountService;
import DB.PatientService;
import Users.*;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 * Web Controller
 *
 * @author Edgar
 */
@Controller
@SessionAttributes("user")
public class DefaultController {

    /**
     * Entry controller redirects to login page if not logged in, 
     * else returns to home page 
     * @param map  model map
     * @param session active session
     * @return redirection to page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "loginPage";
        } else {
            return "redirect:/home";
        }
    }

    /**
     * Redirects to login page
     * @param map model map
     * @return 
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        return "loginPage";
    }

    
    /**
     * Home page controller, sets different home pages for different access level
     * @param map
     * @param session
     * @return 
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Patient) {
            return "p_homePage";
        } else if (session.getAttribute("user") instanceof Doctor) {
            return "d_homePage";
        } else return "redirect:/login";
    }

    /**
     * Logout controller handles session cleaning
     * @param map model map
     * @param session active session
     * @param status session status
     * @return 
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap map, HttpSession session, SessionStatus status) {
        session.removeAttribute("user");
        status.setComplete();
        return "loginPage";
    }
    
    /**
     * Authorization controller
     * @param userID
     * @param password
     * @param map
     * @param session
     * @return 
     */
    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public String autorize(@RequestParam("username") String userID, @RequestParam("password") String password, ModelMap map, HttpSession session) {
        Object user = AccountService.Login(userID, password);
        session.setAttribute("user", user);
        if (user == null) {
            return "loginPage";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/doctorPage", method = RequestMethod.GET)
    public String doctorPage(ModelMap map, HttpSession session) {

        if (session.getAttribute("user") instanceof Doctor) {
            return "doctorPage";
        } else {
            return "loginPage";
        }
    }

    @RequestMapping(value = "/patientSearch", method = RequestMethod.POST)
    public String patientSearch(@RequestParam("userID") String ID, @RequestParam("name") String name,
            @RequestParam("surname")String surname, ModelMap map, HttpSession session){
        
        map.addAttribute("userID", ID);
        map.addAttribute("name",name);
        map.addAttribute("surname",surname);
        if(session.getAttribute("user") instanceof Doctor){
            return "patientSearchPage";            
        }
        
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/patientSearch", method = RequestMethod.GET)
    public String patientSearch(ModelMap map, HttpSession session){
        
        if(session.getAttribute("user") instanceof Doctor){
            return "patientSearchPage";            
        }
        
        return "redirect:/home";
    }
    
    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public String patientPage(@RequestParam("patientID")String petientID, ModelMap map, HttpSession session){
        if(session.getAttribute("user") instanceof Doctor){
            Patient p = PatientService.findPatientByID(petientID);
            if(((Doctor)session.getAttribute("user")).getId().equals(p.getFamilyDoctor())){
                map.addAttribute("patientID", petientID);
                return "d_patientPage";            
            }
        }
        return "redirect:/home";
    }
}
