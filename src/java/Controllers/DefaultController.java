/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Web Controller
 * @author Edgar
 */

@Controller
public class DefaultController {
    
    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String index(ModelMap map){
        return "index";
    } 
    
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@RequestParam("accountID")String accountID,@RequestParam("password")String password, ModelMap map){
        map.addAttribute("accountID", accountID);
        //TODO check if account exists then ho to main , if false redo       
        
        return "mainPage";
    }
    
    
}
