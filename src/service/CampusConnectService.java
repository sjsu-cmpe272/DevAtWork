package service;

import model.User;

import java.util.Map;

/**
 * Created by Sheeban
 */
public interface CampusConnectService {

    public void addUser(User user);

    public User getUserDetails(String userName) throws Exception;

    public Map<String, String> getNameVsUserNameMap(String userName) throws Exception;

    void sendMessage(String twitterUserName, String newMessage) throws Exception;

    Boolean postATweet(String twitterUserName, String tweetString) throws Exception;

    Boolean updateStatus(String twitterUserName, String newStatus) throws Exception;
}
