/*
    RAYA - ПРОГРАММИРОВАНИЕ И IT 
*/ 

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;

    private String host;
    private int port;
    private String userName;

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void execute(){
        try{
            clientSocket = new Socket(host,port);       // Подключение к серверу на localhost:4040
            new ReadThread(clientSocket,this).start();
            new WriteThread(clientSocket,this).start();

        }catch(Exception e){

        }finally {
            System.out.println("Client has exit");
        }
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return this.userName;
    }

    public static void main(String[] args) throws IOException {
      if(args.length < 2){
          System.err.println("Error: enter the host and port!");
          System.exit(0);
      }

      String host = args[0];
      int port = Integer.parseInt(args[1]);

      Client client = new Client(host,port);
      client.execute();

    }

}
