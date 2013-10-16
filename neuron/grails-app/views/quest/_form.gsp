<%@ page import="com.kkk.neuron.quest.Quest" %>



<div class="fieldcontain ${hasErrors(bean: questInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="quest.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${questInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="quest.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="content" required="" value="${questInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questInstance, field: 'periodFrom', 'error')} required">
	<label for="periodFrom">
		<g:message code="quest.periodFrom.label" default="Period From" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="periodFrom" precision="day"  value="${questInstance?.periodFrom}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: questInstance, field: 'periodTo', 'error')} required">
	<label for="periodTo">
		<g:message code="quest.periodTo.label" default="Period To" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="periodTo" precision="day"  value="${questInstance?.periodTo}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: questInstance, field: 'reward', 'error')} required">
	<label for="reward">
		<g:message code="quest.reward.label" default="Reward" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="reward" type="number" value="${questInstance.reward}" required=""/>
</div>

