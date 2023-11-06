/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cobacoba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



/**
 *
 * @author Irkhamnawan
 */
public class Tampilan extends javax.swing.JFrame {

    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(rootPane, pesan);
    }
    ArrayList<BukuPOJO> dataBuku;

    private int simpanData(Connection conn, String isbn, String judul_buku, String tahun_terbit, String penerbit) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("INSERT INTO buku (isbn, judul_buku, tahun_terbit, penerbit) "
                + "VALUES(?, ?, ?, ?)");
        pst.setString(1, isbn);
        pst.setString(2, judul_buku);
        pst.setString(3, tahun_terbit);
        pst.setString(4, penerbit);
        return pst.executeUpdate();
    }
    
    private int editData(Connection conn, String isbn, String judul_buku, String tahun_terbit, String penerbit) throws SQLException {
    PreparedStatement pst = conn.prepareStatement("UPDATE buku SET judul_buku = ?, tahun_terbit = ?, penerbit = ? "
            + "WHERE isbn = ?");
    pst.setString(1, judul_buku);
    pst.setString(2, tahun_terbit);
    pst.setString(3, penerbit);
    pst.setString(4, isbn);
    return pst.executeUpdate();
}
    
    private int hapusData(Connection conn, String isbn) throws SQLException {
    PreparedStatement pst = conn.prepareStatement("DELETE FROM buku "
            + "WHERE isbn = ?");
    pst.setString(1, isbn);
    return pst.executeUpdate();
}



    private void tampilkan(Connection conn) {
        dataBuku.clear();
        try {
            String sql = "SELECT * FROM buku";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BukuPOJO data = new BukuPOJO();
                data.setIsbn(String.valueOf(rs.getObject(1)));
                data.setJudul_buku(String.valueOf(rs.getObject(2)));
                data.setTahun_terbit(String.valueOf(rs.getObject(3)));
                data.setPenerbit(String.valueOf(rs.getObject(4)));
                dataBuku.add(data);
            }
            DefaultTableModel model = (DefaultTableModel) jTableBuku.getModel();
            model.setRowCount(0);
            for (BukuPOJO data : dataBuku) {

                Object[] baris = new Object[4];
                baris[0] = data.getIsbn();
                baris[1] = data.getJudul_buku();
                baris[2] = data.getTahun_terbit();
                baris[3] = data.getPenerbit();

                model.addRow(baris);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Tampilan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Creates new form GUI
     */
    private Timer refreshTimer;
    
    public Tampilan() {
        try {
            dataBuku = new ArrayList<>();
            initComponents();
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/utspbo", "postgres", "1031");
            tampilkan(conn);
            
            refreshTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkan(conn);
            }
        });
        refreshTimer.start();
            
        } catch (SQLException ex) {
            Logger.getLogger(Tampilan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelJudul = new javax.swing.JLabel();
        jLabelISBN = new javax.swing.JLabel();
        jLabelJudulBuku = new javax.swing.JLabel();
        jLabelTahunTerbit = new javax.swing.JLabel();
        jLabelPenerbit = new javax.swing.JLabel();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldJudulBuku = new javax.swing.JTextField();
        jTextFieldTahunTerbit = new javax.swing.JTextField();
        jTextFieldPenerbit = new javax.swing.JTextField();
        jScrollPaneShow = new javax.swing.JScrollPane();
        jTableBuku = new javax.swing.JTable();
        jButtonSimpan = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonCetak = new javax.swing.JButton();
        jButtonKeluar = new javax.swing.JButton();
        jButtonCSV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelJudul.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelJudul.setText("DATA BUKU");

        jLabelISBN.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabelISBN.setText("ISBN");

        jLabelJudulBuku.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabelJudulBuku.setText("Judul Buku");

        jLabelTahunTerbit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabelTahunTerbit.setText("Tahun Terbit");

        jLabelPenerbit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabelPenerbit.setText("Penerbit");

        jTextFieldISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldISBNActionPerformed(evt);
            }
        });

        jTextFieldPenerbit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPenerbitActionPerformed(evt);
            }
        });

        jTableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Judul Buku", "Tahun Terbit", "Penerbit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBukuMouseClicked(evt);
            }
        });
        jScrollPaneShow.setViewportView(jTableBuku);

        jButtonSimpan.setText("Simpan");
        jButtonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimpanActionPerformed(evt);
            }
        });

        jButtonHapus.setText("Hapus");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });

        jButtonEdit.setText("Edit");
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });

        jButtonCetak.setText("Cetak");
        jButtonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCetakActionPerformed(evt);
            }
        });

        jButtonKeluar.setText("Keluar");
        jButtonKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeluarActionPerformed(evt);
            }
        });

        jButtonCSV.setText("Import");
        jButtonCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelISBN)
                                .addComponent(jLabelJudulBuku)
                                .addComponent(jLabelTahunTerbit)
                                .addComponent(jLabelPenerbit))
                            .addComponent(jButtonSimpan))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonHapus)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonEdit))
                            .addComponent(jTextFieldISBN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldJudulBuku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldPenerbit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                .addComponent(jTextFieldTahunTerbit, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButtonCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCetak)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPaneShow, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabelJudul)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabelJudul)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelISBN)
                            .addComponent(jTextFieldISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelJudulBuku)
                            .addComponent(jTextFieldJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTahunTerbit)
                            .addComponent(jTextFieldTahunTerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPenerbit)
                            .addComponent(jTextFieldPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSimpan)
                            .addComponent(jButtonHapus)
                            .addComponent(jButtonEdit))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonCetak)
                            .addComponent(jButtonCSV)))
                    .addComponent(jScrollPaneShow, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPenerbitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPenerbitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPenerbitActionPerformed

    private void jTextFieldISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldISBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldISBNActionPerformed

    private void jButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimpanActionPerformed
        // TODO add your handling code here:
        String isbn = jTextFieldISBN.getText().trim();
        String judulBuku = jTextFieldJudulBuku.getText();
        String tahunTerbit = jTextFieldTahunTerbit.getText().trim();
        String penerbit = jTextFieldPenerbit.getText().trim();
        // awal persistence
        EntityManager entityManager = Persistence.createEntityManagerFactory("cobaaaPU").createEntityManager();
        entityManager.getTransaction().begin();
        Buku z = new Buku();
        z.setIsbn(isbn);
        z.setJudulBuku(judulBuku);
        z.setTahunTerbit(tahunTerbit);
        z.setPenerbit(penerbit);
        
        entityManager.persist(z);
        entityManager.getTransaction().commit();
        
        jTextFieldISBN.setText("");
        jTextFieldJudulBuku.setText("");
        jTextFieldTahunTerbit.setText("");
        jTextFieldPenerbit.setText("");
        
    }//GEN-LAST:event_jButtonSimpanActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
        // TODO add your handling code here:
        String isbn = jTextFieldISBN.getText().trim();

// awal persistence
        EntityManager entityManager = Persistence.createEntityManagerFactory("cobaaaPU").createEntityManager();
        entityManager.getTransaction().begin();

// Cari entitas yang ingin dihapus
        Buku z = entityManager.find(Buku.class, isbn);

        if (z != null) {
            entityManager.remove(z); // Hapus entitas yang ditemukan
            entityManager.getTransaction().commit();
            jTextFieldISBN.setText("");
            jTextFieldJudulBuku.setText("");
            jTextFieldTahunTerbit.setText("");
            jTextFieldPenerbit.setText("");
        } else {
            System.out.println("Data tidak ditemukan"); // Tambahkan penanganan jika data tidak ditemukan
}
        
    }//GEN-LAST:event_jButtonHapusActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed
        // TODO add your handling code here:
    String isbn = jTextFieldISBN.getText().trim();
    String judulBuku = jTextFieldJudulBuku.getText();
    String tahunTerbit = jTextFieldTahunTerbit.getText().trim();
    String penerbit = jTextFieldPenerbit.getText().trim();

// awal persistence
    EntityManager entityManager = Persistence.createEntityManagerFactory("cobaaaPU").createEntityManager();
    entityManager.getTransaction().begin();

    // Ubah kode di bawah ini untuk mencari data yang ingin diupdate
    Buku z = entityManager.find(Buku.class, isbn); // Ganti yourPrimaryKey dengan primary key yang sesuai dengan data yang ingin diupdate
    if (z != null) {
    // Update nilai atribut yang diinginkan
        z.setIsbn(isbn);
        z.setJudulBuku(judulBuku);
        z.setTahunTerbit(tahunTerbit);
        z.setPenerbit(penerbit);

        entityManager.merge(z); // Gunakan merge untuk memperbarui objek

        entityManager.getTransaction().commit();

        jTextFieldISBN.setText("");
        jTextFieldJudulBuku.setText("");
        jTextFieldTahunTerbit.setText("");
        jTextFieldPenerbit.setText("");
    } else {
        System.out.println("Data tidak ditemukan"); // Tambahkan penanganan jika data tidak ditemukan
    }

    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jTableBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBukuMouseClicked
        // TODO add your handling code here:
        try {
        EntityManager entityManager = Persistence.createEntityManagerFactory("cobaaaPU").createEntityManager();
        entityManager.getTransaction().begin();

        int row = jTableBuku.getSelectedRow();
        String tabel_klik = (jTableBuku.getModel().getValueAt(row, 0).toString());

    // Ubah kode di bawah ini untuk mencari data yang diinginkan
    Buku z = entityManager.find(Buku.class, tabel_klik);
    if (z != null) {
        String isbn = z.getIsbn();
        jTextFieldISBN.setText(isbn);
        String judulBuku = z.getJudulbuku();
        jTextFieldJudulBuku.setText(judulBuku);
        String tahunTerbit = z.getTahunterbit();
        jTextFieldTahunTerbit.setText(tahunTerbit);
        String penerbit = z.getPenerbit();
        jTextFieldPenerbit.setText(penerbit);
    }

    entityManager.getTransaction().commit();
} catch (Exception e) {
    // Handle exceptions appropriately
}

        
    }//GEN-LAST:event_jTableBukuMouseClicked

    private void jButtonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonKeluarActionPerformed

    private void jButtonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCetakActionPerformed
        // TODO add your handling code here:
        String reportPath = "src/cobacoba/report7.jrxml";
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cobaaaPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Buku> cq = cb.createQuery (Buku.class);
        Root<Buku> bok = cq.from(Buku.class);
        cq.select(bok);
        
        TypedQuery<Buku> q = em.createQuery(cq);
        List<Buku> list = q.getResultList();
        Query query = em.createQuery("SELECT b FROM Buku b");
        List<Buku> result = query.getResultList();
        
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource (result);
        
        
        try {
            // TODO add your handling code here:
            JasperReport jr = JasperCompileManager.compileReport(reportPath);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, dataSource);
            JasperViewer vw = new JasperViewer (jp, false);
            vw.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        em.getTransaction().commit();
        em.close();
        emf.close();
    


        
    }//GEN-LAST:event_jButtonCetakActionPerformed

    private void jButtonCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCSVActionPerformed
    
        JFileChooser filechooser = new JFileChooser();

                int i = filechooser.showOpenDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {
                    
                    EntityManager entityManager = Persistence.createEntityManagerFactory("cobaaaPU").createEntityManager();
                    entityManager.getTransaction().begin();

                    
                    File f = filechooser.getSelectedFile();
                    String filepath = f.getPath();
                    String fi = f.getName();
                    //Parsing CSV Data
                    System.out.print(filepath);
                    DefaultTableModel csv_data = new DefaultTableModel();

                    try {

                        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filepath));
                        org.apache.commons.csv.CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
                        for (CSVRecord csvRecord : csvParser) {

                        Buku z = new Buku();
                        z.setIsbn(csvRecord.get(0));
                        z.setJudulBuku(csvRecord.get(1));
                        z.setTahunTerbit(csvRecord.get(2));
                        z.setPenerbit(csvRecord.get(3));
                        entityManager.persist(z);
                        
                        }
                        
                    } catch (Exception ex) {
                        System.out.println("Error in Parsing CSV File");
                    }
                    
                    entityManager.getTransaction().commit();
                }
                
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCSVActionPerformed

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
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tampilan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tampilan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCSV;
    private javax.swing.JButton jButtonCetak;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonKeluar;
    private javax.swing.JButton jButtonSimpan;
    private javax.swing.JLabel jLabelISBN;
    private javax.swing.JLabel jLabelJudul;
    private javax.swing.JLabel jLabelJudulBuku;
    private javax.swing.JLabel jLabelPenerbit;
    private javax.swing.JLabel jLabelTahunTerbit;
    private javax.swing.JScrollPane jScrollPaneShow;
    private javax.swing.JTable jTableBuku;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldJudulBuku;
    private javax.swing.JTextField jTextFieldPenerbit;
    private javax.swing.JTextField jTextFieldTahunTerbit;
    // End of variables declaration//GEN-END:variables
}
