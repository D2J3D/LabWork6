package Client;

import Server.commands.Command;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args){

        try{
            Socket client = new Socket("localhost", 8001);
            System.out.println("Hi, connection to the server is accepted!\nType 'help' in order to know about the commands");

            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            DataInputStream ois = new DataInputStream(client.getInputStream());
            DataOutputStream oos = new DataOutputStream(client.getOutputStream());

            ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream objIn = new ObjectInputStream(client.getInputStream());


            System.out.println();
            while (!client.isOutputShutdown()){
                if (br.ready()){

                    String clientData = br.readLine();
                    String[] params = clientData.split(" ");

                    String clientCommand = params[0];
                    String clientParam = null;
                    CommandCaller commandCaller = new CommandCaller(clientCommand);
                    ClientInvoker ci = new ClientInvoker(commandCaller);
                    if (params.length > 1){
                        clientParam = params[1];
                    }

                    if (clientCommand.equals("exit")){
                        objOut.writeObject(commandCaller);
                        objOut.flush();
                        break;
                    }

                    if (ci.recognizeCommand(clientCommand)){
                        if (params.length > 1){
                            commandCaller.setCommandParam(clientParam);
                        }
                        objOut.writeObject(commandCaller);
                        objOut.flush();
                        System.out.println("Client sent command " + commandCaller.getCommandName() + " to server");
                        System.out.println("----------------------------------------");
                        Command command = (Command) objIn.readObject();
                        command.execute();
                        System.out.println("----------------------------------------");
                    }


                }
            }
            System.out.println("Connection closed");
            oos.close();
            ois.close();
            client.close();



        } catch (UnknownHostException e) {
            System.out.println("Server is unavailable\nConnection closed");
        } catch (IOException e) {
            System.out.println("Server is unavailable\nConnection closed");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
