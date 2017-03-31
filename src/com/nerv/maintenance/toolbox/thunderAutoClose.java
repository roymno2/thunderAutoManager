package com.nerv.maintenance.toolbox;

import com.nerv.xlab.base.winProcessControl;

public class thunderAutoClose
{
	private winProcessControl processController= new winProcessControl();
    public static void main(String[] args)
    {
    	thunderAutoClose thunderCloser= new thunderAutoClose();
    	
    	if (args.length >= 1) {
	    	thunderCloser.debugmode(args[0]);
    	} else {
    		thunderCloser.closeThunderService();
    	}
    	
    	
    	
    }
    
    public thunderAutoClose() {
    }
    public void debugmode (String cmdName) {
    	System.out.println(processController.checkProcessRun(cmdName));
    }
    

    public void closeThunderService() {
    	while (true) {
	    	if (processController.checkProcessRun("Thunder.exe")==false && (processController.checkProcessRun("ThunderPlatform.exe") == true || processController.checkServiceRun("XLServicePlatform")==true)) {
	    		try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    		if (processController.checkProcessRun("Thunder.exe")==false) {
		    		processController.stopService("XLServicePlatform");
		    		processController.stopProcess("ThunderPlatform.exe");
		    		System.out.println("auto close thunder");
	    		}
	    	}
    		try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }
}