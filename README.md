# SonarQube Custom Metric Plugin
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=jp.co.atware%3Asonar-custom-metric-plugin&metric=alert_status)
![Coverage](https://sonarcloud.io/api/project_badges/measure?project=jp.co.atware%3Asonar-custom-metric-plugin&metric=coverage)
![Bug](https://sonarcloud.io/api/project_badges/measure?project=jp.co.atware%3Asonar-custom-metric-plugin&metric=bugs)


This is a custom plugin for [SonarQube](https://www.sonarqube.org/) - Continuous code quality inspection tool.

This plugin will allow to add custom metrics project to let quality gate checking for it.

## Building
- Clone this repository
- Run `./gradle build` or `.\gradlew build` on Windows
- Find `sonar-custom-metric-plugin-*.*.jar` in `./build/libs`

## Installing
Copy the built `sonar-custom-metric-plugin-*.*.jar` plugin to SonarQube server plugin directory in `<SonarQubeHome>/extensions/plugins/`.
Restart the SonarQube process.

**SonarQube on Docker**
If you are using [Docker compose file](https://github.com/SonarSource/docker-sonarqube/blob/master/recipes.md), copy the plugin to the container volume:

```bash
// sonarqube_sonarqube_1 is container name
docker cp sonar-customer-metric-plugin-0.1.jar sonarqube_sonarqube_1:/opt/sonarqube/extensions/plugins/
docker-compose restart
```

## Defining metrics

The metrics have to be defined so that SonarQube can interpret them. This has to be done in `.\src\main\java\jp\co\atware\sonar\custommetric\CustomMetrics.java`.

This file contains more information on how to define these metrics.

_Example:_

```java

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
            .create(),	

	);
```

Changing metric definitions requires a new version of the plugin, installing it and restarting the SonarQube process.

## Writing custom metrics
Now that we defined the custom metrics, we have to collect and write the according values during the build and/or test process.
These values need to be written into a file in the project file structure. This file uses a `metricId,value` format.

_Example:_
```csv
MemoryUsage,380
MemoryUsagePercent,0.34
Status,OK
```

The values can be various from _int_, _double_ to _string_, depending on the metric definitions in the plugin (see above).

## Transferring custom metrics
Once the metrics file is written, its relative path needs to be transferred to the SonarQube server.
Use the variable name `custom.metrics.reportFilePath`

_Example:_
```
begin
  /d:sonar.host.url="..."
  /d:custom.metrics.reportFilePath="./custom-sonar-metrics.csv"
  ...
```