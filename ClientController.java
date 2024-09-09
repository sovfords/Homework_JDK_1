public class ClientController
{
    ClientView clientView;
    private boolean isConnected = false;
    Server server;

    ClientController(Server server)
    {
        this.server = server;
        setClientView(new ClientGUI(this));


    }



    private void setClientView(ClientView clientView)
    {
        this.clientView = clientView;
    }
    public boolean getIsWorked()
    {
        return server.getIsWorked();
    }

    public String getLogs()
    {
        return server.getLogs();
    }
    public void sendText(String text)
    {
        server.writeText(text);
    }

    public void sendClients(String text)
    {
        server.sendClients(text);
    }
    public void addClient()
    {
        server.addClient(this);
    }
    public boolean getIsConnected()
    {
        return isConnected;
    }
    public void setIsConnected(boolean isConnected)
    {
        this.isConnected = isConnected;
    }
    public void addText(String text)
    {
        clientView.sendMessage(text);
    }
}
