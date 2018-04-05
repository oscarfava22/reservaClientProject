package Model;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonManager {

    private int json;
    private JSONObject obj;

    public JsonManager() {
       /*
        try {

            Object obj = parser.parse(new FileReader("c:\\file.json"));

            JSONObject jsonObject =  (JSONObject) obj;
        }catch (FileNotFoundException e){
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        */
    }

    public String getIp(){
        return obj.getString("IP");
    }

    public int getPort(){
        return obj.getInt("PORT");
    }
}
