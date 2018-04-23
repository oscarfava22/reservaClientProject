package Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonManager {

    private JsonObject obj;

    public JsonManager() {
        obj = carregarMemoria();
    }

    /**
     * Carga el objecto json buscando la carpeta json para cargar el archivo
     * @return
     */
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

    /**
     * Obtiene el string de IP a partir del jsonObject
     * @return la ip de la red
     */
    public String getIp(){
        return obj.get("IP").getAsString();
    }

    /**
     * Obtiene el int del puerto a partir del jsonObject
     * @return el puerto de red
     */
    public int getPort(){
        return obj.get("PORT").getAsInt();
    }
}
