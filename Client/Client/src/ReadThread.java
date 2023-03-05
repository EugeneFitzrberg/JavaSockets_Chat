import GUI.GUICLient;
import GUI.ISetText;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread{
    private static BufferedReader in;
    private Socket socket;
    private Client client;
    private ISetText iSetText;
    public ReadThread(Socket socket, Client client, ISetText guicLient){
        this.client = client;
        this.socket = socket;
        this.iSetText = guicLient;

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
                System.out.println("\n" + response);
                iSetText.setDate(response);
//                if(client.getUserName() != null){
//                    System.out.println("User: "+ client.getUserName());
//                    System.out.print(client.getUserName() + "-> ");
//                }
            }catch (Exception e){

            }
        }
    }
}
