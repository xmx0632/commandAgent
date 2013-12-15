package commandagent

class CommandService {

    def runCommand(String command) {
    	try{
	    	StringBuilder sb = new StringBuilder()
	    	
	    	def sout = new StringBuffer()
			def serr = new StringBuffer() 
			def process = command.execute()
			
			process.consumeProcessOutput(sout, serr)
			
			process.waitFor()
			if (process.exitValue()){
				println "exec err"
			    print process.err.text
				return "exec command $command error"
			} 
			
			def result = sout.toString()
			println "result:\n$result"
			def returnValue = result.replaceAll("\n","<br>")
			return returnValue;
    	}catch(Exception e){
			println "execute command error,exception:$e.message" 
			return "exec command $command error:$e.message"
    	}

    }
}
