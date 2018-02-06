//package com.athongkun.utils;
//
//import org.activiti.engine.HistoryService;
//import org.activiti.engine.ProcessEngine;
//import org.activiti.engine.RepositoryService;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.repository.Deployment;
//import org.activiti.engine.repository.ProcessDefinition;
//import org.activiti.engine.repository.ProcessDefinitionQuery;
//import org.activiti.engine.runtime.ProcessInstance;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class ActUtil {
//	
//	public static ProcessInstance startProcessInstanceByPdkey( String key ) {
//		ProcessDefinitionQuery query =
//			getProcessDefinitionQuery();
//		ProcessDefinition pd = 
//			query
//			    .processDefinitionKey(key)
//			    .latestVersion()
//			    .singleResult();
//		RuntimeService runtimeService = getRuntimeService();
//		
//		ProcessInstance pi = 
//				runtimeService.startProcessInstanceById(pd.getId());
//			
//		return pi;
//	}
//	
//	public static Deployment deploy( String fileName ) {
//		Deployment deployment = 
//			getRepositoryService()
//			    .createDeployment()
//			    .addClasspathResource(fileName)
//			    .deploy();
//		return deployment;
//	}
//	
//	public static ProcessDefinition getLatestVersion4PD( String key ) {
//		ProcessDefinition pd = 
//				getRepositoryService()
//				    .createProcessDefinitionQuery()
//				    .processDefinitionKey(key)
//				    .latestVersion()
//				    .singleResult();
//		return pd;
//	}
//	
//	public static ProcessEngine getProcessEngine() {
//		ApplicationContext context =
//				new ClassPathXmlApplicationContext("spring/spring-*.xml");
//			
//		ProcessEngine processEngine = (ProcessEngine)context.getBean("processEngine");
//		
//		return processEngine;
//	}
//	
//	public static RepositoryService getRepositoryService() {
//		ProcessEngine pe = getProcessEngine();
//		RepositoryService repositoryService = pe.getRepositoryService();
//		return repositoryService;
//	}
//	
//	public static RuntimeService getRuntimeService() {
//		ProcessEngine pe = getProcessEngine();
//		RuntimeService runtimeService = pe.getRuntimeService();
//		return runtimeService;
//	}
//	
//	public static TaskService getTaskService() {
//		ProcessEngine pe = getProcessEngine();
//		TaskService taskService = pe.getTaskService();
//		return taskService;
//	}
//	
//	public static HistoryService getHistoryService() {
//		ProcessEngine pe = getProcessEngine();
//		HistoryService historyService = pe.getHistoryService();
//		return historyService;
//	}
//	
//	public static ProcessDefinitionQuery getProcessDefinitionQuery() {
//		return getRepositoryService().createProcessDefinitionQuery();
//	}
//}
