package jp.co.atware.sonar.custommetric;

import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.Metric;
import org.sonar.api.measures.Metrics;

import java.util.Arrays;
import java.util.List;

/**
Define custom metrics here

	ValueType
		BOOL 
		DATA 
		DISTRIB 
		FLOAT 
		INT 
		LEVEL 
		MILLISEC 
		PERCENT 
		RATING 
		STRING 
		WORK_DUR
	Direction
		DIRECTION_BETTER: A metric bigger value means an improvement
		DIRECTION_NONE: The metric direction has no meaning
		DIRECTION_WORST: A metric bigger value means a degradation
	Domain
		DOMAIN_COMPLEXITY	 
		DOMAIN_COVERAGE	 
		DOMAIN_DUPLICATIONS	 
		DOMAIN_GENERAL	 
		DOMAIN_ISSUES	 
		DOMAIN_MAINTAINABILITY	
		DOMAIN_RELEASABILITY	 
		DOMAIN_RELIABILITY	
		DOMAIN_SCM	 
		DOMAIN_SECURITY	
		DOMAIN_SECURITY_REVIEW	 
		DOMAIN_SIZE
 */
public class CustomMetrics implements Metrics {

	static final List<Metric> METRICS = Arrays.asList(

		new Metric.Builder(
            "MemoryUsage", "Memory usage", Metric.ValueType.INT)
            .setDirection(Metric.DIRECTION_WORST)
            .setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_RELIABILITY)
            .create(),

		new Metric.Builder(
            "MemoryUsagePercent", "Memory usage (in percent)", Metric.ValueType.FLOAT)
            .setDirection(Metric.DIRECTION_WORST)
            .setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_RELIABILITY)
            .create(),	

		new Metric.Builder(
            "Status", "Api status response", Metric.ValueType.STRING)
            .setDirection(Metric.DIRECTION_NONE)
            .setQualitative(true)
            .setDomain(CoreMetrics.DOMAIN_RELIABILITY)
            .create()
	);

	@Override
	public List<Metric> getMetrics() {
		return METRICS;
	}
}