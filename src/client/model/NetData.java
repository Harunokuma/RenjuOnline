package client.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class NetData {
	private static NetData instance = null;
	
	private PrintStream printStream;
	private BufferedReader bufferedReader;
	
	private NetData(){
	}
	
	public static NetData getInstance()
	{
		if(instance == null)
			instance = new NetData();
		return instance;
	}
	
	public PrintStream getPrintStream()
	{
		return printStream;
	}
	
	public BufferedReader getBufferedReader()
	{
		return bufferedReader;
	}
	
	public void setPrintStream(OutputStream outputStream)
	{
		printStream = new PrintStream(outputStream);
	}
	
	public void setBufferedReader(InputStreamReader iStreamReader)
	{
		bufferedReader = new BufferedReader(iStreamReader);
	}
}
