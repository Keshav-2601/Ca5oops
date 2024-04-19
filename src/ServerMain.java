import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/uni_db";
        String username = "root";
        String password = "";
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            InterfaceDao dao = new StudentDaoImp(url, username, password);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ServerSocketHandler socketHandler = new ServerSocketHandler(clientSocket, dao);
                socketHandler.handleRequest();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
