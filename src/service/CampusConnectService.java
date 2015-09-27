package service;

import model.User;

import java.util.Map;

/**
 * Created by Sheeban
 */
public interface CampusConnectService {

    public void addUser(User user);

    public boolean getUserDetails(String userName) throws Exception;

    public Map<Integer, Map<String, String>> getNameVsUserNameMap(String userName) throws Exception;

    Boolean sendMessage(String twitterUserName, String newMessage) throws Exception;

    Boolean postATweet(String twitterUserName, String tweetString) throws Exception;

    Boolean updateStatus(String twitterUserName, String newStatus) throws Exception;
}
