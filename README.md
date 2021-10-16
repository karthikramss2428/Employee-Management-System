#Simple JAAS implementation

Use with Apache + Tomcat 8.

You need to create a "jaas.config" file in your Apache /conf directory with the following content:

`
	IDwebappLogin{
    		PlainLoginModule required debug=true;
	};
`

Next you need to create a "setenv.sh" file in your Apache /bin directory whit the following content:

`
	export JAVA_OPTS=-Djava.security.auth.login.config==$CATALINA_BASE/conf/jaas.config
`

Place the project in your Apache /webapps directory. 
