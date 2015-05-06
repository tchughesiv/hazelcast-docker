# file run.sh
#!/bin/sh
export CATALINA_OPTS="$CATALINA_OPTS -Dmember1=$member1 -Dmember2=$member2 -Dpublic_ip=$public_ip"
catalina.sh run
