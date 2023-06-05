import org.json.simple.JSONArray;
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


public class ReadJson {
    public int playerNUM = 0;

    public String dataName = "data: " + "\n";

    public static void main(String args[]) throws ParseException {
        ReadJson read = new ReadJson();


        ReadJson readMe = new ReadJson();
        JSONObject file = new JSONObject();

        read.pull();
        readMe.pull();

    }

    public String getName() {
        return dataName;

    }

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";

        ArrayList<String> players = new ArrayList<String>();
        players.add("Ike");
        players.add("Ron");
        players.add("Jabari");
        players.add("MarShon");
        players.add("Lorenzo");
        players.add("Omri");
        players.add("Alex");
        players.add("Tyler");
        players.add("Keenan");
        players.add("Marcin");
        players.add("Andrew");
        players.add("Amir");
        players.add("Malachi");
        players.add("Zach");
        players.add("Kosta");
        players.add("Billy");
        players.add("Zhou");
        players.add("Zach");
        players.add("DJ");
        players.add("Milos");
        players.add("Gary");
        players.add("Michael");
        players.add("John");
        players.add("Howard");
        players.add("Michael");

        try {


            URL url = new URL("https://free-nba.p.rapidapi.com/players");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
//            conn.setRequestProperty("X-RapidAPI-Host", "ree-nba.p.rapidapi.com");
            conn.setRequestProperty("X-RapidAPI-Key", "8548bbf97fmshfbac91b9df35d10p17eec3jsn51c179578d09");


            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            while ((output = br.readLine()) != null) {
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(totlaJson);

        try {
            JSONArray msg = (JSONArray) jsonObject.get("data");
            int n = msg.size();//(msg).length();
            for (int i = 0; i < n; ++i) {
                JSONObject test = (JSONObject) msg.get(i);
                String test2 = (String) test.get("first_name");
                System.out.println(test.get("team"));
                JSONObject jOb1 = (JSONObject) test.get("team");
                System.out.println(jOb1.get("full_name"));

                System.out.println("TEST 2: " + test2);
                System.out.println(test);

                if(test2.equals(players.get(playerNUM))){
                    dataName = "\n" + "Player " + playerNUM + "/24: " + " " + (String) (players.get(playerNUM) + "\n" + (String) (jOb1.get("full_name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
