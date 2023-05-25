import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.text.html.HTML;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


// Program for print data in JSON format.
public class ReadJson {
    public int pokeNUM = 0;

    public String abilityName = "abilities: ";
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.
        ReadJson read= new ReadJson();


        ReadJson readMe = new ReadJson();
        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", new Integer(1704310046));
        file.put("Tution Fees", new Double(65400));


        // To print in JSON format.
        System.out.print(file.get("Tution Fees"));
        read.pull();
        readMe.pull();

    }

    public String getName(){
        return  abilityName;
    }

    public  void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";

        ArrayList<String> characters = new ArrayList<String>();
        characters.add("pikachu");
        characters.add("bulbasaur");
        characters.add("squirtle");
        characters.add("charmander");

        try {

            System.out.println("POKEnum:"+ pokeNUM);

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + characters.get(pokeNUM));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        JSONObject jsonObject = (JSONObject) parser.parse(totlaJson);

        try {
            System.out.println(jsonObject.get("name"));


            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("abilities");
            int n =   msg.size();//(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test =(JSONObject) msg.get(i);
                JSONObject test2 =(JSONObject) test.get("ability");
                abilityName = "\n"+ abilityName+""+(String) test2.get("name")+", ";
                System.out.println(abilityName);

                // System.out.println(person.getInt("key"));
            }
            long name = (long)jsonObject.get("height");
            System.out.println(name);
        }

        catch (Exception e) {
            e.printStackTrace();
        }




    }
}
