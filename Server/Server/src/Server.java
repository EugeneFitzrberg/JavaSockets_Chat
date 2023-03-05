/*
    RAYA - ПРОГРАММИРОВАНИЕ И IT 
*/ 

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Server {

    private ArrayList<ClientHandler> clients = new ArrayList<>(); // Список клиентов
    private Set<String> userNames = new HashSet<>();
    private int port;

    public Server(int port) {
        this.port = port;
    }
    public void execute(){
        try(ServerSocket serverSocket = new ServerSocket(port);){ // Использует не занятый TCP порт
        Socket clientSocket = null;

        System.out.println("RAYA chat server listening port: " + port);
        try{
            while(true){
                    clientSocket = serverSocket.accept();
                    ClientHandler client = new ClientHandler(clientSocket,this);

                    clients.add(client);
                    client.start();
            }
        }catch (Exception e){

        }finally {
            try{
                clientSocket.close();
                serverSocket.close();

            }catch (Exception e){
                // TODO: обработчик исключений
            }
        }
        }catch (Exception e){

        }
    }
    public void broadcast(String message, ClientHandler clientHandler){
        for(ClientHandler client : clients){
            if(client != clientHandler){
            client.sendMessage(message);        // Сообщение для всех клиентов
            }
        }
    }

    public void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);                   // удаляем клиента
    }

    public void addUserName(String userName){
        userNames.add(userName);
    }

    public Set<String> getUserNames(){
        return userNames;
    }


    public static void main(String[] args) throws IOException {
        if(args.length < 1){
            System.err.println("Error: Enter the port!");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);
        Server server = new Server(port);

        server.execute();
    }
}
