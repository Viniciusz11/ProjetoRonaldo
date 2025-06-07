# Use uma imagem oficial do OpenJDK 11 JRE como imagem base
FROM openjdk:11-jre-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR executável gerado pelo Maven para dentro do contêiner
# Certifique-se de que o nome do JAR corresponde ao gerado pelo seu build (verifique a pasta target/)
# O nome padrão baseado no seu pom.xml é cliente-0.0.1-SNAPSHOT.jar
COPY target/cliente-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta em que a aplicação roda (o padrão para Spring Boot é 8080)
EXPOSE 8080

# Especifique o comando para rodar a aplicação quando o contêiner iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
