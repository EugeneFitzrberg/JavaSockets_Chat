import java.io.*;
import java.net.Socket;

public class WriteThread extends Thread{

    private Socket socket;
    private Client client;
    private static PrintWriter out;

    public WriteThread(Socket socket, Client client){
        this.client = client;
        this.socket = socket;

        try {
            OutputStream output = socket.getOutputStream();
            out  = new PrintWriter(output,true);
        }catch (Exception e){

        }
    }

    @Override
    public void run() {

        Console console = System.console();
        String userName = console.readLine("Enter username :");
        client.setUserName(userName);


        try{
            out.println(userName);

            String text = console.readLine(userName + "-> ");

            while(!text.equals("END")){
                text = console.readLine(userName + "-> ");
                out.println(text);
            }

        }catch (Exception e){

        }finally {
            try{
            socket.close();
            }catch (Exception e){

            }
        }
    }
}
