<%@ page import="com.kkk.neuron.quest.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="question.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${questionInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'content', 'error')} required">
	<label for="content">
		<g:message code="question.content.label" default="Content" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="content" required="" value="${questionInstance?.content}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'periodFrom', 'error')} required">
	<label for="periodFrom">
		<g:message code="question.periodFrom.label" default="Period From" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="periodFrom" precision="day"  value="${questionInstance?.periodFrom}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'periodTo', 'error')} required">
	<label for="periodTo">
		<g:message code="question.periodTo.label" default="Period To" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="periodTo" precision="day"  value="${questionInstance?.periodTo}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'reward', 'error')} required">
	<label for="reward">
		<g:message code="question.reward.label" default="Reward" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="reward" type="number" value="${questionInstance.reward}" required=""/>
</div>

