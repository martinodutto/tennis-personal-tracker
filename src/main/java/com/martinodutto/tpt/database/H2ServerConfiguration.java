package com.martinodutto.tpt.database;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile("production")
public class H2ServerConfiguration {

    @Value("${db.port}")
    private String h2TcpPort;

    /**
     * TCP connection to connect with SQL clients to the embedded H2 database.
     *
     * @see Server
     * @throws SQLException If something went wrong during startup of the server.
     * @return H2 db Server.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", h2TcpPort);
    }
}
