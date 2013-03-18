/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.soitoolkit.commons.module.logger;

import java.util.Map;

import javax.inject.Inject;

import org.mule.RequestContext;
import org.mule.api.MuleEvent;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.param.Optional;
import org.soitoolkit.commons.logentry.schema.v1.LogLevelType;
import org.soitoolkit.commons.module.logger.api.EventLogger;
import org.soitoolkit.commons.module.logger.impl.DefaultEventLogger;
import org.springframework.stereotype.Component;

/**
 * soi-toolkit module for logging
 * 
 * @author Magnus Larsson
 */
@SuppressWarnings("deprecation")
@Component
@Module(name="soitoolkitlogger", schemaVersion="0.5.1-SNAPSHOT")
public class SoitoolkitLoggerModule {

	/*
	 * TODO:
	 * - updat mvn-enforcer till 1.1.1 * 2 pga http://jira.codehaus.org/browse/MENFORCER-117 och module-logger 
	 * - ta bort beroende till slf4j och se till att den (3 jar-filer) inte paketeras med i zip-fil eller update-site
	 * - ta bort beroende till commons-mule, kopiera in kod helt enkelt...
	 * - ta bort pa
	 * + separata metoder för varje loggnivå
	 * - DI fallback i getters måste vara att default impl instansieras...
	 * - classpath scanning måste med i alla projekt...
	 * - dekl av defaulteventlogger måste oxå med???
	 * - impl detaljer...
	 *   - få bort håtdkodningar i form av könamn och connectorer, flytta ut till config-element med bra defaultväden, typ properties i property fil...
	 * + busContext, behövs det eller räcker det med extraInfo + correlationId
	 * - behövs busCorrId som namn eller kan vi bara kalla det corrId och defaulta det till mule's?
	 * - bättre namn i mule studio
	 * - ikoner...
	 * - explicita set*Beans på config-element?
	 *
	 */

	/**
     * Configurable
     */
    @Configurable
    private String myProperty;

    /**
     * Set property
     *
     * @param myProperty My property
     */
    public void setMyProperty(String myProperty)
    {
        this.myProperty = myProperty;
    }
    

    /*
     * Dependencies
     */
    private EventLogger eventLogger;

    @Inject
    public void setEventLogger(EventLogger eventLogger) {
    	this.eventLogger = eventLogger;
    }
    
	protected EventLogger getEventLogger() {
    	if (eventLogger == null) {
    		// Fallback if classpath-scanning is missing, eg: <context:component-scan base-package="org.soitoolkit.commons.module.logger" />
    		eventLogger = new DefaultEventLogger();
    	}
    	return eventLogger;
    }

    /**
     * Log processor for level TRACE
     *
     * {@sample.xml ../../../doc/SoitoolkitLogger-connector.xml.sample soitoolkitlogger:log}
     *
     * @param message Log-message to be processed
	 * @param integrationScenario Optional name of the integration scenario or business process
	 * @param contractId Optional name of the contract in use
	 * @param correlationId Optional correlation identity of the message
     * @param extra Optional extra info
     * @return The incoming payload
     */ 
    @Processor
    public Object logTrace(
    	String message, 
    	@Optional String integrationScenario, 
    	@Optional String contractId, 
    	@Optional String correlationId,
    	@Optional Map<String, String> extra) {

    	return doLog(LogLevelType.TRACE, message, integrationScenario, contractId, correlationId, extra);
    }

    /**
     * Log processor for level DEBUG
     *
     * {@sample.xml ../../../doc/SoitoolkitLogger-connector.xml.sample soitoolkitlogger:log}
     *
     * @param message Log-message to be processed
	 * @param integrationScenario Optional name of the integration scenario or business process
	 * @param contractId Optional name of the contract in use
	 * @param correlationId Optional correlation identity of the message
     * @param extra Optional extra info
     * @return The incoming payload
     */ 
    @Processor
    public Object logDebug(
    	String message, 
    	@Optional String integrationScenario, 
    	@Optional String contractId, 
    	@Optional String correlationId,
    	@Optional Map<String, String> extra) {

    	return doLog(LogLevelType.DEBUG, message, integrationScenario, contractId, correlationId, extra);
    }

    /**
     * Log processor for level INFO
     *
     * {@sample.xml ../../../doc/SoitoolkitLogger-connector.xml.sample soitoolkitlogger:log}
     *
     * @param message Log-message to be processed
	 * @param integrationScenario Optional name of the integration scenario or business process
	 * @param contractId Optional name of the contract in use
	 * @param correlationId Optional correlation identity of the message
     * @param extra Optional extra info
     * @return The incoming payload
     */ 
    @Processor
    public Object logInfo(
    	String message, 
    	@Optional String integrationScenario, 
    	@Optional String contractId, 
    	@Optional String correlationId,
    	@Optional Map<String, String> extra) {

    	return doLog(LogLevelType.INFO, message, integrationScenario, contractId, correlationId, extra);
    }

    /**
     * Log processor for level WARNING
     *
     * {@sample.xml ../../../doc/SoitoolkitLogger-connector.xml.sample soitoolkitlogger:log}
     *
     * @param message Log-message to be processed
	 * @param integrationScenario Optional name of the integration scenario or business process
	 * @param contractId Optional name of the contract in use
	 * @param correlationId Optional correlation identity of the message
     * @param extra Optional extra info
     * @return The incoming payload
     */ 
    @Processor
    public Object logWarning(
    	String message, 
    	@Optional String integrationScenario, 
    	@Optional String contractId, 
    	@Optional String correlationId,
    	@Optional Map<String, String> extra) {

    	return doLog(LogLevelType.WARNING, message, integrationScenario, contractId, correlationId, extra);
    }

    /**
     * Log processor for level ERROR
     *
     * {@sample.xml ../../../doc/SoitoolkitLogger-connector.xml.sample soitoolkitlogger:log}
     *
     * @param message Log-message to be processed
	 * @param integrationScenario Optional name of the integration scenario or business process
	 * @param contractId Optional name of the contract in use
	 * @param correlationId Optional correlation identity of the message
     * @param extra Optional extra info
     * @return The incoming payload
     */ 
    @Processor
    public Object logError(
    	String message, 
    	@Optional String integrationScenario, 
    	@Optional String contractId, 
    	@Optional String correlationId,
    	@Optional Map<String, String> extra) {

    	return doLog(LogLevelType.ERROR, message, integrationScenario, contractId, correlationId, extra);
    }

    protected Object doLog(
    	LogLevelType level,
    	String message, 
    	String integrationScenario, 
    	String contractId, 
    	String correlationId,
    	Map<String, String> extra) {

    	// Get the MuleEvent from the RequestContent instead of having payload and headers injected in method call.
    	// Injecting the payload will cause an evaluation of the expression [#payload] on every call, so it will be a performance killer...
    	// MuleEvent also includes a lot of information that we can't get injected
		MuleEvent muleEvent = RequestContext.getEvent();

    	getEventLogger().logEvent(muleEvent, message, level, integrationScenario, contractId, correlationId, extra);

    	return muleEvent.getMessage().getPayload();
    }
}