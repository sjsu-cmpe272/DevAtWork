package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import service.CampusConnectService;

/**
 * Created by Sheeban
 */

@Controller
public class CampusConnectController {

    @Autowired
    private CampusConnectService campusConnectService;

    @RequestMapping(value = "/authenticateUser/CampusConnect.htm")
    public ModelAndView authenticateUser(@RequestParam("user") String twitterUserName) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/index.jsp");
        User user = campusConnectService.getUserDetails(twitterUserName);
        return modelAndView;
    }

}
