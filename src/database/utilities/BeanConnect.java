/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.utilities;
import java.sql.*;
/**
 *
 * @author Vince
 */
public class BeanConnect {
    private String typeBD;
    private Connection con;
    private Statement instruc;
    private ResultSet rs;
    
    public BeanConnect()
    {
        typeBD = null;
    }
    /**
     * @return the typeBD
     */
    public String getTypeBD() {
        return typeBD;
    }

    /**
     * @param typeBD the typeBD to set
     */
    public void setTypeBD(String typeBD) {
        this.typeBD = typeBD;
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @return the instruc
     */
    public Statement getInstruc() {
        return instruc;
    }

    /**
     * @param instruc the instruc to set
     */
    public void setInstruc(Statement instruc) {
        this.instruc = instruc;
    }
    
    public int connect()
    {
        Class leDriver;
        if(getTypeBD().equals(null))
            return -1;
        else
        {
            System.out.println("Essai de connexion JDBC");
            try
            {
                if(getTypeBD().equals("Oracle"))
                    leDriver = Class.forName("oracle.jdbc.driver.OracleDriver");
                else
                    leDriver = Class.forName("org.gjt.mm.mysql.Driver");
            }
            catch (ClassNotFoundException e)
            { 
                System.out.println("Driver adéquat non trouvable : " + e.getMessage()); 
            }

            try
            {  
                if(getTypeBD().equals("Oracle"))
                {
                    setCon(DriverManager.getConnection("jdbc:oracle:thin:@192.168.253.138:1521:xe","thib","123"));
                    System.out.println("Connexion avec BD Oracle");
                }
                else
                {
                    setCon(DriverManager.getConnection("jdbc:mysql://192.168.81.132:3306/sys","thib","1234"));
                    System.out.println("Connexion avec mySQL");
                }
                System.out.println("Connexion à la BDD inpres-metal réalisée");
                setInstruc(getCon().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE));
                System.out.println("Création d'une instance d'instruction pour cette connexion");
                rs = instruc.executeQuery("select * from Intervenants");
            }
            catch (SQLException e) { System.out.println("Erreur SQL : " + e.getMessage()); }
            return 0;
        }
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}