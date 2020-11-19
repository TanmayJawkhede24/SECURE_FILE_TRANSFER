



import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import java.io.InputStream;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
public class SEND implements Serializable {
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getcipherText() {
        return cipherText;
    }
    

    public void setcipherText(String cipherText) {
        this.cipherText = cipherText;
    }
    
   public PublicKey getPublicKey() {
        return pubkey;
    }
   public void setSenderAesKey(String senderAesKey) {
       this.senderAesKey = senderAesKey;
   }
   
  public String getsenderAesKey() {
       return senderAesKey;
   }

    public void setPublicKey(PublicKey pubkey) {
        this.pubkey = pubkey;
    }
    
    public PrivateKey getPrivateKey() {
        return prikey;
    }
    
    public void setAESKey(SecretKey aesKey) {
        this.aesKey = aesKey;
    }
    
    public SecretKey getAESKey() {
        return aesKey;
    }
    
    public void setPrivateKey(PrivateKey prikey) {
        this.prikey = prikey;
    }
    
    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setpassCode(String passCode) {
        this.passCode = passCode;
    }
   public String getPasscode() {
        return passCode;
    }
   
   public void setStatus(String status) {
       this.status = status;
   }
  public String toString() {
       return name;
   }
    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
    public ArrayList <String> getActiveRcvr () {
        return list;
    }

    public void setActiveRcvr(ArrayList <String> list) {
        this.list = list;
    }

    private String status;
   
    private byte[] file;
    private String passCode;
    
    private String name;
    private String ip;
    
    private String cipherText;
    private String fileName;
    
    private SecretKey aesKey;
    
    private String senderAesKey;
    
    private PrivateKey prikey;
    private PublicKey pubkey;
    private  ArrayList <String> list ;
    
    
}
