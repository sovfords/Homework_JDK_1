public class Main {
    public static void main(String[] args)
    {
        Server serverController = new Server();
        ClientController clientGUIView = new ClientController(serverController);
        ClientController clientGUIView1 = new ClientController(serverController);


    }
}
