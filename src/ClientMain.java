import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientMain {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 5000;
        try (
                Socket socket = new Socket(serverAddress, serverPort);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            writer.println("GET_ALL_ENTITIES");
            String jsonResponse = reader.readLine();

            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                Gson gson = new Gson();
                List<Student> students = gson.fromJson(jsonResponse, new TypeToken<List<Student>>() {}.getType());

                for (Student student : students) {
                    System.out.println(student);
                }
            } else {
                System.out.println("No students found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
