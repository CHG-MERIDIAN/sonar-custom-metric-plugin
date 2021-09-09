# SonarQube Custom Metric Plugin
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=jp.co.atware%3Asonar-custom-metric-plugin&metric=alert_status)
![Coverage](https://sonarcloud.io/api/project_badges/measure?project=jp.co.atware%3Asonar-custom-metric-plugin&metric=coverage)
![Bug](https://sonarcloud.io/api/project_badges/measure?project=jp.co.atware%3Asonar-custom-metric-plugin&metric=bugs)


This is a custom plugin for [SonarQube](https://www.sonarqube.org/) - Continuous code quality inspection tool.

This plugin will allow to add custom metrics project to let quality gate checking for it.

## Building
- Clone this repository
- Run `.\gradlew build`
- `sonar-custom-metric-plugin-*.*.jar` is written to `.\build\libs`

## Installing
Copy the built `sonar-custom-metric-plugin-*.*.jar` plugin to SonarQube server plugin directory in `<SonarQube home>/extensions/plugins/`.
Restart the SonarQube server

**SonarQube on Docker**
If you are using [Docker compose file](https://github.com/SonarSource/docker-sonarqube/blob/master/recipes.md), copy the plugin to the container volume:

```bash
// sonarqube_sonarqube_1 is container name
docker cp sonar-customer-metric-plugin-0.1.jar sonarqube_sonarqube_1:/opt/sonarqube/extensions/plugins/
docker-compose restart
```

## Custom metric file format
The custom metric we define need to be written down somewhere in the project. The file will have a CSV format

```csv
metricId,value
myMetricInt1,1
myMetricStr2,good
```

the `metricId` should be defined in this plugin. `value` could be various from _int_, _double_ to _string_. It will be parsed depend on the value type of metric we defined in this plugin.
