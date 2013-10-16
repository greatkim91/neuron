package com.kkk.neuron.quest



import org.junit.*
import grails.test.mixin.*

@TestFor(QuestController)
@Mock(Quest)
class QuestControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/question/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.questionInstanceList.size() == 0
        assert model.questionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.questionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.questionInstance != null
        assert view == '/question/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/question/show/1'
        assert controller.flash.message != null
        assert Quest.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        populateValidParams(params)
        def question = new Quest(params)

        assert question.save() != null

        params.id = question.id

        def model = controller.show()

        assert model.questionInstance == question
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        populateValidParams(params)
        def question = new Quest(params)

        assert question.save() != null

        params.id = question.id

        def model = controller.edit()

        assert model.questionInstance == question
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        response.reset()

        populateValidParams(params)
        def question = new Quest(params)

        assert question.save() != null

        // test invalid parameters in update
        params.id = question.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/question/edit"
        assert model.questionInstance != null

        question.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/question/show/$question.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        question.clearErrors()

        populateValidParams(params)
        params.id = question.id
        params.version = -1
        controller.update()

        assert view == "/question/edit"
        assert model.questionInstance != null
        assert model.questionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        response.reset()

        populateValidParams(params)
        def question = new Quest(params)

        assert question.save() != null
        assert Quest.count() == 1

        params.id = question.id

        controller.delete()

        assert Quest.count() == 0
        assert Quest.get(question.id) == null
        assert response.redirectedUrl == '/question/list'
    }
}