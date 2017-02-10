/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verhora;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public class ReConfiguracion {

    public String ipServidor;//"192.168.2.5";//
    public int PortServidor = 9761;
    public String total = "";
    private int HumMAx, HumMin, TempMin, TempMax, Refresco;
    RegistroConfiguracion Registro = new RegistroConfiguracion();

    public void getReConfiguracion(RegistroConfiguracion re) throws FileNotFoundException {
        File fleExample = new File("ip.txt");
        Scanner opnScanner = new Scanner(fleExample);

        // Read each line in the file
        while (opnScanner.hasNext()) {
            // Read each line and display its value
            this.ipServidor = opnScanner.nextLine();
            this.PortServidor = Integer.parseInt(opnScanner.nextLine());
            this.HumMin = Integer.parseInt(opnScanner.nextLine());
            this.HumMAx = Integer.parseInt(opnScanner.nextLine());
            this.TempMin = Integer.parseInt(opnScanner.nextLine());
            this.TempMax = Integer.parseInt(opnScanner.nextLine());
            this.Refresco = Integer.parseInt(opnScanner.nextLine());

            System.out.println("ipServidor:    " + ipServidor);
            System.out.println("PortServidor:     " + PortServidor);
            System.out.println("HumMin: " + HumMin);
            System.out.println("HumMAx: " + HumMAx);
            System.out.println("TempMin: " + TempMin);
            System.out.println("TempMax: " + TempMax);
            System.out.println("Refresco: " + Refresco);

        }
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public void setPortServidor(int PortServidor) {
        this.PortServidor = PortServidor;
    }

    public boolean setReConfiguracion(RegistroConfiguracion re) throws FileNotFoundException {
        File archivo = new File("ip.txt");
        PrintWriter wrtStudent = new PrintWriter(archivo);
        wrtStudent.println(re.ipServidor);
        wrtStudent.println(re.PortServidor);
        wrtStudent.println(re.HumMin);
        wrtStudent.println(re.HumMAx);
        wrtStudent.println(re.TempMin);
        wrtStudent.println(re.TempMax);

        wrtStudent.println(re.Refresco);
        wrtStudent.close();
        // For convenience, let the user know that the file has been created
        System.out.println("The file has been created.");

        return true;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public int getTempMin() {
        return TempMin;
    }

    public int getTempMax() {
        return TempMax;
    }

    public int getHumMAx() {
        return HumMAx;
    }

    public int getHumMin() {
        return HumMin;
    }

    public int getRefresco() {
        return Refresco;
    }

    public int getPortServidor() {
        return PortServidor;
    }

}
