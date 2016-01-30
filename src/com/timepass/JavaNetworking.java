package com.timepass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class JavaNetworking{
	public static void main(String[] args) {
		Thread t1 = new Thread(new Server());
		Thread t2 = new Thread(new Server());
		t1.start();
		t2.start();
	}
}
class Server implements Runnable{
	private volatile Boolean flag = true;  
	private static final int port = 580;
	@Override
	public void run() {
		if(flag){
			flag = false;
			ServerSocket serverSocket = null;
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(port);
				socket = serverSocket.accept();
				BufferedReader bReader = new BufferedReader(
											new InputStreamReader(
													socket.getInputStream()));
				String currentLine = null;
				while((currentLine = bReader.readLine()) != null){
					System.out.println(currentLine);
				}
				
				PrintWriter pWriter = new PrintWriter(
										new BufferedWriter(
											new OutputStreamWriter(
													socket.getOutputStream())));
				pWriter.println("Sdduuuuuuu");
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
					socket.close();
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
		} else{
			Socket client = null;
			try {
				client = new Socket("localhost", port);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								client.getInputStream()));
				String curLine = null;
				while((curLine = br.readLine()) != null){
					System.out.println(curLine);
				}
				
				PrintWriter pr = new PrintWriter(
									new BufferedWriter(
										new OutputStreamWriter(
												client.getOutputStream())));
				pr.println("Gouthuuuu");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
