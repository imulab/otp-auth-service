FROM adoptopenjdk/openjdk11:alpine-jre

ENV JAVA_OPTS="-noverify -Xms64m -Xmx128m -XX:MaxMetaspaceSize=75m -XX:MinMetaspaceFreeRatio=10"

ADD build/libs/otp-auth-service-*.jar app.jar

ENTRYPOINT exec java $JAVA_OPTS -jar app.jar