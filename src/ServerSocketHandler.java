import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSocketHandler {
    private final Socket clientSocket;
    private final InterfaceDao dao;

    public ServerSocketHandler(Socket clientSocket, InterfaceDao dao) {
        this.clientSocket = clientSocket;
        this.dao = dao;
    }

    public void handleRequest() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = reader.readLine();
            int studentId = Integer.parseInt(request);

            Student student = dao.getStudentById(studentId);

            String jsonResponse = JsonConverter.studentToJson(student);

            writer.println(jsonResponse);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
