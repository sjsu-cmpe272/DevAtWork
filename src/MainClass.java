import dao.CampusConnectDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CampusConnectService;


/**
 * Created by Sheeban
 */
public class MainClass {

    static String consumerKeyStr = "ZXdvJOpCkpNeLAqoA4Fxr0kIt";
    static String consumerSecretStr = "N2WV3Bvm1sgpTPMyRws4mh2winQRWyuLiC6B1yZiAqsnMmGuYl";
    static String accessTokenStr = "127229156-8MayIQYd3dlgM45f2j0tFD6rhzYpJK4XY3OScO8j";
    static String accessTokenSecretStr = "JL48vNbxH7XOCdefUqM9y9tffhfGXMHDI8RuFLnMTavLK";


    public static void main(String[] args) throws Exception {
//        HttpGet httpGet1 = new HttpGet("https://api.twitter.com/1.1/search/tweets.json?q=%23freebandnames&since_id=24012619984051000&max_id=250126199840518145&result_type=mixed&count=4");




        //Sending message via twitter

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CampusConnectService campusConnectService  = applicationContext.getBean(CampusConnectService.class);
        CampusConnectDAO campusConnectDAO = applicationContext.getBean(CampusConnectDAO.class);
        campusConnectService.getUserDetails("miths_ds");
        campusConnectService.getNameVsUserNameMap("shaikhsheeban");
        campusConnectService.sendMessage("New Message", "miths_ds");

        Boolean tweetStatus = campusConnectService.postATweet("shaikhsheeban", "Tweet");
        if(tweetStatus) {
            System.out.println("Tweet Successful");
        }

        Boolean updatedStatus = campusConnectService.updateStatus("Sheeban Raza", "Graduate Student At SJSU");
        if(updatedStatus){
            System.out.println("Status Posted");
        }
    }
}
