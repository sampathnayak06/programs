import java.net.*;
import java.io.*;

public class SocketClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        System.out.print("Enter file name: ");
        String fileName = console.readLine();

        pw.println(fileName);

        String response = br.readLine();

        if (response.equals("FOUND")) {
            System.out.println("\n--- File Content ---");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } else {
            System.out.println("File not found on server.");
        }

        s.close();
    }
}
