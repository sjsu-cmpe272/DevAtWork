package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CampusConnectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Sheeban
 */

@Controller
public class CampusConnectController {

    @Autowired
    private CampusConnectService campusConnectService;

    @RequestMapping(value = "/authenticateUser/CampusConnect.htm")
    public ModelAndView authenticateUser(@ModelAttribute(value = "user") User user,
                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                    @RequestParam("user") String twitterUserName) throws Exception {

        ModelAndView modelAndView = new ModelAndView("/charts.jsp");
        Boolean userExists = campusConnectService.getUserDetails("sheebanshaikh");
        modelAndView.addObject("user", user);
        Map<String,String> userDetails = campusConnectService.getNameVsUserNameMap("sheebanshaikh");

        modelAndView.addObject("usersDataMap",userDetails);
        return modelAndView;
    }

}
