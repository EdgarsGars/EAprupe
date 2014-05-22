/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web Controller
 *
 * @author Edgar
 */
@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        return "/login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        return "loginPage";
    }

    
    @RequestMapping(value = "/autorize", method = RequestMethod.POST)
    public String autorize(@RequestParam("username")String userID,@RequestParam("password")String password,ModelMap map) {
        map.addAttribute("userID", userID);
        
        return "mainPage";
    }

    @RequestMapping(value = "/doctorPage", method = RequestMethod.GET)
    public String doctorPage(ModelMap map) {
        
        
        return "doctorPage";
    }

}
