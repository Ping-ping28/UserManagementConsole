package userManagement.tele;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
public class telegramNoti {
    private static final String BOT_TOKEN = "7758679354:AAGvLn9X1PwPp7bI3NIcr146QzqqSfumQn8"; // Replace with your bot token
    private static final String CHAT_ID = ":7758679354"; // Replace with your chat ID
    public void sendNotification(String message) {
        try {

            String encodedMessage = URLEncoder.encode(message, "UTF-8");

            String apiUrl = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage?chat_id=" + CHAT_ID + "&text=" + encodedMessage;

            System.out.println("API URL: " + apiUrl);

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response from Telegram: " + response);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}