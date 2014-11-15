package Interface_Serial;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class Server implements Runnable 
{
    Communicator2 comunicator = new Communicator2();
    
    
    @Override
    public void run() 
    {
        int ascII;
        byte content[]; 
        String message = ""; 
        String string = "";
        
        String uuid = "fdfe2ca0-2e1d-11e4-8c21-0800200c9a66"; 
        String address = "btspp://localhost:" + uuid.replace("-", "");
    
        try {
            
            StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector.open(address);
            StreamConnection stream = notifier.acceptAndOpen();
         
            InputStream input = stream.openInputStream();
            OutputStream output = stream.openOutputStream();
         
            while (!message.equals("y")) 
            {
                ascII = input.read();
                message = new Character((char)ascII).toString();
                
                System.out.println("msn = " + message);
                
                if(comunicator.getConnected() == false ) //Somente se não estiver conectado...
                {
                    if(message.equals("p")) // BOTAO PROCURAR PORTAS  
                    {
                        comunicator.searchForPorts();
                        System.out.println("Porta encontrada ! ");
                    }

                    if(message.equals("c")) // BOTAO CONECTAR 
                    {
                        comunicator.connect();                       
                        if (comunicator.getConnected() == true)      
                        {
                            System.out.println("Conectado ! ");

                            if (comunicator.initIOStream() == true)  
                            {
                                comunicator.initListener();          
                            }
                        }
                        message = "";
                    }
                }
                
                if(comunicator.getConnected() == true) // Somente se estiver conectado...
                {
                    System.out.println("string - " + string);
                    /**
                    *Legenda dos servos.
                    * servo1 = w
                    * servo2 = k
                    * servo3 = l
                    * servo4 = m
                    * servo5 = n
                    * servo6 = o
                    **/
                    if(!message.equals("w") &&
                            !message.equals("k") && 
                            !message.equals("l") && 
                            !message.equals("m") &&
                            !message.equals("n") &&
                            !message.equals("o")
                            ) // Enquanto não digitar as variáveis acima, irá concatenar o valor na string 
                    {
                        string += message; 
                    }
                    
                    if(string.equals("dis")) // BOTAO DESCONECTAR
                    {
                        comunicator.disconnect();
                    }

                    if(message.equals("w")) // W - write valor servo1
                    {
                        comunicator.writeData(1, Integer.valueOf(string));
                        string = ""; 
                    }
                   if(message.equals("k")) // K - write valor servo2
                    {
                        comunicator.writeData(2, Integer.valueOf(string));
                        string = ""; 
                    }
                   if(message.equals("l")) // L - write valor servo3
                    {
                        comunicator.writeData(3, Integer.valueOf(string));
                        string = ""; 
                    }
                   if(message.equals("m")) // M - write valor servo4
                    {
                        comunicator.writeData(4, Integer.valueOf(string));
                        string = ""; 
                    }
                   if(message.equals("n")) // N - write valor servo5
                    {
                        comunicator.writeData(5, Integer.valueOf(string));
                        string = ""; 
                    }
                   if(message.equals("o")) // O - write valor servo6
                    {
                        comunicator.writeData(6, Integer.valueOf(string));
                        string = ""; 
                    }
                    
                }
            }
            
        }catch (IOException e) {
            System.out.println("falha na conexão");
        }
    
    }
}