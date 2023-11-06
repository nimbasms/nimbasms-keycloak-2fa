package nimbasms.keycloak.authenticator.gateway;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.Base64;

public class NimbaSmsService implements SmsService {

    private final String senderId;
    private final String serviceId;
    private final String secretToken;

    NimbaSmsService(Map<String, String> config) {
        senderId = config.get("senderId");
        serviceId = config.get("serviceId");
        secretToken = config.get("secretToken");
    }

    @Override
    public void send(String phoneNumber, String message) {
        String targetURL = "https://api.nimbasms.com/v1/messages";
        String urlParameters = String.format(
                "{\"sender_name\": \"%s\", \"to\": [\"%s\"], \"message\": \"%s\"}",
                senderId, phoneNumber, message);
        HttpURLConnection connection = null;
        String credentials = serviceId + ":" + secretToken;
        byte[] credentialsBytes = credentials.getBytes(StandardCharsets.UTF_8);
        String apiKey = Base64.getEncoder().encodeToString(credentialsBytes);

        try {
            // Créer une URL à partir de l'adresse de l'API
            URL url = new URL(targetURL);
            // Ouvrir une connexion à cette URL
            connection = (HttpURLConnection) url.openConnection();
            // Configurer la connexion pour l'envoi d'une requête POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            // Ajouter l'en-tête d'autorisation avec la clé API encodée en base64
            connection.setRequestProperty("Authorization", "Basic " + apiKey);
            connection.setDoOutput(true);

            // Écrire le corps de la requête dans le flux de sortie de la connexion
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = urlParameters.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Envoyer la requête et lire la réponse
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // Succès
                try (Scanner scanner = new Scanner(connection.getInputStream(), StandardCharsets.UTF_8.name())) {
                    String response = scanner.useDelimiter("\\A").next();
                    System.out.println(response); // Gérez la réponse comme nécessaire
                }
            } else {
                // Gérez les codes de réponse autres que HTTP OK
                System.out.println("Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
