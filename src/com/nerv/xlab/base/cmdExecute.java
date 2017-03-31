package com.nerv.xlab.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public class cmdExecute
{
	public cmdExecute () {
	}
	
	public Map<String, String> runCmd (String cmdline) {
		Map<String, String> outVal = new HashMap<String, String>();
		try {
			Process process = Runtime.getRuntime().exec(cmdline);
			InputStream is = process.getInputStream();  
			BufferedReader br = new BufferedReader(new InputStreamReader(is));   
		    String line = null;  
		    StringBuilder sb = new StringBuilder();  
		    while ((line = br.readLine()) != null) {  
		        sb.append(line + "\n");  
		    }
		    try {
				process.waitFor();
			    outVal.put("returnCode", Integer.toString(process.exitValue()));
			    outVal.put("output", sb.toString());
			} catch (InterruptedException e) {
				System.out.println("[Error]"+cmdline);
				e.printStackTrace();
			    outVal.put("returnCode", "1");
			    outVal.put("output", "");
			}  
		    is.close();  
		    br.close();  
		    process.destroy(); 
		    return outVal;  
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[Error]"+cmdline);
		    outVal.put("returnCode", "1");
		    outVal.put("output", e.toString());
			return outVal;
		}
	}
}