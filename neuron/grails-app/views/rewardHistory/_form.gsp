<%@ page import="com.kkk.neuron.quest.RewardHistory" %>



<div class="fieldcontain ${hasErrors(bean: rewardHistoryInstance, field: 'date', 'error')} required">
	<label for="date">
		<g:message code="rewardHistory.date.label" default="Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="date" precision="day"  value="${rewardHistoryInstance?.date}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: rewardHistoryInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="rewardHistory.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" pattern="${rewardHistoryInstance.constraints.type.matches}" required="" value="${rewardHistoryInstance?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rewardHistoryInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="rewardHistory.note.label" default="Note" />
		
	</label>
	<g:textField name="note" value="${rewardHistoryInstance?.note}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rewardHistoryInstance, field: 'amount', 'error')} required">
	<label for="amount">
		<g:message code="rewardHistory.amount.label" default="Amount" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="amount" type="number" value="${rewardHistoryInstance.amount}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: rewardHistoryInstance, field: 'balance', 'error')} required">
	<label for="balance">
		<g:message code="rewardHistory.balance.label" default="Balance" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="balance" type="number" value="${rewardHistoryInstance.balance}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: rewardHistoryInstance, field: 'reward', 'error')} required">
	<label for="reward">
		<g:message code="rewardHistory.reward.label" default="Reward" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="reward" name="reward.id" from="${com.kkk.neuron.quest.Reward.list()}" optionKey="id" required="" value="${rewardHistoryInstance?.reward?.id}" class="many-to-one"/>
</div>

