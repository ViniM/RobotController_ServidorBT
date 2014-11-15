/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface_Serial;

import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;

public class Communicator2 implements SerialPortEventListener
{
    private String portName = "";
    
    private Enumeration ports = null;
    private HashMap portMap = new HashMap();

    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;

    private InputStream input = null;
    private OutputStream output = null;

    private boolean isConnected = false;

    final static int TIMEOUT = 2000;
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;

    String logText = "";

    public Communicator2()
    {
        
    }

    public void searchForPorts()
    {
        ports = CommPortIdentifier.getPortIdentifiers(); 
        
        while (ports.hasMoreElements())     
        {
            CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();  // Insere um elemento na lista de portas

            if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
            {
                portName = curPort.getName();  
                portMap.put(curPort.getName(), curPort);
            }
        }
    }

    public void connect()
    {
        String selectedPort = portName;
        selectedPortIdentifier = (CommPortIdentifier)portMap.get(selectedPort);

        CommPort commPort = null;

        try
        {
            commPort = selectedPortIdentifier.open("Interface_Serial", TIMEOUT);
            serialPort = (SerialPort)commPort;

            setConnected(true);

            logText = selectedPort + " opened successfully.";
            
        }
        catch (PortInUseException e) // Mensagem para porta já em uso
        {
            logText = selectedPort + " is in use. (" + e.toString() + ")";
            
        }
        catch (Exception e)  //  Mensagem para erro ao abrir
        {
            logText = "Failed to open " + selectedPort + "(" + e.toString() + ")";
        }
    
    }

    public boolean initIOStream()
    {
        boolean successful = false;

        try 
        {
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            
            successful = true;
            return successful;
        }
        
        catch (IOException e) 
        {
            logText = "I/O Streams failed to open. (" + e.toString() + ")";
            return successful;
        }
    }

    public void initListener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
            logText = "Too many listeners. (" + e.toString() + ")";
        }
    }

    public void disconnect()
    {
    
        try
        {

            setConnected(false);
            
            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();
            setConnected(false);
        
            logText = "Disconnected.";
        }
        catch (Exception e)
        {
            logText = "Failed to close " + serialPort.getName() + "(" + e.toString() + ")";
        }
    }

    final public boolean getConnected()
    {
        return isConnected;
    }

    public void setConnected(boolean bConnected)
    {
        this.isConnected = bConnected;
    }

    public void serialEvent(SerialPortEvent evt) 
    {
        
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
            try
            {
                byte singleData = (byte)input.read();

                if (singleData != NEW_LINE_ASCII)
                {
                    logText = new String(new byte[] {singleData});
                    System.out.println(logText);
                }
                else
                {
                    System.out.println("\n");
                }
            }
            catch (Exception e)
            {
                logText = "Failed to read data. (" + e.toString() + ")";
            }
        }
    }

    public void writeData(int servo, int angle)
    {
        try
        {
            output.write(servo);
            output.flush();
            //this is a delimiter for the data
            output.write(DASH_ASCII);
            output.flush();
            
            output.write(angle);
            output.flush();
            //will be read as a byte so it is a space key
            output.write(SPACE_ASCII);
            output.flush();
        }
        catch (Exception e)
        {
            logText = "Failed to write data. (" + e.toString() + ")";
        }
    }

}