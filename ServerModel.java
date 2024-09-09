import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServerModel {
    File logs;


    ServerModel()  {
        logs = new File("logs.txt");
    }

    public void addLog(String log) {
        try(FileWriter fileWriter = new FileWriter("logs.txt",true);) {
            fileWriter.write(log + "\n");

        }
        catch (IOException e)
        {
            throw new RuntimeException("Can't write log");
        }


    }

    public String getLogs()
    {
        StringBuilder text = new StringBuilder();
        try(FileReader fileReader = new FileReader("logs.txt")) {
            int c;
            while ((c = fileReader.read()) != -1)
            {
                text.append((char)c);
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Can't read log");
        }
        return text.toString();
    }

}
