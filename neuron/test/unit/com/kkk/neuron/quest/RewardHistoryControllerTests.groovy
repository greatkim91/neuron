package com.kkk.neuron.quest



import org.junit.*
import grails.test.mixin.*

@TestFor(RewardHistoryController)
@Mock(RewardHistory)
class RewardHistoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/rewardHistory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.rewardHistoryInstanceList.size() == 0
        assert model.rewardHistoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.rewardHistoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.rewardHistoryInstance != null
        assert view == '/rewardHistory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/rewardHistory/show/1'
        assert controller.flash.message != null
        assert RewardHistory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/rewardHistory/list'

        populateValidParams(params)
        def rewardHistory = new RewardHistory(params)

        assert rewardHistory.save() != null

        params.id = rewardHistory.id

        def model = controller.show()

        assert model.rewardHistoryInstance == rewardHistory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/rewardHistory/list'

        populateValidParams(params)
        def rewardHistory = new RewardHistory(params)

        assert rewardHistory.save() != null

        params.id = rewardHistory.id

        def model = controller.edit()

        assert model.rewardHistoryInstance == rewardHistory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/rewardHistory/list'

        response.reset()

        populateValidParams(params)
        def rewardHistory = new RewardHistory(params)

        assert rewardHistory.save() != null

        // test invalid parameters in update
        params.id = rewardHistory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/rewardHistory/edit"
        assert model.rewardHistoryInstance != null

        rewardHistory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/rewardHistory/show/$rewardHistory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        rewardHistory.clearErrors()

        populateValidParams(params)
        params.id = rewardHistory.id
        params.version = -1
        controller.update()

        assert view == "/rewardHistory/edit"
        assert model.rewardHistoryInstance != null
        assert model.rewardHistoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/rewardHistory/list'

        response.reset()

        populateValidParams(params)
        def rewardHistory = new RewardHistory(params)

        assert rewardHistory.save() != null
        assert RewardHistory.count() == 1

        params.id = rewardHistory.id

        controller.delete()

        assert RewardHistory.count() == 0
        assert RewardHistory.get(rewardHistory.id) == null
        assert response.redirectedUrl == '/rewardHistory/list'
    }
}
