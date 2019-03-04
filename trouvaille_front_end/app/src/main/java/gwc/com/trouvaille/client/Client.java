package gwc.com.trouvaille.client;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Client implements Runnable{
    @Override
    public void run(){
        try{
            URL endpoint = new URL("https://trouvaille-rails.herokuapp.com/events.json");
            HttpsURLConnection connection = (HttpsURLConnection) endpoint.openConnection();
            if(connection.getResponseCode()==200){
                InputStream responseBody = connection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                System.out.println(responseBodyReader);
                JsonReader reader = new JsonReader(responseBodyReader);
                System.out.println(reader.toString());
            }
            else{
                System.out.println("Error while connecting");
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
