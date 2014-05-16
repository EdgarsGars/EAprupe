/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import org.springframework.security.access.annotation.Secured;
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
        return "loginPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ModelMap map) {
        return "loginPage";
    }

    @RequestMapping(value = "/loginError", method = RequestMethod.GET)
    public String loginError(ModelMap map) {

        return "loginPage";
    }

    @RequestMapping(value = "/autorize", method = RequestMethod.POST)
    public String autorize(ModelMap map) {

        return "doctorPage";
    }

    @Secured("DOCTOR")
    @RequestMapping(value = "/doctorPage", method = RequestMethod.POST)
    public String doctorPage(ModelMap map) {
        
        
        return "doctorPage";
    }

}
