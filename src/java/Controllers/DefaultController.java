/*
 *   
 *  
 */
package Controllers;

import DB.AccountService;
import DB.MedicalRecordService;
import DB.PatientService;
import Users.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
     * Entry controller redirects to login page if not logged in, else returns
     * to home page
     *
     * @param map model map
     * @param session active session
     * @return redirection to page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }
    }

    /**
     * Redirects to login page
     *
     * @param map model map
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) {
        return "loginPage";
    }

    /**
     * Home page controller, sets different home pages for different access
     * level
     *
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
        } else if (session.getAttribute("user") instanceof MedicalFacility) {
            return "f_homePage";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * Logout controller handles session cleaning
     *
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
     *
     * @param userID
     * @param password
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public String autorize(@RequestParam("username") String userID, @RequestParam("password") String password,
            ModelMap map, HttpSession session) {
        Object user = AccountService.Login(userID, password);
        session.setAttribute("user", user);
        if (user == null) {
            return "loginPage";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/patientSearch", method = RequestMethod.POST)
    public String patientSearch(@RequestParam("userID") String ID, @RequestParam("name") String name,
            @RequestParam("surname") String surname, ModelMap map, HttpSession session) {
        map.addAttribute("userID", ID);
        map.addAttribute("name", name);
        map.addAttribute("surname", surname);
        if (session.getAttribute("user") instanceof Doctor) {
            return "patientSearchPage";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/patientSearch", method = RequestMethod.GET)
    public String patientSearch(ModelMap map, HttpSession session) {

        if (session.getAttribute("user") instanceof Doctor) {
            return "patientSearchPage";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public String patientPage(@RequestParam("patientID") String petientID, ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Doctor) {
            Patient p = PatientService.findPatientByID(petientID);
            if (((Doctor) session.getAttribute("user")).getId().equals(p.getFamilyDoctor())) {
                map.addAttribute("patientID", petientID);
                return "d_patientPage";
            }
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/addPatient")
    public String addPatientPage(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Doctor) {
            return "d_addPatient";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/contact")
    public String pacientDoctorContact(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Patient) {
            String doctorID = ((Patient) session.getAttribute("user")).getFamilyDoctor();
            map.addAttribute("doctorID", doctorID);
            return "p_contact";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/medicalRecord")
    public String medRecord(@RequestParam("ID") String id, ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Doctor) {
            map.addAttribute("medID", id);
            return "d_medicalRecord";
        } else if (session.getAttribute("user") instanceof Patient) {
            map.addAttribute("medID", id);
            return "p_medicalRecord";
        } else if (session.getAttribute("user") instanceof MedicalFacility) {
            map.addAttribute("medID", id);
            return "f_medicalRecord";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/docAddPatient", method = RequestMethod.POST)
    public String addPatient(@RequestParam("userID") String pID, @RequestParam("name") String pName, @RequestParam("surname") String pSur,
            @RequestParam("address") String address, @RequestParam("number") String number, @RequestParam("email") String email, @RequestParam("password1") String pass1,
            @RequestParam("password2") String pass2, ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Doctor) {
            if (pass1.equals(pass2)) {
                if (PatientService.findPatientByID(pID) == null) {
                    String doctorID = ((Doctor) session.getAttribute("user")).getId();
                    AccountService.addAccount(pID, pass1, 1);
                    PatientService.addPatient(pID, pName, pSur, address, number, email, doctorID);
                }
            }
            return "redirect:/patient?patientID=" + pID;
        }

        return "redirect:/home";
    }

    @RequestMapping(value = "/updateMed", method = RequestMethod.POST)
    public String addComment(@RequestParam("comment") String comment, @RequestParam("ID") String id, ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Doctor) {
            MedicalRecord r = MedicalRecordService.findMedicalRecordByID(id);
            MedicalRecordService.updateComment(id, comment);
        } else if (session.getAttribute("user") instanceof MedicalFacility) {
            MedicalRecord r = MedicalRecordService.findMedicalRecordByID(id);
            MedicalRecordService.updateDesc(id, comment);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public String settings(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof Doctor) {
            String userID = ((Doctor) session.getAttribute("user")).getId();
            map.addAttribute("doctorID", userID);
            return "d_settings";
        } else if (session.getAttribute("user") instanceof Patient) {
            String userID = ((Patient) session.getAttribute("user")).getId();
            map.addAttribute("patientID", userID);
            return "p_settings";
        } else if (session.getAttribute("user") instanceof MedicalFacility) {
            String userID = ((MedicalFacility) session.getAttribute("user")).getId();
            map.addAttribute("facilityID", userID);
            return "f_settings";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/medicalRecords", method = RequestMethod.GET)
    public String f_medRecords(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof MedicalFacility) {
            String userID = ((MedicalFacility) session.getAttribute("user")).getId();
            map.addAttribute("facilityID", userID);
            return "f_medicalRecords";
        }
        return "redirect:/home";
    }

    //Record search from facility
    @RequestMapping(value = "/medicalRecords", method = RequestMethod.POST)
    public String f_medRecordsSearch(@RequestParam("patientID") String patientID, @RequestParam("dd") String day,
            @RequestParam("mm") String month, @RequestParam("yyyy") String year,
            ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof MedicalFacility) {
            String userID = ((MedicalFacility) session.getAttribute("user")).getId();
            map.addAttribute("facilityID", userID);
            map.addAttribute("patientID", patientID);
            map.addAttribute("dd", day);
            map.addAttribute("mm", month);
            map.addAttribute("yyyy", year);
            return "f_medicalRecords";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.GET)
    public String f_addRecord(ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof MedicalFacility) {
            return "f_addRecord";
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/addRecord", method = RequestMethod.POST)
    public String f_addNewRecord(@RequestParam("userID") String patientID, @RequestParam("file") String file,
            @RequestParam("desc") String desc, ModelMap map, HttpSession session) {
        if (session.getAttribute("user") instanceof MedicalFacility) {
            if (PatientService.findPatientByID(patientID) != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                MedicalRecordService.addMedicalRecords(patientID, ((MedicalFacility) session.getAttribute("user")).getId(), PatientService.findPatientByID(patientID).getFamilyDoctor(), file, desc, dateFormat.format(date), "");
            }
        }
        return "redirect:/home";
    }

}
