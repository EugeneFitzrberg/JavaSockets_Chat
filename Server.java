/*
    RAYA - ПРОГРАММИРОВАНИЕ И IT 
*/ 

package Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ArrayList<ClientHandler> clients = new ArrayList<>(); // Список клиентов

    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(4040); // Использует не занятый TCP порт
        Socket clientSocket = null;

        System.out.println("Сервер запустился"); // Вывод в консоль сервера
        // sendMessageToAllClients("Сервер запустился"); // Метод для оповещения клиентов - нет смысла при запуске оповещать, но малоли)
        
        try{
            while(true){
                    clientSocket = serverSocket.accept();
                    ClientHandler client = new ClientHandler(clientSocket,this);

                    clients.add(client);
                    new Thread(client).start();      // Поток клиента
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
    }
    public void sendMessageToAllClients(String message){
        for(ClientHandler client : clients){
            client.sendMessage(message);        // Сообщение для всех клиентов
        }
    }

    public void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);                   // удаляем клиента
    }


    public static void main(String[] args) throws IOException {
        Server server = new Server();
    }
}
