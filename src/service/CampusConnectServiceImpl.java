package service;

import dao.CampusConnectDAO;
import model.User;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Sheeban
 */

@Component
public class CampusConnectServiceImpl implements CampusConnectService {

    @Autowired
    private CampusConnectDAO campusConnectDAO;

    static String consumerKeyStr = "";
    static String consumerSecretStr = "";
    static String accessTokenStr = "-";
    static String accessTokenSecretStr = "";
    static Integer STATUS_OK = 200;


    @Override
    public void addUser(User user) {
        campusConnectDAO.addUsersData(user);
    }

    @Override
    public boolean getUserDetails(String userName) throws Exception {
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpGet httpGetDetailsForUser =
                new HttpGet("https://api.twitter.com/1.1/users/show.json?screen_name=" + userName);
        oAuthConsumer.sign(httpGetDetailsForUser);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGetDetailsForUser);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        System.out.println(mainJsonString);

        JSONObject jsonObjectOfUserDetails = new JSONObject(mainJsonString)
        if (statusCode == STATUS_OK) {
            System.out.println("Followers Count: " + jsonObjectOfUserDetails.get("followers_count") +
                    "\nFriendsCount: " + jsonObjectOfUserDetails.get("friends_count") +
                    "\n Description: " + jsonObjectOfUserDetails.get("description") +
                    "\nLocation: " + jsonObjectOfUserDetails.get("location"));
        }
        return statusCode == STATUS_OK;
    }

    @Override
    public Map<Integer, Map<String, String>> getNameVsUserNameMap(String userName) throws Exception {
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpGet httpGet = new HttpGet("https://api.twitter.com/1.1/followers/list.json?cursor=-1&screen_name=" + userName + "&skip_status=true&include_user_entities=false&count=100");

        oAuthConsumer.sign(httpGet);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        System.out.println(mainJsonString);

        JSONObject jsonObject = new JSONObject(mainJsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        Map<Integer, Map<String, String>> followersCountVsNameAndScreenName = new TreeMap<>();
        for (int i = 0; i < 10; i++) {
            Map<String, String> nameVSScreenName = new HashMap<>();
            JSONObject followersString = jsonArray.getJSONObject(i);

            nameVSScreenName.put(followersString.getString("name"), followersString.getString("screen_name"));
            followersCountVsNameAndScreenName.put((Integer) followersString.get("followers_count"), nameVSScreenName);
        }
        return followersCountVsNameAndScreenName;
    }

    @Override
    public Boolean sendMessage(String twitterUserName, String message) throws Exception {
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        message = message.replaceAll(" ", "%20");
        HttpPost httpGetForSendingMessage =
                new HttpPost("https://api.twitter.com/1.1/direct_messages/new.json?text=" + message);
        oAuthConsumer.sign(httpGetForSendingMessage);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGetForSendingMessage);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        System.out.println("Message: " + mainJsonString);
        return statusCode == STATUS_OK;

    }

    @Override
    public Boolean postATweet(String twitterUserName, String tweetString) throws Exception {
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        tweetString = tweetString.replaceAll(" ", "%20");
        HttpPost httpPostATweet =
                new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=" + tweetString);
        oAuthConsumer.sign(httpPostATweet);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPostATweet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        return statusCode == STATUS_OK;
    }

    @Override
    public Boolean updateStatus(String twitterUserName, String newStatus) throws Exception {

        newStatus = newStatus.replaceAll(" ", "%20");
        twitterUserName = twitterUserName.replaceAll(" ", "%20");
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpPost httpPostStatusUpdate =
                new HttpPost("https://api.twitter.com/1.1/account/update_profile.json?name=" + twitterUserName + "&description=" + newStatus);
        oAuthConsumer.sign(httpPostStatusUpdate);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPostStatusUpdate);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        System.out.println(mainJsonString);
        return statusCode == STATUS_OK;
    }


    private OAuthConsumer getoAuthConsumer() {
        OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr, consumerSecretStr);
        oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
        return oAuthConsumer;
    }

}
