import java.io.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.sql.*;
import java.util.Map;
import java.util.List;
import java.lang.String;
import java.util.ArrayList;
import java.lang.StringBuffer;
import javax.servlet.*;
import javax.servlet.http.*;
 
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import java.sql.ResultSet;
import javax.security.auth.spi.LoginModule;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

 
/**
 * Login module that simply matches name and password to perform authentication.
 * If successful, set principal to name and credential to "admin".
 *
 * @author Nicolas Fränkel
 * @since 2 avr. 2009
 */
public class PlainLoginModule implements LoginModule{
 
    /** Callback handler to store between initialization and authentication. */
    private CallbackHandler handler;
 
    /** Subject to store. */
    private Subject subject;

    /** Login name. */
    private String login;
    
    /** Role names **/
    private String [] roles=new String[1];
    /**
     * This implementation always return false.
     *
     * @see javax.security.auth.spi.LoginModule#abort()
     */
    @Override
    public boolean abort() throws LoginException {
        return false;
    }
 
    /**
     * This is where, should the entire authentication process succeeds,
     * principal would be set.
     *
     * @see javax.security.auth.spi.LoginModule#commit()
     */
    @Override
    public boolean commit() throws LoginException {
        String s="";
        try {
 
            PlainUserPrincipal user = new PlainUserPrincipal(login);

            subject.getPrincipals().add(user);
            for(String i : roles){
                PlainRolePrincipal aux = new PlainRolePrincipal(i);
                subject.getPrincipals().add(aux);
                s=i;
            }
 
            return true;
 
        } catch (Exception e) {
 
            throw new LoginException(s);
        }
    }
 
    /**
     * This implementation ignores both state and options.
     *
     * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject,
     *      javax.security.auth.callback.CallbackHandler, java.util.Map,
     *      java.util.Map)
     */
    @Override
    public void initialize(Subject aSubject, CallbackHandler aCallbackHandler, Map aSharedState, Map aOptions) {
        handler = aCallbackHandler;
        subject = aSubject;
    }

    /** 
    * Devuelve el password digerido en SHA-256
    */
    public String getPasswordDigest(String password) throws NoSuchAlgorithmException{

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte [] digestBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < digestBytes.length; i++) {
                String hex = Integer.toHexString(0xff & digestBytes[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        }catch(NoSuchAlgorithmException e){
            throw new  NoSuchAlgorithmException("Passwrod error");
        }
    }
 
    /**
     * This method checks whether the name and the password are the same.
     *
     * @see javax.security.auth.spi.LoginModule#login()
     */
    @Override
    public boolean login() throws LoginException{
        
        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);
 
        try {
            
            handler.handle(callbacks);
            
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1]).getPassword());
            String s1="";
            try{  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/employee","root","Minesister11@");  
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from user");
                while(rs.next())
                {
                    String name1=rs.getString(1);
                    String role1=rs.getString(2);
                    if(name1.equals(name))
                    {
                        ResultSet rs1=stmt.executeQuery("select * from authentication");
                        while(rs1.next())
                        {
                            String name2=rs1.getString(1);
                            String password1=rs1.getString(2);
                            if(name2.equals(name)&&password1.equals(password))
                            {
                                roles[0]=role1;
                                login=name;
                                return true;
                            }
                        }
                    }
                }        
            }catch(Exception e){
                    throw new LoginException("Authentication Failed");
            }

        }catch (IOException e) {
            throw new LoginException("Hello World");
        } catch (UnsupportedCallbackException e) {
 
            throw new LoginException("First Program");
        }

        return false;
    }
 
    /**
     * Clears subject from principal and credentials.
     *
     * @see javax.security.auth.spi.LoginModule#logout()
     */
    @Override
    public boolean logout() throws LoginException {
 
        try {
 
            PlainUserPrincipal user = new PlainUserPrincipal(login);
            subject.getPrincipals().remove(user);

             for(String i : roles){
                PlainRolePrincipal aux = new PlainRolePrincipal(i);
                subject.getPrincipals().remove(aux);
            }
 
            return true;
 
        } catch (Exception e) {
 
            throw new LoginException("Error Logout");
        }
    }
}
