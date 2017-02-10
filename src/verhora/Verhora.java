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
public class Verhora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        monitores moni = new monitores();
        moni.setDefaultCloseOperation(moni.EXIT_ON_CLOSE);
        
        Reloj ns = new Reloj(moni);
        ns.Start();
        moni.setVisible(true);

    }

}
