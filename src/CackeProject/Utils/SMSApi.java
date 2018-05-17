package CackeProject.Utils;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;


import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SMSApi {
    static String myPasscode = "93874391";
    static String myUsername = "catalyst";


    public static void sendSms(String toPhoneNumber,String myMessage)  {
        try {
            HttpClient client =  HttpClients.createDefault();
            HttpGet request = new HttpGet("http://cloud.fowiz.com/api/message_http_api.php?username=" + myUsername + "&phonenumber=" + "00216"+toPhoneNumber
                    + "&message=" + myMessage.replace(" ","%20") + "&passcode=" + myPasscode);
            HttpResponse response = client.execute(request);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            StringBuffer response2 = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response2.append(line);
            }


            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
