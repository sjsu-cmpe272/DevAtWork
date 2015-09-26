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

/**
 * Created by Sheeban
 */

@Component
public class CampusConnectServiceImpl implements CampusConnectService {

    @Autowired
    private CampusConnectDAO campusConnectDAO;

    static String consumerKeyStr = "ZXdvJOpCkpNeLAqoA4Fxr0kIt";
    static String consumerSecretStr = "N2WV3Bvm1sgpTPMyRws4mh2winQRWyuLiC6B1yZiAqsnMmGuYl";
    static String accessTokenStr = "127229156-8MayIQYd3dlgM45f2j0tFD6rhzYpJK4XY3OScO8j";
    static String accessTokenSecretStr = "JL48vNbxH7XOCdefUqM9y9tffhfGXMHDI8RuFLnMTavLK";
    static Integer STATUS_OK = 200;


    @Override
    public void addUser(User user) {
        campusConnectDAO.addUsersData(user);
    }

    @Override
    public boolean getUserDetails(String userName) throws Exception{
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpGet httpGetDetailsForUser =
                new HttpGet("https://api.twitter.com/1.1/users/show.json?screen_name="+userName);
        oAuthConsumer.sign(httpGetDetailsForUser);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGetDetailsForUser);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        System.out.println(mainJsonString);

        JSONObject jsonObjectOfUserDetails = new JSONObject(mainJsonString);
        if(statusCode==STATUS_OK) {
            System.out.println("Followers Count: " + jsonObjectOfUserDetails.get("followers_count") +
                    "\nFriendsCount: " + jsonObjectOfUserDetails.get("friends_count") +
                    "\n Description: " + jsonObjectOfUserDetails.get("description") +
                    "\nLocation: " + jsonObjectOfUserDetails.get("location"));
        }
        return statusCode==STATUS_OK;
    }

    @Override
    public Map<String, String> getNameVsUserNameMap(String userName) throws Exception{
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpGet httpGet = new HttpGet("https://api.twitter.com/1.1/followers/list.json?cursor=-1&screen_name=shaikhsheeban&skip_status=true&include_user_entities=false&count=50");

        oAuthConsumer.sign(httpGet);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        System.out.println(mainJsonString);

        JSONObject jsonObject = new JSONObject(mainJsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("users");
        Map<String,String> followersNameVsScreenName = new HashMap<String,String>();
        for(int i=0; i<10; i++) {
            JSONObject followersString = jsonArray.getJSONObject(i);
            followersNameVsScreenName.put(followersString.getString("name"), followersString.getString("screen_name"));
            String bio = followersString.getString("description");
            if(bio.contains("San Jose State University") || bio.contains("SJSU")){
                System.out.println("Bio: "+bio);
            }
        }
        for(String s: followersNameVsScreenName.keySet()) {
            System.out.println(s +" - "+followersNameVsScreenName.get(s));
        }
        return followersNameVsScreenName;
    }

    @Override
    public void sendMessage(String shaikhsheeban, String twitterUserName) throws Exception{
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpPost httpGetForSendingMessage =
                new HttpPost("https://api.twitter.com/1.1/direct_messages/new.json?text=hello%2C%20tworldaaaa.%20welcome%20to%201.1.&screen_name="+twitterUserName);
        oAuthConsumer.sign(httpGetForSendingMessage);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGetForSendingMessage);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        System.out.println("Message: "+mainJsonString);

    }

    @Override
    public Boolean postATweet(String twitterUserName, String tweetString) throws Exception{
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpPost httpPostATweet =
                new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status="+tweetString);
        oAuthConsumer.sign(httpPostATweet);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpPostATweet);

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        System.out.println(statusCode + ':' + httpResponse.getStatusLine().getReasonPhrase());
        String mainJsonString = IOUtils.toString(httpResponse.getEntity().getContent());
        return statusCode == STATUS_OK;
    }

    @Override
    public Boolean updateStatus(String twitterUserName, String newStatus) throws Exception{

        newStatus = newStatus.replaceAll(" ", "%20");
        twitterUserName = twitterUserName.replaceAll(" ","%20");
        OAuthConsumer oAuthConsumer = getoAuthConsumer();
        HttpPost httpPostStatusUpdate =
            new HttpPost("https://api.twitter.com/1.1/account/update_profile.json?name="+twitterUserName+"&description="+newStatus);
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
        OAuthConsumer oAuthConsumer = new CommonsHttpOAuthConsumer(consumerKeyStr,consumerSecretStr);
        oAuthConsumer.setTokenWithSecret(accessTokenStr, accessTokenSecretStr);
        return oAuthConsumer;
    }

}
