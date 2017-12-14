import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.net.Socket;

public class CliantSocket{
    public static void main(String[] args){
    	byte[] buf = new byte[1024];
	byte[] size_buf = new byte[8];
	long size;
	int len;
	String file_name = new String("input.jpg");
	try{
	    FileInputStream input = new FileInputStream(file_name);
	    File file = new File(file_name);
            Socket sock = new Socket("localhost", 11451);
            BufferedOutputStream out = new BufferedOutputStream(sock.getOutputStream());
	    size = file.length();
	    for(int i=0;i<8;i++){ 
	    	buf[i] = (byte)( (size >> 56 - 8*i) & 0xFF);
	    }
	    out.write(buf,0,8);
	    while((len=input.read(buf)) != -1){
	    	out.write(buf,0,len);
	    }
	    out.close();
	    sock.close();
	}catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
