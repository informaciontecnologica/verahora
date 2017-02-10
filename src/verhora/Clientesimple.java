package verhora;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

// declararamos la clase clientetcp
//
//Ingresando:
//#h la devolución es inmediata y a continuación del comando #h:
//63.70,32.30,cc,ss,190117,191635
//Significado:
//63.70 Humedad relativa 63.70 %
//32.30 Temperatura 32.3 ºC
//CC Estado de puerta cc = puerta cerrada, aa = puerta abierta
//SS Estado de inundación ss = seco, mm = mojado o inundado
//190117 Fecha dia = 19 mes = 01 (enero) año = 2017
//191635 Hora = 19 minutos = 16 segundos = 35
public class Clientesimple {

    private  String ipServidor ;//"192.168.2.5";//
    private  int PortServidor = 9761;
    public boolean bandera;
//    static final String HOST = "localhost";
//    static final int Puerto = 4444;

//    PrintWriter outr = null;
//    BufferedReader in = null;
    public String total = "";

    public Clientesimple(String ipServidor ) {
//        PrintStream salida;
//        StringBuffer instr = new StringBuffer();
        try {

            Socket skCliente = new Socket(ipServidor, PortServidor);

            System.out.println("Se conecto");
//            System.out.println(skCliente.getInetAddress());

            PrintWriter outr = new PrintWriter(skCliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(skCliente.getInputStream(),"UTF-8"));

//        } catch (UnknownHostException e) {
//
//            System.err.println("Host desconocido");
//
//            System.exit(1);
            String valor1 = "h";
            outr.print("#h");// send the response to client
            outr.flush();
            int line;

            while ((line = in.read()) != 65533) // just read everything
            {
                total = total + (char) line;
                 
            }
            if (total.length()==31){
            System.out.println("Recibio");
            bandera=true;
            } else{
                bandera=false;
            }
            outr.close();

            in.close();
            skCliente.close();
        } catch (IOException e) {
            System.err.println("No se puede conectar a localhost");
             JOptionPane.showMessageDialog(null, "No se puede conectar a localhost", "Información: " + "conección", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
   
}
