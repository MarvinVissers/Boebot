package Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;

import json.*;

public class LogController extends ApiRequest {
    /**
     * Constructor for the Controller.ApiRequest
     *
     * @param sMap the map of the api
     */
    public LogController(String sMap) throws UnknownHostException {
        super(sMap);
    }

    /**
     * Function to get the content of the API map
     *
     * @return List with objects of the API map
     */
    @Override
    public ArrayList<Object> get() {
        return null;
    }

    /**
     * Function to post to the map of the API
     *
     * @param obj model of the object of the API map
     */
    @Override
    public void post(Object obj) {
        System.out.println(obj);

        try {

            URL oracle = new URL("https://bp6.adainforma.tk/helloworldbot/functions/datalayer/api/obstacle/?selector=ae026dd58cd57fd2&validator=4424bdd85905aa88646327911b6893598a279abb4f82466dca61a988041afb08&action=get");
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                JsonArray json = Json.parse(inputLine).asArray();

                System.out.println(json);

                for (Object o : json) {

                    JsonObject Cords = (JsonObject) o;
//                    //haal de eerste coords op.
//                    JsonValue Row = Cords.get("row1");
//                    JsonValue Column = Cords.get("column1");
//                    //haal de 2e coords op.
//                    JsonValue Row2 = Cords.get("row2");
//                    JsonValue Column2 = Cords.get("column2");

                    String iRow = removeQuotes(Cords.get("row1"));
                    System.out.println(iRow);

//                    int iRow = Integer.parseInt(String.valueOf(Row));
//                    int iColum = Integer.parseInt(String.valueOf(Column));
//                    int iRow2 = Integer.parseInt(String.valueOf(Row2));
//                    int iColum2 = Integer.parseInt(String.valueOf(Column2));

//                    System.out.println(iRow);

//                    blocksArray.add(new int[]{iRow, iColum});
//                    blocksArray.add(new int[]{iRow2, iColum2});

                    //TODO er voor zorgen dat alles word toegevoegd.
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Function to remove quotes from the JsonValue String
     *
     * @param sJsonValue the gotton value of the API
     */
    @Override
    public String removeQuotes(JsonValue sJsonValue) {
        String sNewJsonValue = sJsonValue.toString().replace("\"", "");

        return sNewJsonValue;
    }
}
