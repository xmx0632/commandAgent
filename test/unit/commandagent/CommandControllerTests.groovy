package commandagent



import org.junit.*
import grails.test.mixin.*

@TestFor(CommandController)
@Mock(Command)
class CommandControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/command/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commandInstanceList.size() == 0
        assert model.commandInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.commandInstance != null
    }

    void testSave() {
        controller.save()

        assert model.commandInstance != null
        assert view == '/command/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/command/show/1'
        assert controller.flash.message != null
        assert Command.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/command/list'


        populateValidParams(params)
        def command = new Command(params)

        assert command.save() != null

        params.id = command.id

        def model = controller.show()

        assert model.commandInstance == command
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/command/list'


        populateValidParams(params)
        def command = new Command(params)

        assert command.save() != null

        params.id = command.id

        def model = controller.edit()

        assert model.commandInstance == command
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/command/list'

        response.reset()


        populateValidParams(params)
        def command = new Command(params)

        assert command.save() != null

        // test invalid parameters in update
        params.id = command.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/command/edit"
        assert model.commandInstance != null

        command.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/command/show/$command.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        command.clearErrors()

        populateValidParams(params)
        params.id = command.id
        params.version = -1
        controller.update()

        assert view == "/command/edit"
        assert model.commandInstance != null
        assert model.commandInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/command/list'

        response.reset()

        populateValidParams(params)
        def command = new Command(params)

        assert command.save() != null
        assert Command.count() == 1

        params.id = command.id

        controller.delete()

        assert Command.count() == 0
        assert Command.get(command.id) == null
        assert response.redirectedUrl == '/command/list'
    }
}
