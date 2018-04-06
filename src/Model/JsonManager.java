package Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonManager {

    private int json;
    private JsonObject obj;

    public JsonManager() {
        obj = carregarMemoria();
    }

    //carregarMemoria
    private static JsonObject carregarMemoria(){

        JsonParser parser = new JsonParser(); // tipus parser per obtenir el format desitjat

        try{
            FileReader fr = new FileReader("data/config.json"); //cargo el fitxero amb la classe fileReader, busco en el relative path
            JsonElement datos = parser.parse(fr); //per a passar a format json de tipus element usant el "parse"
            return datos.getAsJsonObject();

        }catch(FileNotFoundException e){ //salta el bloc de codi si hi ha un error
            System.err.println(e.getMessage());
        }
        return null;
    }

    public String getIp(){
        return obj.get("IP").getAsString();
    }

    public int getPort(){
        return obj.get("PORT").getAsInt();
    }
}
