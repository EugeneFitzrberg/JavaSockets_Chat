import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
    private static BufferedReader in;
    private Socket socket;
    private Client client;
    public ReadThread(Socket socket, Client client){
        this.client = client;
        this.socket = socket;

        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        }catch (Exception e){

        }
    }

    @Override
    public void run() {
        while(true){
            try{
                String response =in.readLine();
                System.out.println("\n Response: " + response);

                if(client.getUserName() != null){
                    System.out.println("User: "+ client.getUserName());
                }
            }catch (Exception e){

            }
        }
    }
}
