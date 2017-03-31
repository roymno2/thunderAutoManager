package com.nerv.xlab.base;

public class winProcessControl {
	private cmdExecute cmdExecuter= new cmdExecute();
	public winProcessControl () {
		
	}
    public Boolean stopService (String serviceName) {
    	String liveProcess=(String) cmdExecuter.runCmd("sc stop "+serviceName).get("returnCode");
    	if (liveProcess.equals("0")) {
    		return true;
    	} else {
    		return false;
    	}
    }
    public Boolean stopProcess (String processName) {
       	String liveProcess=(String) cmdExecuter.runCmd("TASKKILL /F /T /IM "+processName).get("returnCode");
    	if (liveProcess.equals("0")) {
    		return true;
    	} else {
    		return false;
    	}
    }
    public Boolean checkServiceRun (String serviceName) {
    	String[] liveService=((String) cmdExecuter.runCmd("net start").get("output")).split("\n");
        for (int i = 0 ; i <liveService.length ; i++ ) {
        	if (liveService[i].trim().equals(serviceName) == true) {
        		return true;
        	}
        }
        return false;
    }
    public Boolean checkProcessRun(String processName) {
    	String[] liveProcess=((String) cmdExecuter.runCmd("TASKLIST /FI \"IMAGENAME eq "+processName+"\" /NH").get("output")).split("\n");
        for (int i = 0 ; i <liveProcess.length ; i++ ) {
        	if (liveProcess[i].startsWith(processName)) {
        		return true;
        	}
        }
        return false;
    }
}