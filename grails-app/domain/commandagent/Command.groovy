package commandagent

class Command {

	String command;
	String commandParam;
	
    static constraints = {
    	command(inList:["ls -l", 
    		"/mnt/e6/workspace/upgrade.sh", 
    		"/mnt/e6/workspace/nginx_start.sh", 
    		"/mnt/e6/workspace/nginx_stop.sh", 
    		"/mnt/e6/workspace/zip_log.sh",
    		"/mnt/e6/workspace/tomcat_start.sh",
    		"/mnt/e6/workspace/tomcat_stop.sh"
    		])
    }
}
