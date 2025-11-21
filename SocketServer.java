import java.net.*;
import java.io.*;

public class SocketServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server started... Waiting for client request...");

        Socket s = ss.accept();
        System.out.println("Client connected.");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        String fileName = br.readLine();
        System.out.println("Requested file: " + fileName);

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            String line;
            pw.println("FOUND");
            while ((line = fileReader.readLine()) != null) {
                pw.println(line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            pw.println("NOT_FOUND");
        }

        s.close();
        ss.close();
    }
}
