import java.util.ArrayList;

public class Server {


    public ArrayList<ClientController> clients;

    private boolean isWorked = false;


    ServerM serverModel;
    ServerW serverView;
    Server()
    {

        serverView = setView(new ServerView(this));
        serverModel = setModel(new ServerModel());
        clients = new ArrayList<>();

    }

    private ServerW setView(ServerView serverView)
    {
        return serverView;
    }

    private ServerM setModel(ServerM serverModel)
    {
        return serverModel;
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

    public void addClient(ClientController client)
    {
        clients.add(client);
    }

    public void sendClients(String text)
    {

            for(ClientController client : clients)
            {
                if(client.getIsConnected())
                {
                    client.addText(text);
                }

            }
    }

    public String getLogs()
    {
        return serverModel.getLog();
    }

    public ArrayList<ClientController> getClients()
    {
        return clients;
    }



}
