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
    
    @RequestMapping(value = "/viewdemo", method = RequestMethod.GET)
    public String demo(ModelMap map) {
       // map.put("personObject", object);
       // map.addAttribute("greet", "Hi");
        return "pagename";
    }
    
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@RequestParam("username") String u,ModelMap model) {
        model.addAttribute("username", u);
        return "pagename";
    }
    
    
    
}
