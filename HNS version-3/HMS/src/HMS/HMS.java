/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HMS;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author kfard
 */
public final class HMS extends javax.swing.JFrame {

    /**
     * Creates new form HMS
     */
    public HMS() {
        initComponents();
        showDate();
        showTime();
        updateCombooD(); // calling combobox in constructor
        updateCombooP();
        updateCombooT();
    }
       //Date
     void showDate(){
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
        jdate.setText(s.format(d));
        showDate.setText(s.format(d)); // date read from the system
        showDate1.setText(s.format(d));// date read from the system
    }
    //time
    void showTime(){
    new Timer(0, (ActionEvent e) -> {
        Date d=new Date();
        SimpleDateFormat s=new SimpleDateFormat("hh:mm:ss a");
        Jtime.setText(s.format(d));
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }).start();
    }
    //login form
    void LoginCall(String usr, String pswd){
        boolean flag = false;
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from hmslogin where username=\"" + usr +"\" and password=\"" + pswd + "\";";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               flag=true;
            }
                if(flag){
                    JOptionPane.showMessageDialog(null, "Successfully Logged In (*^*):");
                    System.out.print(qrry);
                    jTabbedPane1.setSelectedIndex(1);
                }
                else{
                     JOptionPane.showMessageDialog(null, "Invalid User-Name or Password");
                     System.out.print(qrry);
                }
            
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    //patient resister
    void RegisterCall(String name,String fname,String cnic, String illness,String doctor,String date){
         try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "Insert into patient values("
                         +"'" + name + "',"
                         +"'" + fname + "',"
                         +"'" + cnic + "',"
                         +"'" + illness + "',"
                         +"'" + doctor + "',"
                         +"'" + date + "'"
                         + ")";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Resister Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    //patient info
    void viewPatient(){
        DefaultTableModel model =(DefaultTableModel)tpatient.getModel();
        model.setRowCount(0);
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from patient";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               String Name = rs.getString("name");
               String Father_Name = rs.getString("fatherName");
               String CNIC = rs.getString("CNIC");
               String Illness = rs.getString("illness");
               String Doctor_Name = rs.getString("doctor");
               String date = rs.getString("date");
               
               model.addRow(new Object[]{Name,Father_Name,CNIC,Illness,Doctor_Name,date});
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
        }
    }
    // create new appoinment with doctor
    void doctorBooking(String doctor,String time,String fee, String paymentOption,String cnic,String date){
        try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "Insert into doctorBooking values("
                         +"'" + doctor + "',"
                         +"'" + time + "',"
                         +"'" + fee + "',"
                         +"'" + paymentOption + "',"
                         +"'" + cnic + "',"
                         +"'" + date + "'"
                         + ")";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Resister Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }

    }
    // search patient appoinment with doctor
    void querry(String cnic){
        DefaultTableModel model =(DefaultTableModel)tcnic.getModel();
        model.setRowCount(0);
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from doctorBooking where CNIC=\"" + cnic +"\";";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               String dctName = rs.getString("doctorName");
               String time = rs.getString("time");
               String fee = rs.getString("fee");
               String paymentOption = rs.getString("paymentOption");
               String CNIC = rs.getString("CNIC");
               String date = rs.getString("date");
               
               model.addRow(new Object[]{dctName,time,fee,paymentOption,CNIC,date});
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
        }
    }
    // resister new admin
    void newAdmin(String usr,String pswd){
        
         try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "Insert into hmslogin values("
                         +"'" + usr + "',"
                         +"'" + pswd + "'"
                         + ")";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Resister Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
        
    }
    // insert doctor info 
    void addDoctor(String name,String mail,String phone,String roomNO,String qulf,String din,String activeHour){
        try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "Insert into doctor values("
                         +"'" + name + "',"
                         +"'" + mail + "',"
                         +"'" + phone + "',"
                         +"'" + roomNO + "',"
                         +"'" + qulf + "',"
                         +"'" + din + "',"
                         +"'" + activeHour + "'"
                         + ")";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Resister Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    //show doctor info
    void doctorDetails(){
        DefaultTableModel model =(DefaultTableModel)doctorTable.getModel();
        model.setRowCount(0);
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from doctor";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               String name = rs.getString("name");
               String mail = rs.getString("mail");
               String phoneNO = rs.getString("phoneNO");
               String roomNO = rs.getString("roomNO");
               String Qualification = rs.getString("qualification");
               String din = rs.getString("din");
               String acthour = rs.getString("activeHour");
               
               model.addRow(new Object[]{name, mail,phoneNO,roomNO,Qualification,din,acthour});
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
        }
    }
    //delete doctor info 
    void deleteDoctor(String din){
         try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "DELETE FROM doctor WHERE din = "
                         +"'" + din + "'"
                         + ";";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Deletation Complete Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    // delete admin 
    void deleteAdmin(String usr){
        try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "DELETE FROM hmslogin WHERE username = "
                         +"'" + usr + "'"
                         + ";";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Deletation Complete Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    // delete patinet info
    void deletePatient(String cnic){
         try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "DELETE FROM patient WHERE cnic = "
                         +"'" + cnic + "'"
                         + ";";
                 
                 stmt.executeUpdate(qrry);
                 String qrry1 = "DELETE FROM doctorBooking WHERE cnic = "
                         +"'" + cnic + "'"
                         + ";";
                 
                 stmt.executeUpdate(qrry1);
             }
            JOptionPane.showMessageDialog(null,"Deletation Complete Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    // resister new emergency number 
    void addEmergency(String type,String number){
        try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "Insert into emergency values("
                         +"'" + type + "',"
                         +"'" + number + "'"
                         + ")";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Resister Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    // delete emergency number
    void delEmergency(String name){
        try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "DELETE FROM emergency WHERE Type = "
                         +"'" + name + "'"
                         + ";";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Deletation Complete Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    // show emergency number
    void showEmergency(){
        DefaultTableModel model =(DefaultTableModel)showEmr.getModel();
        model.setRowCount(0);
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from emergency";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               String Type = rs.getString("Type");
               String number = rs.getString("number");
               
               model.addRow(new Object[]{Type,number});
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
        }
    }
    // add stuff info 
    void addWorker(String name,String workType, String contact,String salary ,String activeHour){
         try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "Insert into worker values("
                         +"'" + name + "',"
                         +"'" + workType + "',"
                         +"'" + contact + "',"
                         +"'" + salary + "',"
                         +"'" + activeHour + "'"
                         + ")";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Resister Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    // remove staff info 
    void removeWorker(String cnt){
        try{
            Class.forName("java.sql.Driver");
             try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4"); Statement stmt = conn.createStatement()) {
                 String qrry = "DELETE FROM worker WHERE contact = "
                         +"'" + cnt + "'"
                         + ";";
                 
                 stmt.executeUpdate(qrry);
             }
            JOptionPane.showMessageDialog(null,"Deletation Complete Complete");
        }catch(HeadlessException | ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null,"Error in Connectivity "+ex);
        }
    }
    // show staff info 
    void showWorker(){
        DefaultTableModel model =(DefaultTableModel)tworker.getModel();
        model.setRowCount(0);
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from worker";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               String Name = rs.getString("name");
               String work_type = rs.getString("work_type");
               String contact = rs.getString("contact");
               String salary = rs.getString("salary");
               String active_hour = rs.getString("active_hour");
               
               model.addRow(new Object[]{Name,work_type,contact,salary,active_hour});
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
        }
    }
    void updateCombooD(){ // update value datebase to combobox
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from doctor";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               pickDoctor.addItem(rs.getString("name"));
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
        }
    }
    void updateCombooP(){ // update value datebase to combobox
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from doctor";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               pickDoctor1.addItem(rs.getString("name"));
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
        }
    }
    void updateCombooT(){ // update value datebase to combobox
        try{
            Class.forName("java.sql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hms","root","alphacoders4T4");
            Statement stmt = conn.createStatement();
            String qrry = "select * from doctor";
            ResultSet rs = stmt.executeQuery(qrry);
            while(rs.next()){
               comboTime.addItem(rs.getString("activeHour"));
            }
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(rootPane,"Error in Fetching Data ");
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

        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        adminUsr = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        signOut = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnPatientFrm = new javax.swing.JButton();
        btnDoctorFrm = new javax.swing.JButton();
        pview = new javax.swing.JButton();
        querry = new javax.swing.JButton();
        newAdmin = new javax.swing.JButton();
        doctorInfo = new javax.swing.JButton();
        staffInfo = new javax.swing.JButton();
        emergencyContact = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Jtime = new javax.swing.JLabel();
        jdate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pname = new javax.swing.JTextField();
        pfatherName = new javax.swing.JTextField();
        pcnic = new javax.swing.JTextField();
        pillness = new javax.swing.JTextField();
        pdoctor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        psubmit = new javax.swing.JButton();
        MENU1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        pickDoctor = new javax.swing.JComboBox<>();
        showDate = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpatient = new javax.swing.JTable();
        MENU2 = new javax.swing.JButton();
        menu3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        dtime = new javax.swing.JTextField();
        dfee = new javax.swing.JTextField();
        dfeeMethod = new javax.swing.JTextField();
        dname = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dcnicNo = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        dsubmit = new javax.swing.JButton();
        menu4 = new javax.swing.JButton();
        pickDoctor1 = new javax.swing.JComboBox<>();
        comboTime = new javax.swing.JComboBox<>();
        cpayment = new javax.swing.JComboBox<>();
        showDate1 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        inputCnic = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cnicQry = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tcnic = new javax.swing.JTable();
        menu6 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        adminName = new javax.swing.JTextField();
        adminpswd = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        resister = new javax.swing.JButton();
        menu12 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        admindelete = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        doctorAdd = new javax.swing.JButton();
        doctorView = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        doctorDelete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        doctorName = new javax.swing.JTextField();
        doctorMail = new javax.swing.JTextField();
        doctorPhone = new javax.swing.JTextField();
        doctorRoom = new javax.swing.JTextField();
        qualification = new javax.swing.JTextField();
        DIN = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        activeHour = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        doctorSubmit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        croom = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        doctorTable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        dinSearch = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        delteDin = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        dlt = new javax.swing.JButton();
        del = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        emgNumber = new javax.swing.JTextField();
        emgName = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        delEmer = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        delEmergency = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        showEmr = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tworker = new javax.swing.JTable();
        wn = new javax.swing.JTextField();
        ww = new javax.swing.JTextField();
        ws = new javax.swing.JTextField();
        wa = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        delcnt = new javax.swing.JTextField();
        wc = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        adminUsr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminUsrActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("USER NAME");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("PASSWORD");

        login.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/sign-in.png"))); // NOI18N
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        signOut.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        signOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/exit-door.png"))); // NOI18N
        signOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutActionPerformed(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/icon.jpg"))); // NOI18N
        jLabel22.setText("HMS ");

        jLabel21.setText("AUST-CSE-44");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(adminUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(signOut)
                        .addGap(55, 55, 55))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(login)
                    .addComponent(jLabel21))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(adminUsr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(login)
                    .addComponent(signOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 789));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("WELCOME TO HOSPITAL MANAGMENT SYSTEM");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Admin Login Panel");

        jLabel36.setBackground(new java.awt.Color(153, 153, 255));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/login.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(239, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(197, 197, 197))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(332, 332, 332))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("tab1", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu-1.png"))); // NOI18N
        jLabel5.setText("Menu Page");

        btnPatientFrm.setBackground(new java.awt.Color(255, 255, 255));
        btnPatientFrm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPatientFrm.setForeground(new java.awt.Color(204, 0, 51));
        btnPatientFrm.setText("GO TO PATIENT FORM");
        btnPatientFrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatientFrmActionPerformed(evt);
            }
        });

        btnDoctorFrm.setBackground(new java.awt.Color(255, 255, 255));
        btnDoctorFrm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDoctorFrm.setForeground(new java.awt.Color(204, 0, 51));
        btnDoctorFrm.setText("DOCTOR AVAILABILITY");
        btnDoctorFrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctorFrmActionPerformed(evt);
            }
        });

        pview.setBackground(new java.awt.Color(255, 255, 255));
        pview.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pview.setForeground(new java.awt.Color(204, 0, 51));
        pview.setText("VIEW PATIENT INFO");
        pview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pviewActionPerformed(evt);
            }
        });

        querry.setBackground(new java.awt.Color(255, 255, 255));
        querry.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        querry.setForeground(new java.awt.Color(204, 0, 51));
        querry.setText("APPOINTMENT QUERY");
        querry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                querryActionPerformed(evt);
            }
        });

        newAdmin.setBackground(new java.awt.Color(255, 255, 255));
        newAdmin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newAdmin.setForeground(new java.awt.Color(204, 0, 51));
        newAdmin.setText("ADMIN");
        newAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAdminActionPerformed(evt);
            }
        });

        doctorInfo.setBackground(new java.awt.Color(255, 255, 255));
        doctorInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doctorInfo.setForeground(new java.awt.Color(204, 0, 51));
        doctorInfo.setText("DOCTOR INFO");
        doctorInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorInfoActionPerformed(evt);
            }
        });

        staffInfo.setBackground(new java.awt.Color(255, 255, 255));
        staffInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        staffInfo.setForeground(new java.awt.Color(204, 0, 51));
        staffInfo.setText("STAFF INFO");
        staffInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffInfoActionPerformed(evt);
            }
        });

        emergencyContact.setBackground(new java.awt.Color(255, 255, 255));
        emergencyContact.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        emergencyContact.setForeground(new java.awt.Color(204, 0, 51));
        emergencyContact.setText("EMERGENCY  P.");
        emergencyContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emergencyContactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(querry, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pview, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoctorFrm, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(doctorInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(emergencyContact)
                            .addGap(18, 18, 18)
                            .addComponent(staffInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(btnPatientFrm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorInfo)
                    .addComponent(staffInfo)
                    .addComponent(emergencyContact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPatientFrm)
                .addGap(30, 30, 30)
                .addComponent(btnDoctorFrm)
                .addGap(18, 18, 18)
                .addComponent(pview)
                .addGap(32, 32, 32)
                .addComponent(querry)
                .addGap(32, 32, 32)
                .addComponent(newAdmin)
                .addContainerGap(301, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 255, 204));

        Jtime.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        Jtime.setText("Time:");

        jdate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jdate.setText("Date:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setText("PATIENT FORM");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Patient Name");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Father Name");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("CNIC Number");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Patient Illness");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Choose Doctor");

        psubmit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        psubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/add.png"))); // NOI18N
        psubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                psubmitActionPerformed(evt);
            }
        });

        MENU1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        MENU1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        MENU1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MENU1ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/delete-1.png"))); // NOI18N
        jButton6.setText("DELETATION");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        pickDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickDoctorActionPerformed(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel49.setText("Date");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(MENU1)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                        .addComponent(Jtime)
                        .addGap(106, 106, 106)
                        .addComponent(jdate)
                        .addGap(138, 138, 138))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(42, 42, 42)
                        .addComponent(pickDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel49))
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(psubmit)
                    .addComponent(pname, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(pfatherName, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(pcnic, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(pillness, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(pdoctor, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(showDate))
                .addGap(153, 153, 153))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MENU1)
                            .addComponent(jButton6))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Jtime)
                            .addComponent(jdate)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel6)))
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(34, 34, 34)
                                        .addComponent(pfatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(34, 34, 34)
                                .addComponent(pcnic, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(34, 34, 34)
                        .addComponent(pillness, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pdoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(pickDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(psubmit)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel4);

        tpatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Father Name", "CNIC", "Illness", "Doctor Name", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tpatient);

        MENU2.setText("MENU");

        menu3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        menu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        menu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menu3))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(MENU2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(MENU2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("tab4", jPanel5);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel12.setText("DOCTOR AVAILABILITY");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Time:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Appointment Fee: ");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Payement Option:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Doctor Name:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("CNIC NO:");

        dsubmit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dsubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/add.png"))); // NOI18N
        dsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dsubmitActionPerformed(evt);
            }
        });

        menu4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        menu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        menu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu4ActionPerformed(evt);
            }
        });

        pickDoctor1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickDoctor1ActionPerformed(evt);
            }
        });

        comboTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTimeActionPerformed(evt);
            }
        });

        cpayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Bkash", "Visa" }));
        cpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpaymentActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel37.setText("Date:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                .addComponent(menu4))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dsubmit)
                .addGap(420, 420, 420))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addComponent(cpayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dfee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                                    .addComponent(dfeeMethod, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dcnicNo)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pickDoctor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtime)
                                    .addComponent(dname)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel12))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menu4)))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(pickDoctor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dtime, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(dfee, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dfeeMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(cpayment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcnicNo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(32, 32, 32)
                .addComponent(dsubmit)
                .addContainerGap(349, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanel6);

        inputCnic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCnicActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("CNIC NO:");

        cnicQry.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cnicQry.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/loupe.png"))); // NOI18N
        cnicQry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cnicQryActionPerformed(evt);
            }
        });

        tcnic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Doctor Name", "Time", "Fee", "Payment Method", "CNIC", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tcnic);

        menu6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        menu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        menu6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jLabel18)
                .addGap(111, 111, 111)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cnicQry)
                    .addComponent(inputCnic, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(382, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menu6))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu6)
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(inputCnic, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cnicQry)
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel7);

        adminName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminNameActionPerformed(evt);
            }
        });

        adminpswd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminpswdActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("USER NAME");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("PASSWORD");

        resister.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        resister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/add.png"))); // NOI18N
        resister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resisterActionPerformed(evt);
            }
        });

        menu12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        menu12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        menu12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu12ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setText("DELETATION");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setText("ADD");

        admindelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admindeleteActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setText("USER NAME:");

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/delete.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(menu12))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(270, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel19)
                        .addComponent(jLabel20))
                    .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(110, 110, 110)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(admindelete, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(adminName)
                        .addComponent(adminpswd, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                    .addComponent(resister))
                .addGap(248, 248, 248))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel33)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(menu12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(adminName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(adminpswd, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(52, 52, 52)
                        .addComponent(resister)
                        .addGap(52, 52, 52)
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(admindelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46)
                .addComponent(jButton5)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab7", jPanel8);

        doctorAdd.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        doctorAdd.setText("ADD");
        doctorAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorAddActionPerformed(evt);
            }
        });

        doctorView.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        doctorView.setText("VIEW");
        doctorView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorViewActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/icon2.jpg"))); // NOI18N

        doctorDelete.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        doctorDelete.setText("Delete");
        doctorDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorDeleteActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(284, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(doctorAdd)
                        .addGap(115, 115, 115)
                        .addComponent(doctorView)
                        .addGap(103, 103, 103)
                        .addComponent(doctorDelete))
                    .addComponent(jLabel24))
                .addGap(250, 250, 250))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(39, 39, 39)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorAdd)
                    .addComponent(doctorView)
                    .addComponent(doctorDelete))
                .addContainerGap(519, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab8", jPanel9);

        doctorName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorNameActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Full Name:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setText("E-mail:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setText("Phone No:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("Room NO:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("Qualilfication:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setText("Doctor Identity No:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("Active Hour:");

        doctorSubmit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        doctorSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/add.png"))); // NOI18N
        doctorSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorSubmitActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        croom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1A", "1B", "1C", "2A", "2B", "2C", "3A", "3B", "3C", "4A", "4B", "4C" }));
        croom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                croomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(66, 66, 66)
                                .addComponent(croom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(103, 103, 103)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(activeHour, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(doctorName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(doctorMail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(doctorPhone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(doctorRoom, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qualification, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DIN, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(228, 228, 228))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(doctorSubmit)
                        .addGap(312, 312, 312))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(107, 107, 107)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(doctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23))
                                        .addGap(18, 18, 18)
                                        .addComponent(doctorMail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel25))
                                .addGap(18, 18, 18)
                                .addComponent(doctorPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addComponent(doctorRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(croom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(qualification, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DIN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(activeHour, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(38, 38, 38)
                .addComponent(doctorSubmit)
                .addContainerGap(297, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab9", jPanel10);

        doctorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Mail", "Phone NO", "Room NO", "Qualification", "DIN", "Active Hour"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(doctorTable);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/back-button.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton3))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab10", jPanel11);

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setText("DIN:");

        delteDin.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        delteDin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/delete.png"))); // NOI18N
        delteDin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delteDinActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/back-button.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel31)
                .addGap(89, 89, 89)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delteDin)
                    .addComponent(dinSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(400, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addGap(39, 39, 39)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dinSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(30, 30, 30)
                .addComponent(delteDin)
                .addContainerGap(668, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab11", jPanel12);

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setText("CNIC NO:");

        dlt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dlt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/delete.png"))); // NOI18N
        dlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dltActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/back-button.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton7))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jLabel35)
                .addGap(78, 78, 78)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dlt)
                    .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(397, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addGap(68, 68, 68)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(del, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(dlt)
                .addContainerGap(639, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab12", jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton8.setText("VIEW");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("jButton9");

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/emergency.png"))); // NOI18N

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton10.setText("ADD");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton12.setText("REMOVE");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 122, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addGap(262, 262, 262))))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addComponent(jButton8)
                .addGap(117, 117, 117)
                .addComponent(jButton10)
                .addGap(88, 88, 88)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton11)
                .addGap(39, 39, 39)
                .addComponent(jLabel38)
                .addGap(48, 48, 48)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton10)
                    .addComponent(jButton12))
                .addGap(379, 379, 379)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab13", jPanel14);

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setText("Name:");

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel40.setText("Number:");

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/back-button.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/add.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton13))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14)
                    .addComponent(emgNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emgName, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(250, 250, 250))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13)
                .addGap(158, 158, 158)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39)
                    .addComponent(emgName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emgNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(18, 18, 18)
                .addComponent(jButton14)
                .addContainerGap(489, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab14", jPanel15);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setText("Name:");

        delEmergency.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        delEmergency.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/delete.png"))); // NOI18N
        delEmergency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delEmergencyActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/back-button.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(371, Short.MAX_VALUE)
                .addComponent(jLabel41)
                .addGap(73, 73, 73)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delEmergency)
                    .addComponent(delEmer, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(235, 235, 235))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton15))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton15)
                .addGap(180, 180, 180)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(delEmer, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(delEmergency)
                .addContainerGap(516, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab15", jPanel16);

        showEmr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Type", "Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(showEmr);

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/back-button.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton16))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(402, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab16", jPanel17);

        tworker.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Work-Type", "Contact", "Salary", "Active Hour"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tworker);

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setText("Name:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel43.setText("Work-Type:");

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setText("Salary:");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel45.setText("Active Hour:");

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel46.setText("Remove A Worker:");

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel47.setText("Contact Number:");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel48.setText("Contact Number:");

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/menu.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/delete.png"))); // NOI18N
        jButton18.setToolTipText("");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HMS/add.png"))); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton17))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel48)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton18)
                                    .addComponent(delcnt, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jButton19)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(wc, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(ww, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                        .addComponent(wn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel45)
                                            .addComponent(jLabel44))
                                        .addGap(23, 23, 23)))
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ws, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                    .addComponent(wa))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton17)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(ws, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(wn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel42)
                                            .addComponent(jLabel44))))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ww, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel43))
                                    .addComponent(wa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel45)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(wc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel47)))
                .addGap(12, 12, 12)
                .addComponent(jButton19)
                .addGap(35, 35, 35)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel48)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(36, 36, 36)
                                .addComponent(delcnt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addComponent(jButton18))))
        );

        jTabbedPane1.addTab("tab17", jPanel18);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, -34, 960, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        //LoginCall(adminUsr.getText(),adminPswd.getText());
        LoginCall(adminUsr.getText(),pass.getText());
        adminUsr.setText(null);
        pass.setText(null);       // set text panel empty
        
    }//GEN-LAST:event_loginActionPerformed

    private void signOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutActionPerformed
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_signOutActionPerformed

    private void psubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_psubmitActionPerformed
        // TODO add your handling code here:
        RegisterCall(pname.getText(),pfatherName.getText(),pcnic.getText(),pillness.getText(),pdoctor.getText(),showDate.getText());
        pname.setText(null);
        pfatherName.setText(null);
        pcnic.setText(null);
        pillness.setText(null);     // set text box empty
        pdoctor.setText(null);
        
    }//GEN-LAST:event_psubmitActionPerformed

    private void pviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pviewActionPerformed
        // TODO add your handling code here:
        viewPatient();
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_pviewActionPerformed

    private void dsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dsubmitActionPerformed
        // TODO add your handling code here:
        doctorBooking(dname.getText(),dtime.getText(),dfee.getText(),dfeeMethod.getText(),dcnicNo.getText(),showDate1.getText());
        dname.setText(null);
        dtime.setText(null);
        dfee.setText(null);
        dfeeMethod.setText(null);    // set text box null
        dcnicNo.setText(null);
        
    }//GEN-LAST:event_dsubmitActionPerformed

    private void inputCnicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputCnicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCnicActionPerformed

    private void cnicQryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cnicQryActionPerformed
        // TODO add your handling code here:
        querry(inputCnic.getText());
        inputCnic.setText(null);
    }//GEN-LAST:event_cnicQryActionPerformed

    private void querryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_querryActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_querryActionPerformed

    private void btnPatientFrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPatientFrmActionPerformed
        // TODO add your handling code here:
        //updateCombooT();
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_btnPatientFrmActionPerformed

    private void menu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        DefaultTableModel model =(DefaultTableModel)tcnic.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_menu6ActionPerformed

    private void menu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_menu4ActionPerformed

    private void menu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
        DefaultTableModel model =(DefaultTableModel)tpatient.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_menu3ActionPerformed

    private void MENU1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MENU1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_MENU1ActionPerformed

    private void btnDoctorFrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoctorFrmActionPerformed
           // TODO add your handling code here:
          jTabbedPane1.setSelectedIndex(4);
         
    }//GEN-LAST:event_btnDoctorFrmActionPerformed

    private void adminNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminNameActionPerformed

    private void adminpswdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminpswdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminpswdActionPerformed

    private void resisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resisterActionPerformed
        // TODO add your handling code here:
        newAdmin(adminName.getText(),adminpswd.getText());
        adminName.setText(null);
        adminpswd.setText(null);
    }//GEN-LAST:event_resisterActionPerformed

    private void menu12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu12ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_menu12ActionPerformed

    private void newAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAdminActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_newAdminActionPerformed

    private void doctorInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorInfoActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_doctorInfoActionPerformed

    private void doctorAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorAddActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(8);
    }//GEN-LAST:event_doctorAddActionPerformed

    private void doctorSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorSubmitActionPerformed
        // TODO add your handling code here:
        addDoctor(doctorName.getText(),doctorMail.getText(),doctorPhone.getText(),doctorRoom.getText(),qualification.getText(),DIN.getText(),activeHour.getText());
        doctorName.setText(null);
        doctorMail.setText(null);
        doctorPhone.setText(null);
        doctorRoom.setText(null);                      // set tex box empty
        qualification.setText(null);
        DIN.setText(null);
        activeHour.setText(null);
    }//GEN-LAST:event_doctorSubmitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(7);
        DefaultTableModel model =(DefaultTableModel)doctorTable.getModel();
        model.setRowCount(0);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void doctorViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorViewActionPerformed
        // TODO add your handling code here:
        
        doctorDetails();
        jTabbedPane1.setSelectedIndex(9);
    }//GEN-LAST:event_doctorViewActionPerformed

    private void delteDinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delteDinActionPerformed
        // TODO add your handling code here:
        deleteDoctor(dinSearch.getText());
    }//GEN-LAST:event_delteDinActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(7);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void doctorDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorDeleteActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(10);
    }//GEN-LAST:event_doctorDeleteActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        deleteAdmin(admindelete.getText());

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(11);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void dltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dltActionPerformed
        // TODO add your handling code here:
        deletePatient(del.getText());
        del.setText(null); // set text box empty
    }//GEN-LAST:event_dltActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void emergencyContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emergencyContactActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(12);
    }//GEN-LAST:event_emergencyContactActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(12);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        addEmergency(emgName.getText(),emgNumber.getText());
        emgName.setText(null);
        emgNumber.setText(null); // set text box empty
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(13);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(12);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void delEmergencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delEmergencyActionPerformed
        // TODO add your handling code here:
        delEmergency(delEmer.getText());
        delEmer.setText(null);
    }//GEN-LAST:event_delEmergencyActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(14);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
         jTabbedPane1.setSelectedIndex(12);
         DefaultTableModel model =(DefaultTableModel)showEmr.getModel();
         model.setRowCount(0);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model =(DefaultTableModel)showEmr.getModel();
        model.setRowCount(0);
        jTabbedPane1.setSelectedIndex(15);
        showEmergency();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void staffInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffInfoActionPerformed
        // TODO add your handling code here:
        showWorker();
        jTabbedPane1.setSelectedIndex(16);
    }//GEN-LAST:event_staffInfoActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
         
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        removeWorker(delcnt.getText());
        DefaultTableModel model =(DefaultTableModel)tworker.getModel();
        model.setRowCount(0);
        showWorker();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        addWorker(wn.getText(),ww.getText(),wc.getText(),ws.getText(),wa.getText());
        DefaultTableModel model =(DefaultTableModel)tworker.getModel();
        model.setRowCount(0);
        showWorker();
        wn.setText(null);
        ww.setText(null);
        wc.setText(null);
        ws.setText(null);
        wa.setText(null);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void adminUsrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminUsrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminUsrActionPerformed

    private void doctorNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorNameActionPerformed

    private void admindeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admindeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_admindeleteActionPerformed

    private void pickDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickDoctorActionPerformed
        // TODO add your handling code here:
        String selectedValue= pickDoctor.getSelectedItem().toString();
        pdoctor.setText(selectedValue);
    }//GEN-LAST:event_pickDoctorActionPerformed

    private void pickDoctor1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickDoctor1ActionPerformed
        // TODO add your handling code here:
        String selectedValue= pickDoctor1.getSelectedItem().toString();
        dname.setText(selectedValue);
    }//GEN-LAST:event_pickDoctor1ActionPerformed

    private void comboTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTimeActionPerformed
        // TODO add your handling code here:
        String selectedValue= comboTime.getSelectedItem().toString();
        dtime.setText(selectedValue);
    }//GEN-LAST:event_comboTimeActionPerformed

    private void cpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpaymentActionPerformed
        // TODO add your handling code here:
        //dfeeMethod
        String selectedValue= cpayment.getSelectedItem().toString();
        dfeeMethod.setText(selectedValue);
    }//GEN-LAST:event_cpaymentActionPerformed

    private void croomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_croomActionPerformed
        // TODO add your handling code here:
        String selectedValue= croom.getSelectedItem().toString();
        doctorRoom.setText(selectedValue);
    }//GEN-LAST:event_croomActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new HMS().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DIN;
    private javax.swing.JLabel Jtime;
    private javax.swing.JButton MENU1;
    private javax.swing.JButton MENU2;
    private javax.swing.JTextField activeHour;
    private javax.swing.JTextField adminName;
    private javax.swing.JTextField adminUsr;
    private javax.swing.JTextField admindelete;
    private javax.swing.JTextField adminpswd;
    private javax.swing.JButton btnDoctorFrm;
    private javax.swing.JButton btnPatientFrm;
    private javax.swing.JButton cnicQry;
    private javax.swing.JComboBox<String> comboTime;
    private javax.swing.JComboBox<String> cpayment;
    private javax.swing.JComboBox<String> croom;
    private javax.swing.JTextField dcnicNo;
    private javax.swing.JTextField del;
    private javax.swing.JTextField delEmer;
    private javax.swing.JButton delEmergency;
    private javax.swing.JTextField delcnt;
    private javax.swing.JButton delteDin;
    private javax.swing.JTextField dfee;
    private javax.swing.JTextField dfeeMethod;
    private javax.swing.JTextField dinSearch;
    private javax.swing.JButton dlt;
    private javax.swing.JTextField dname;
    private javax.swing.JButton doctorAdd;
    private javax.swing.JButton doctorDelete;
    private javax.swing.JButton doctorInfo;
    private javax.swing.JTextField doctorMail;
    private javax.swing.JTextField doctorName;
    private javax.swing.JTextField doctorPhone;
    private javax.swing.JTextField doctorRoom;
    private javax.swing.JButton doctorSubmit;
    private javax.swing.JTable doctorTable;
    private javax.swing.JButton doctorView;
    private javax.swing.JButton dsubmit;
    private javax.swing.JTextField dtime;
    private javax.swing.JButton emergencyContact;
    private javax.swing.JTextField emgName;
    private javax.swing.JTextField emgNumber;
    private javax.swing.JTextField inputCnic;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jdate;
    private javax.swing.JButton login;
    private javax.swing.JButton menu12;
    private javax.swing.JButton menu3;
    private javax.swing.JButton menu4;
    private javax.swing.JButton menu6;
    private javax.swing.JButton newAdmin;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField pcnic;
    private javax.swing.JTextField pdoctor;
    private javax.swing.JTextField pfatherName;
    private javax.swing.JComboBox<String> pickDoctor;
    private javax.swing.JComboBox<String> pickDoctor1;
    private javax.swing.JTextField pillness;
    private javax.swing.JTextField pname;
    private javax.swing.JButton psubmit;
    private javax.swing.JButton pview;
    private javax.swing.JTextField qualification;
    private javax.swing.JButton querry;
    private javax.swing.JButton resister;
    private javax.swing.JTextField showDate;
    private javax.swing.JTextField showDate1;
    private javax.swing.JTable showEmr;
    private javax.swing.JButton signOut;
    private javax.swing.JButton staffInfo;
    private javax.swing.JTable tcnic;
    private javax.swing.JTable tpatient;
    private javax.swing.JTable tworker;
    private javax.swing.JTextField wa;
    private javax.swing.JTextField wc;
    private javax.swing.JTextField wn;
    private javax.swing.JTextField ws;
    private javax.swing.JTextField ww;
    // End of variables declaration//GEN-END:variables
}
