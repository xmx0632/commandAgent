package commandagent

class CommandService {

    def runCommand(String command) {
    	StringBuilder sb = new StringBuilder()
    	
    	def sout = new StringBuffer()
		def serr = new StringBuffer() 
    	//println "command:$command"
		def process = command.execute()
		
		process.consumeProcessOutput(sout, serr)
		
		process.waitFor()
		if (process.exitValue()){
			println "err"
		    print process.err.text
		} else {
		    print process.text
		}
		
		sout.toString()		

		//process.in.eachLine { line -> println line ;sb.append(line).append("\n");}
		//println sb.toString()
    }
}
