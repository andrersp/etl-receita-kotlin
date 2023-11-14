FROM amazoncorretto:17-alpine3.17 as build-image

WORKDIR /app
COPY . .
# Build the application.
RUN ./gradlew shadowJar -x test

FROM amazoncorretto:17-alpine3.17
RUN apk update && apk add --no-cache postgresql-client

RUN echo "America/Sao_Paulo" >  /etc/timezone
ENV TZ America/Sao_Paulo
ENV LANG pt_BR.UTF-8
ENV LANGUAGE pt_BR.UTF-8
ENV LC_ALL pt_BR.UTF-8
COPY ./sql ./sql


ENV DISABLE_JAVADUMP true
ENV IBM_NOSIGHANDLER true

COPY --from=build-image /app/build/libs/receita-0.1-all.jar app.jar
ENTRYPOINT java -jar app.jar