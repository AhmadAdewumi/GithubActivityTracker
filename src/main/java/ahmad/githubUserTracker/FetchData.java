package ahmad.githubUserTracker;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetchData {
    private String username;

    public FetchData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private List<GithubEvents> event = new ArrayList<>();
    private static final String FILE_NAME = ".Activity.json";
    private static final Gson gson = new GsonBuilder()
            //.excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    public void getActivity() {

        try {
            URL url = new URL("https://api.github.com/users/" + this.username + "/events");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                System.out.println("Data recieved successfully");
            }else
                System.out.println("Failed to retrieve data");
            System.out.println("RESPONSE CODE FOR GET METHOD IS :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (
//                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_NAME, true)));
                        FileWriter fileWriter = new FileWriter(createFileDirectory());
                        BufferedWriter writer = new BufferedWriter(fileWriter);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                ) {
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
//                    System.out.println(response.toString());
                    GithubEvents[] events = gson.fromJson(response.toString(),GithubEvents[].class);
                    event = Arrays.asList(events);
                    System.out.println("Current working directory : " + System.getProperty("user.dir"));
                    writer.write(gson.toJson(event));
                    String json = gson.toJson(event);
                    System.out.println(json);

                    System.out.println("Data written to file successfully!");

                }
            } else
                System.out.println("GET request failed!");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File createFileDirectory(){
        String dir = System.getProperty("user.home");
        System.out.println(dir);
        File jsonFile = new File(dir,FILE_NAME);

        try {
            if (!jsonFile.exists()){
                jsonFile.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonFile;
    }
}
