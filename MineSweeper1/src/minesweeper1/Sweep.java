/*
    Hocam yapabildigim kadar yaptim fakat bi yerden sonra birsuru hata vermeye basladi.
    2 gundur onunla ugrasiyorum bu yuzden yarim olarak teslim ediyorum kusura bakmayin.
    127. ve 128. satirda aciklama yaptim.
*/
package minesweeper1;
import javax.swing.JToggleButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Sweep extends javax.swing.JFrame {
    /*
    -2: Acilmis ve bomba yok
    -1: Bomba var
     0: Acilmamis
    1-8: Bomba Numaralari
    */
    final int wid = 9, hei = 9, noBombs = 10;
    JToggleButton[][] blocks = new JToggleButton [hei] [wid];
    int [][] blox = new int [hei] [wid];
    boolean first, oynanabilir;
    
    ActionListener listen = new ActionListener(){
        public void actionPerformed(ActionEvent x){
            int i = 0, j = 0;
            boolean found=false;
            for(i=0; i<hei; i++){
                for(j=0; j<wid; j++){
                    if(x.getSource()==blocks [i][j]){
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if(oynanabilir ){   
                blocks [i][j].setSelected(true);
                if(!first){
                    spawn(i,j);
                    first=true;
                }
                if(blox[i][j] !=-1){
                    open(i,j);
                    revalue();
                }
                else lose();
            }
            else revalue();
        }
    };
    private void won(){
        boolean won = true;
        for(int i = 0; i< hei; i++){
            for(int j = 0; j< wid; j++){
                if(blox[i][j]==0) {
                    won=false;
                    break;
                }
            }
            if(!won) break;
        }
        if(won){
            javax.swing.JOptionPane.showMessageDialog(null, "Kazandin!");
            oynanabilir=false;
        }
    }
        
    
    private void lose(){
        oynanabilir = false;
        for(int i=0; i<hei; i++){
            for(int j=0; j<wid; j++){
                if(blox [i][j]==-1){
                    blocks[i][j].setText("Boom!");
                    blocks[i][j].setSelected(true);
                }
            }
        }
    }
    
    private void open(int b, int a){
        if (b < 0 || a < 0 || a>wid -1 || b>hei-1 || blox [b][a] !=0) return;
        int bombs = 0;
        for (int i = b - 1; i <= b + 1 ; i++) {
            for (int j = a - 1; i <= a + 1 ; j++){
                if (!(j < 0 || i < 0 || j > wid - 1 || i > hei - 1) && blox[i][j] == -1)
                    bombs++;
            }
        }
        if(bombs == 0){
            blox [b][a] = -2;
            for (int i = b - 1; i <= b + 1 ; i++) {
                for (int j = a - 1; i <= a + 1 ; j++){
                    if(!(j < 0 || i < 0 || j > wid-1 || i > hei - 1))
                    if(i !=b || j !=a) open(i,j);
                }
            }    
        }
        else blox [b][a] = bombs;
    }
    
    private void revalue(){
        for(int i=0; i<hei; i++){
            for(int j=0; j<wid; j++){
                if (blox[i][j]==0){
                    blocks[i][j].setText("");
                    blocks[i][j].setSelected(false);
                }
                if(blox[i][j] == -2){
                    blocks[i][j].setText("");
                    blocks[i][j].setSelected(true);
                }
                if(blox[i][j] > 0){
                    blocks[i][j].setText(""+blox[i][j]);
                    blocks[i][j].setSelected(true);
                }
                if (!oynanabilir && blox[i][j] == -1) blocks[i][j].setSelected(true);
            }
        }            
    }
    
    private void spawn(int b, int a){
        for(int k=1; k<= noBombs; k++){
            int i,j;
            do{
                i =(int)(Math.random()*(.01-wid));  
                j =(int)(Math.random()*(.01-hei));
                /*
                    Yukaridaki iki satirdaki (.01-wid) ve (.01-hei) islemini
                    (wid-.01) ve (hei-.01) olarak yazinca nedense oyun donuyor
                    Bu asamaya gelmeden once bomba olan yerler falan duzgunce calisirken bu kodlardan   
                    sonra hata vermeye basladi. Zamanim kalmadigi icin ve zaten hata oldugu icin daha fazla
                    ilerleyemeyip sag tik ile bayrak eklemeyi falan yapamadim.
                */
            }
            while(blox[i][j] == -1 || (i==b && j==a));
                blox[i][j]= -1;
        }
    }

    public Sweep() {
        initComponents();
        for(int i = 0; i< hei; i++){
            for(int j = 0; j< wid; j++){
                blocks [i][j] = new JToggleButton();
                blocks [i][j].setSize(jPanel1.getWidth()/wid, jPanel1.getHeight()/hei);
                jPanel1.add(blocks[i][j]);
                blocks[i][j].setLocation(j*jPanel1.getWidth()/wid, i*jPanel1.getHeight()/hei);
                blocks[i][j].addActionListener(listen);
            }
            first = false;
            oynanabilir = true;
        }
    }
    
    private void resize(){
        for(int i = 0; i< hei; i++){
            for(int j = 0; j< wid; j++){
                blocks [i][j] = new JToggleButton();
                blocks [i][j].setSize(jPanel1.getWidth()/wid, jPanel1.getHeight()/hei);
                jPanel1.add(blocks[i][j]);
                blocks[i][j].setLocation(j*jPanel1.getWidth()/wid, i*jPanel1.getHeight()/hei);
            }
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        
    }//GEN-LAST:event_jPanel1ComponentResized


    public static void main(String args[]) {
    
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sweep().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
