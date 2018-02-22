/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interprete;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author FernandoEsteban
 */
public class Interfaz extends javax.swing.JFrame {

    /**
     * Creates new form Interfaz
     */
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JButton jButton10;
    private JTextField jTextField10;
    private JPanel contenedorVentana;
    private JPanel contenedorTop;
    private JPanel contenedorMiddle;
    private JPanel contenedorBottom;
    private JComboBox select1;
    private JComboBox select2;
    private JComboBox select3;
    private Ventana v;
    
    public Interfaz() {
        initComponents();
        inicializar();
    }
    
    private void inicializar(){
        v = new Ventana();
        v.setSize(310, 210);                                 // colocamos tamanio a la ventana (ancho, alto)
        v.setLocationRelativeTo(this);                       // centramos la ventana en la pantalla
        v.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        v.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
        this.setLocationRelativeTo(null);
        jLabel10 = new JLabel("Insertar caracter: ");
        jLabel11 = new JLabel("Guardar en: ");
        jLabel12 = new JLabel(" con ");
        jLabel13 = new JLabel("Aplicar a: ");
        jButton10 = new JButton("OK");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jTextField10 = new JTextField();
        contenedorVentana =  new JPanel();
        contenedorTop =  new JPanel();
        contenedorMiddle =  new JPanel();
        contenedorBottom =  new JPanel();
        
        
        select1 =  new JComboBox();
        select1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // Aquí hacemos lo que queramos hacer.
            }
        });
        select1.addItem("Autómata 1");
        select1.addItem("Autómata 2");
        select1.addItem("Autómata 3");
        select2 =  new JComboBox();
        select2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // Aquí hacemos lo que queramos hacer.
            }
        });
        select2.addItem("Autómata 1");
        select2.addItem("Autómata 2");
        select2.addItem("Autómata 3");
        select3 =  new JComboBox();
        select3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // Aquí hacemos lo que queramos hacer.
            }
        });
        select3.addItem("Autómata 1");
        select3.addItem("Autómata 2");
        select3.addItem("Autómata 3");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Operaciones");

        jButton1.setText("Crear AFN básico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Unir AFN's");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Concatenar AFN's");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Operación cerradura +");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Operación cerradura *");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Operación ?");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Autómata 1:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Autómata 2:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Autómata 3:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton7.setText("Validar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton8.setText("Validar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton9.setText("Validar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton9)))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (evt.getSource() == jButton2){
            v.setVisible(true);
            v.setTitle("Unir AFN's");
            
            contenedorTop.removeAll();
            contenedorMiddle.removeAll();
            contenedorBottom.removeAll();
            contenedorTop.setLayout(new GridLayout(1,3));
            contenedorTop.add(select1);
            contenedorTop.add(jLabel12);
            contenedorTop.add(select2);
            contenedorMiddle.setLayout(new GridLayout(1,2));
            contenedorMiddle.add(jLabel11);
            contenedorMiddle.add(select3);
            contenedorBottom.setLayout(new GridLayout(1,1));
            contenedorBottom.add(jButton10);
            
            v.setLayout(new GridLayout(3,1));
            v.add(contenedorTop);
            v.add(contenedorMiddle);
            v.add(contenedorBottom);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (evt.getSource() == jButton3){
            v.setVisible(true);
            v.setTitle("Concatenar AFN's");
            
            contenedorTop.removeAll();
            contenedorMiddle.removeAll();
            contenedorBottom.removeAll();
            contenedorTop.setLayout(new GridLayout(1,3));
            contenedorTop.add(select1);
            contenedorTop.add(jLabel12);
            contenedorTop.add(select2);
            contenedorMiddle.setLayout(new GridLayout(1,2));
            contenedorMiddle.add(jLabel11);
            contenedorMiddle.add(select3);
            contenedorBottom.setLayout(new GridLayout(1,1));
            contenedorBottom.add(jButton10);
            
            v.setLayout(new GridLayout(3,1));
            v.add(contenedorTop);
            v.add(contenedorMiddle);
            v.add(contenedorBottom);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (evt.getSource() == jButton6){
            v.setVisible(true);
            v.setTitle("Operación ?");
            
            contenedorTop.removeAll();
            contenedorMiddle.removeAll();
            contenedorBottom.removeAll();
            contenedorTop.setLayout(new GridLayout(1,2));
            contenedorTop.add(jLabel13, BorderLayout.WEST);
            contenedorTop.add(select1);
            contenedorBottom.setLayout(new GridLayout(1,1));
            contenedorBottom.add(jButton10);
            
            v.setLayout(new GridLayout(2,1));
            v.add(contenedorTop);
            v.add(contenedorBottom);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (evt.getSource() == jButton1){
            v.setVisible(true);
            v.setTitle("Crear AFN básico");                   // colocamos titulo a la ventana
            
            contenedorTop.removeAll();
            contenedorMiddle.removeAll();
            contenedorBottom.removeAll();
            contenedorTop.setLayout(new GridLayout(1,2));
            contenedorTop.add(jLabel10);
            contenedorTop.add(jTextField10);
            contenedorMiddle.setLayout(new GridLayout(1,2));
            contenedorMiddle.add(jLabel11);
            contenedorMiddle.add(select1);
            //contenedorBottom.setLayout(new GridLayout(1,1));
            //contenedorBottom.add(jButton10);

            v.setLayout(new GridLayout(3,1));
            v.add(contenedorTop);
            v.add(contenedorMiddle);
            v.add(contenedorBottom);
            
            contenedorBottom.setLayout(new GridLayout(1,1));
            contenedorBottom.add(jButton10);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (evt.getSource() == jButton4){
            v.setVisible(true);
            v.setTitle("Operación cerradura +");
            
            contenedorTop.removeAll();
            contenedorMiddle.removeAll();
            contenedorBottom.removeAll();
            contenedorTop.setLayout(new GridLayout(1,2));
            contenedorTop.add(jLabel13, BorderLayout.WEST);
            contenedorTop.add(select1);
            
            v.setLayout(new GridLayout(2,1));
            v.add(contenedorTop);
            v.add(contenedorBottom);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (evt.getSource() == jButton5){
            v.setVisible(true);
            v.setTitle("Operación cerradura *");
            
            contenedorTop.removeAll();
            contenedorMiddle.removeAll();
            contenedorBottom.removeAll();
            contenedorTop.setLayout(new GridLayout(1,2));
            contenedorTop.add(jLabel13, BorderLayout.WEST);
            contenedorTop.add(select1);
            
            v.setLayout(new GridLayout(2,1));
            v.add(contenedorTop);
            v.add(contenedorBottom);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt){
        if (evt.getSource() == jButton10){
            System.out.println("rer");
            v.dispose();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
