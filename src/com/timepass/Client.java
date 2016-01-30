package com.timepass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private static final int port = 8080;
	
	public static void main(String[] args) {
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

