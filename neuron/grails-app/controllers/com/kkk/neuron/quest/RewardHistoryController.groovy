package com.kkk.neuron.quest

import grails.plugin.springsecurity.annotation.Secured;

import org.springframework.dao.DataIntegrityViolationException

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class RewardHistoryController {

	def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		def currentUser = springSecurityService.currentUser
		def w = RewardHistory.where {
			reward.owner == currentUser
		}
        [rewardHistoryInstanceList: w.list(params), rewardHistoryInstanceTotal: w.count()]
    }

    def create() {
        [rewardHistoryInstance: new RewardHistory(params)]
    }

    def save() {
        def rewardHistoryInstance = new RewardHistory(params)
        if (!rewardHistoryInstance.save(flush: true)) {
            render(view: "create", model: [rewardHistoryInstance: rewardHistoryInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), rewardHistoryInstance.id])
        redirect(action: "show", id: rewardHistoryInstance.id)
    }

    def show(Long id) {
        def rewardHistoryInstance = RewardHistory.get(id)
        if (!rewardHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), id])
            redirect(action: "list")
            return
        }

        [rewardHistoryInstance: rewardHistoryInstance]
    }

    def edit(Long id) {
        def rewardHistoryInstance = RewardHistory.get(id)
        if (!rewardHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), id])
            redirect(action: "list")
            return
        }

        [rewardHistoryInstance: rewardHistoryInstance]
    }

    def update(Long id, Long version) {
        def rewardHistoryInstance = RewardHistory.get(id)
        if (!rewardHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rewardHistoryInstance.version > version) {
                rewardHistoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rewardHistory.label', default: 'RewardHistory')] as Object[],
                          "Another user has updated this RewardHistory while you were editing")
                render(view: "edit", model: [rewardHistoryInstance: rewardHistoryInstance])
                return
            }
        }

        rewardHistoryInstance.properties = params

        if (!rewardHistoryInstance.save(flush: true)) {
            render(view: "edit", model: [rewardHistoryInstance: rewardHistoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), rewardHistoryInstance.id])
        redirect(action: "show", id: rewardHistoryInstance.id)
    }

    def delete(Long id) {
        def rewardHistoryInstance = RewardHistory.get(id)
        if (!rewardHistoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), id])
            redirect(action: "list")
            return
        }

        try {
            rewardHistoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rewardHistory.label', default: 'RewardHistory'), id])
            redirect(action: "show", id: id)
        }
    }
}
