# parts

Простое web-приложение с использованием технологий MySQL, SpringMVC, Hibernate, Bootstrap.

Проект собирается с помощью Maven:
1) mvn package
2) Если запущен Tomcat, то можно сразу задеплоить проект командой mvn clean package tomcat7:redeploy
  (должны присутствовать конфигурации maven settings.xml и tomcat users)
settings.xml (~/.m2/)
      <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
      <localRepository/>
      <interactiveMode/>
      <offline/>
      <pluginGroups/>
      <servers>
		  <server>
				<id>TomcatServer</id>
				<username>admin</username>
				<password>password</password>
			</server>
	  </servers>
      <mirrors/>
      <proxies/>
      <profiles/>
      <activeProfiles/>
    </settings>

tomcat-users
	<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<user username="admin" password="password" roles="manager-gui,manager-script" />
