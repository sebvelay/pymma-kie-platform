
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

glusterFS

*gluster volume set <volume> performance.stat-prefetch off

*gluster volume set <volume> performance.read-ahead off

*gluster volume set <volume> performance.write-behind off

*gluster volume set <volume> performance.readdir-ahead off

*gluster volume set <volume> performance.io-cache off

*gluster volume set <volume> performance.quick-read off

*gluster volume set <volume> performance.open-behind off

*gluster volume set <volume> nfs.trusted-write on

*gluster volume set <volume> nfs.trusted-sync on

*gluster volume set <volume> performance.strict-o-direct on

*gluster volume set <volume> locks.mandatory-locking off