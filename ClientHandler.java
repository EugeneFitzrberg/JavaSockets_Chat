/*
    RAYA - ПРОГРАММИРОВАНИЕ И IT 
*/ 

package Sockets;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;

    private static int clients_count = 0;

    public ClientHandler(Socket socket, Server server)
    {
        try {
            clients_count++;
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());

        }catch (Exception e){

        }
    }

    public void sendMessage(String message){
        try{
            outMessage.println(message);
            outMessage.flush();
        }catch (Exception e){

        }
    }

    public void closeConnection(){
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
    }


    @Override
    public void run() {
        try{
            while (true){
                server.sendMessageToAllClients("Новый участник");
                server.sendMessageToAllClients("Клиентов в чате = " + clients_count);
                break;
            }

            while(true){
                if(inMessage.hasNext()){
                    String clientMessage = inMessage.nextLine();

                    if(clientMessage.equals("END")){
                        break;
                    }

                    System.out.println(clientMessage);
                    server.sendMessageToAllClients(clientMessage);

                }
                Thread.sleep(100);

            }
        }catch (Exception e){

        }
    }
}
