GitHub-Explorer

If you haven maven installed just type 'mvn spring-boot:run' in the command line from the root folder of the project.
Then to test the application just the use the following endpoint:
http://localhost:8080/commits?gitHubUrl='GIT_HUB_URL'

Where 'GIT_HUB_URL' is the url of the repository from which you want the commits. 

Example:
http://localhost:8080/commits?gitHubUrl=https://github.com/Crydust/TokenReplacer.git