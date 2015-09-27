package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        String userName = httpServletRequest.getParameter("userName");
        Boolean userExists = campusConnectService.getUserDetails(userName);
        if(userExists){
            modelAndView.setViewName("/charts");
            modelAndView.addObject("user", user);
            Map<Integer,Map<String,String>> userDetails = campusConnectService.getNameVsUserNameMap(userName);
            modelAndView.addObject("usersDataMap", userDetails);
        }else {
            modelAndView.addObject("error","Twitter UserName does not exists");
            modelAndView.setViewName("/home");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/postTweet/CampusConnect.htm")
    public ModelAndView postTweet(@ModelAttribute(value = "user") User user,
                                  HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/charts");
        String userName = httpServletRequest.getParameter("userName");
        String tweetMessage = httpServletRequest.getParameter("tweetMessage");
        Boolean isTweetPostedSuccessful = campusConnectService.postATweet(userName, tweetMessage);
        modelAndView.addObject("isTweetPostedSuccessful",isTweetPostedSuccessful);
        Map<Integer,Map<String,String>> userDetails = campusConnectService.getNameVsUserNameMap(userName);
        modelAndView.addObject("usersDataMap", userDetails);
        return modelAndView;
    }

    @RequestMapping(value = "/updateStatus/CampusConnect.htm")
    public ModelAndView updateStatus(@ModelAttribute(value = "user") User user,
                                     HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/charts");
        String userName = httpServletRequest.getParameter("userName");
        String updateStatuString = httpServletRequest.getParameter("updateStatusString");

        Boolean isStatusUpdateSuccessful = campusConnectService.updateStatus(userName, updateStatuString);
        modelAndView.addObject("isStatusUpdateSuccessful",isStatusUpdateSuccessful);
        Map<Integer,Map<String,String>> userDetails = campusConnectService.getNameVsUserNameMap(userName);
        modelAndView.addObject("usersDataMap", userDetails);
        return modelAndView;
    }
}
