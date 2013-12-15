package commandagent

import org.springframework.dao.DataIntegrityViolationException
import commandagent.CommandService

class CommandController {

	CommandService commandService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [commandInstanceList: Command.list(params), commandInstanceTotal: Command.count()]
    }

    def create() {
        [commandInstance: new Command(params)]
    }

    def save() {
        def commandInstance = new Command(params)
        if (!commandInstance.save(flush: true)) {
            render(view: "create", model: [commandInstance: commandInstance])
            return
        }

		def result = commandService.runCommand(commandInstance.getCommand())
		
		render "console:"+result
		//flash.message = message(code: 'default.created.message', args: [message(code: 'command.label', default: 'Command'), commandInstance.id])
		
        //redirect(action: "show", id: commandInstance.id)
    }

    def show() {
        def commandInstance = Command.get(params.id)
        if (!commandInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])
            redirect(action: "list")
            return
        }

        [commandInstance: commandInstance]
    }

    def edit() {
        def commandInstance = Command.get(params.id)
        if (!commandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])
            redirect(action: "list")
            return
        }

        [commandInstance: commandInstance]
    }

    def update() {
        def commandInstance = Command.get(params.id)
        if (!commandInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (commandInstance.version > version) {
                commandInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'command.label', default: 'Command')] as Object[],
                          "Another user has updated this Command while you were editing")
                render(view: "edit", model: [commandInstance: commandInstance])
                return
            }
        }

        commandInstance.properties = params

        if (!commandInstance.save(flush: true)) {
            render(view: "edit", model: [commandInstance: commandInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'command.label', default: 'Command'), commandInstance.id])
        redirect(action: "show", id: commandInstance.id)
    }

    def delete() {
        def commandInstance = Command.get(params.id)
        if (!commandInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'command.label', default: 'Command'), params.id])
            redirect(action: "list")
            return
        }

        try {
            commandInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'command.label', default: 'Command'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'command.label', default: 'Command'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
