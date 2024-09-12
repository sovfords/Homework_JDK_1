import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerView extends JFrame implements ServerW{

    Server server;




    int WIDTH = 350;
    int HEIGHT = 250;
    int POS_X = 800;
    int POS_Y = 400;

    String MSG_START = "Сервер запущен";
    String MSG_STOP = "Сервер остановлен";


    JTextArea serverText;
    JScrollPane scrollPane;
    JPanel panel;
    JButton btnStart;
    JButton btnStop;

    ServerView(Server server)
    {
        this.server = server;
        serverText = new JTextArea(1,1);
        JScrollPane scrollPane = new JScrollPane(serverText);
        scrollPane.setSize(20,100);
        serverText.setLineWrap(true);




        panel = new JPanel();
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        setTitle("Chat server");
        setSize(WIDTH,HEIGHT);
        setLocation(POS_X,POS_Y);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        add(serverText);
        add(scrollPane,BorderLayout.WEST);
        panel.setLayout(new GridLayout(1,2));
        panel.add(btnStart);
        panel.add(btnStop);
        add(panel,BorderLayout.SOUTH);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(! server.getIsWorked())
                {
                    server.setIsWorked(true);

                    writeText(MSG_START);
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                writeText(MSG_STOP);
                server.setIsWorked(false);
                for(ClientController client:server.getClients())
                {
                    client.getClientView().disconnect();
                }
            }
        });


    }

    @Override
    public void writeText(String  text)
    {
        if(server.getIsWorked())
        {
            serverText.append(text + "\n");
        }

    }
    public String readText()
    {
        return serverText.getText();
    }



    }


