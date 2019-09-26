
To run the platform, we ecourage you to use the docker containers build with maven
To be able to build the docker container, active the profiles as follow
mvn clean install -Pdev,docker-build

we have a docker-compose file.
As we are using sso, you have in your /etc/hosts to add a line :
YourHostIPnotLocalhost host.docker

then on the root
docker-compose up -d


the workbench will be at the following url : htto://host.docker:8080/kie-wb

We need an ip visible from your browser when used as a callback once identified 

# 
put http://localhost:9200/droolstransaction/_settings
{
  "index.mapping.total_fields.limit": 200000
}

put http://localhost:9200/droolsaction/_settings
{
  "index.mapping.total_fields.limit": 200000
}