/*
    RAYA - ПРОГРАММИРОВАНИЕ И IT 
*/ 

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread{
    private Server server;
    private PrintWriter outMessage;
    private Socket socket;

    private static int clients_count = 0;

    public ClientHandler(Socket socket, Server server)
    {
        try {
            clients_count++;
            this.server = server;
            this.socket = socket;
        }catch (Exception e){

        }
    }

    public void sendMessage(String message){
        try{
            outMessage.println(message);
        }catch (Exception e){

        }
    }

    public void closeConnection(){
        server.removeClient(this);
        clients_count--;
        server.broadcast("Клиентов в чате = " + clients_count, this);
    }


    @Override
    public void run() {
        try{

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            OutputStream outputStream = socket.getOutputStream();
            outMessage = new PrintWriter(outputStream,true);

            String userName = reader.readLine();
            server.addUserName(userName);
            System.out.println("New client: " + userName);
            while (true){
                server.broadcast("Новый участник",this);
                server.broadcast("Клиентов в чате = " + clients_count,this);
                break;
            }

            String clientMessage;

            do{

                    clientMessage = reader.readLine();
                    server.broadcast(userName + " - " + clientMessage, this);
            }while (!clientMessage.equals("END"));
            socket.close();
        }catch (Exception e){

        }
    }
}
