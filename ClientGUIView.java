import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUIView extends JFrame
{

    boolean isConnected = false;
    public String nickname;
    Server server;

    JPanel upComponents;
    JPanel downComponents;

    JTextArea clientText;
    JPasswordField passwordField;
    JTextField nicknameField;
    JTextField ipField;
    JTextField portField;
    JTextField messageField;
    JTextField empty;

    JButton btnLogin;
    JButton btnSend;

    int WIDTH = 350;
    int HEIGHT = 250;
    int POS_X = 450;
    int POS_Y = 400;


    ClientGUIView(Server server)
    {
        this.server = server;
        upComponents =new JPanel();
        downComponents = new JPanel(new BorderLayout());
        btnLogin = new JButton("login");
        ipField = new JTextField("127.0.0.1");
        portField = new JTextField("8120");
        nicknameField = new JTextField("Matt");
        passwordField = new JPasswordField("12354");
        messageField=  new JTextField();
        btnSend = new JButton("Send");
        clientText = new JTextArea();
        empty = new JTextField();

        empty.setVisible(false);
        clientText.setLineWrap(true);
        clientText.setWrapStyleWord(true);

        setTitle("client.");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(POS_X,POS_Y);
        setSize(WIDTH,HEIGHT);
        setResizable(false);

        upComponents.setLayout(new GridLayout(2,3));
        upComponents.add(ipField);
        upComponents.add(portField);
        upComponents.add(empty);
        upComponents.add(nicknameField);
        upComponents.add(passwordField);
        upComponents.add(btnLogin);


        downComponents.add(messageField);
        downComponents.add(btnSend,BorderLayout.EAST);

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (server.getIsWorked() && isConnected)
                {
                    server.writeText(nickname + ": " +messageField.getText());

                    server.sendClients(nickname + ": " + messageField.getText());
                    messageField.setText("");


                }
                else
                {
                    clientText.append("Подключение разовано \n");
                }
            }
        });


        add(upComponents,BorderLayout.NORTH);
        add(downComponents,BorderLayout.SOUTH);
        add(clientText);
        setVisible(true);


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    nickname = nicknameField.getText();

                if( server.getIsWorked())
                {
                    setTitle("client:" + nickname);
                    upComponents.setVisible(false);
                    clientText.append("Подключение успешно! \n");
                    isConnected = true;

                    clientText.append(server.getLogs());



                    server.writeText(nickname + " подключился к беседе \n");


                }
                else
                {
                    clientText.append("Не удалось подключиться");
                }


            }
        });

            server.addClient(this);


        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getIsWorked() && isConnected)
                {
                    server.writeText(nickname + ": " +messageField.getText());

                    server.sendClients(nickname + ": " + messageField.getText());
                    messageField.setText("");


                }
                else
                {
                    clientText.append("Подключение разовано \n");
                }

            }
        });


    }

    public void sendMessage(String text )
    {
        clientText.append(text + "\n");
    }
    public void disconnect()
    {
        upComponents.setVisible(true);
        isConnected = false;
    }
}
