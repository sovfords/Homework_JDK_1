import java.util.ArrayList;

public class Server {


    public ArrayList<ClientGUIView> clients;
    private String ip = "1";
    private String port = "8189";
    private boolean isWorked = false;


    ServerModel serverModel;
    ServerView serverView;
    Server()
    {
        serverModel = new ServerModel();
        serverView = new ServerView(this);

        clients = new ArrayList<>();

    }

    public void writeText(String text)
    {
        if(isWorked)
        {
            serverView.writeText(text);
            serverModel.addLog(text);




        }
    }

    public boolean getIsWorked()
    {
        return isWorked;
    }
    public void setIsWorked(boolean value)
    {
        isWorked = value;
    }

    public void addClient(ClientGUIView client)
    {
        clients.add(client);
    }

    public void sendClients(String text)
    {

            for(ClientGUIView client : clients)
            {
                if(client.isConnected)
                {
                    client.sendMessage(text);
                }

            }
    }

    public String getLogs()
    {

        return serverModel.getLogs();

    }



}
