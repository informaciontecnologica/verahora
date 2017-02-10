/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verhora;

/**
 *
 * @author daniel
 */
//import java.awt.Label;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reloj {

// monitores mon = new monitores();
// Controller credit = new Controller(mon);
//
    private monitores mon;
    private String ipServidor = "";

    Reloj(monitores mon) {
        this.mon = mon;
    }
    ReConfiguracion parametros = new ReConfiguracion();
    RegistroConfiguracion registro = new RegistroConfiguracion();

    Timer timer = new Timer(); // El timer que se encarga de administrar los tiempo de repeticion
    public int segundos; // manejar el valor del contador
    public boolean frozen; // manejar el estado del contador TIMER AUTOMATICO -- True Detenido | False Corriendo
    int cont = 0;
    // clase interna que representa una tarea, se puede crear varias tareas y asignarle al timer luego

    class MiTarea extends TimerTask {

        public void run() {
//            try {
//                
            segundos++;
            System.out.println(segundos);

            System.out.println();
            Clientesimple simple = new Clientesimple(parametros.getIpServidor());
//                
//                //Ingresando:
//                //#h la devolución es inmediata y a continuación del comando #h:
//                //63.70,32.30,cc,ss,190117,191635
//                //Significado:
//                //63.70 Humedad relativa 63.70 %
//                //32.30 Temperatura 32.3 ºC
//                //CC Estado de puerta cc = puerta cerrada, aa = puerta abierta
//                //SS Estado de inundación ss = seco, mm = mojado o inundado
//                //190117 Fecha dia = 19 mes = 01 (enero) año = 2017
//                //191635 Hora = 19 minutos = 16 segundos = 35
            if (simple.bandera) { // bandera algunas no llega la informacion por retardo del sistema y continuar con el reloj o sino se para el sistema
                // la segunda ves recibe bien .
                String cadena = simple.total;
                if ((cadena != null) || (cadena.length() == 31)) {
                    String[] registros = cadena.split(",");
                    String Humedad = registros[0];
                    String Temperatura = registros[1];
                    String Puerta = registros[2];
                    String Liquidos = registros[3];
                    String Fecha1 = registros[4];
                    String Hora1 = registros[5];
                    String Fecha = Fecha1.substring(0, 2) + "/" + Fecha1.substring(2, 4) + "/20" + Fecha1.substring(4, 6);
                    String Hora = Hora1.substring(0, 2) + ":" + Hora1.substring(2, 4) + ":" + Hora1.substring(4, 6);
                    if (Puerta.codePointAt(0) == 97) {
                        Puerta = "Abierta";
                        mon.EventoPuerta("blink");
                    } else if (Puerta.codePointAt(0) == 99) {
                        
                        Puerta = "Cerrada";
                    }
//            System.out.println(Liquidos.codePointAt(0));
                    switch (Liquidos.codePointAt(0)) {
                        case 115:
                            Liquidos = "Sin Fluidos";
                            break;
                        case 109:
                            Liquidos = "!!Fluidos . Ver!!";
                        Toolkit.getDefaultToolkit().beep();
                            mon.EventoLiquidos("blink");
                            break;
                        default:
                            Liquidos = "Error";
                            break;
                    }
                    int hume = Integer.parseInt(registros[0].substring(0, 2));
                    if (hume <= parametros.getHumMin()) {
                        Toolkit.getDefaultToolkit().beep();
                        mon.EventoHumedad("blink");
                    } else if (parametros.getHumMAx() <= hume) {
                        Toolkit.getDefaultToolkit().beep();
                        mon.EventoHumedad("blink");
                    }

                    int tem = Integer.parseInt(registros[1].substring(0, 2));
                    if (tem <= parametros.getTempMin()) {
                        Toolkit.getDefaultToolkit().beep();
                        mon.EventoTemperatura("blink");
                    } else if (parametros.getTempMax() <= tem) {
                        Toolkit.getDefaultToolkit().beep();
                        mon.EventoTemperatura("blink");
                    }

//                String c= String.valueOf(tem);
//                
//                
//                System.out.println("valor:"+c+"--"+parametros.getTempMax());
                    mon.temperatura = Temperatura + "°C";
                    mon.SetTemperatura();
                    mon.humedad = Humedad + "%";
                    mon.SetHumedad();
                    mon.puerta = Puerta;
                    mon.SetPuerta();
                    mon.liquidos = Liquidos;
                    mon.SetLiquidos();
                    mon.hora = Hora;
                    mon.SetHora();
                    mon.fecha = Fecha;
                    mon.SetFecha();
                }
            }
////                       mon.settt("comerrr");
// aqui se puede escribir el codigo de la tarea que necesitamos ejecutar
//            }
//            } catch (IOException ex) {
//                 System.err.println("EEE desconocido");
//
//            System.exit(1);
//            }
        }
    }// end SincronizacionAutomatica

    public void Start() {
//        String registro="";
        try {
            parametros.getReConfiguracion(registro);
        } catch (IOException e) {

        }
//        ipServidor=parametros.getIpServidor();
        String DATE_FORMAT_NOW = "dd-MM-yyyy";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        String stringDate = sdf.format(date);
//       registro=(String) simple.getTotal().get(2);
//        String[] reg= simple.getRegistro();
//        System.out.println(reg[0]);

//        credit.reset();
        frozen = false;
        // le asignamos una tarea al timer
        timer.schedule(new MiTarea(), 4, 1000);

    }// end Start

    public void Stop() {
        System.out.println("Stop");
        frozen = true;
    }// end Stop

    public void Reset() {
        System.out.println("Reset");
        frozen = true;
        segundos = 0;
    }// end Reset

}// end Reloj
