package com.jinson.controllers;/*
jinsonjacob 
5/7/20
*/

import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.IClientConfig;

import java.util.ArrayList;
import java.util.List;

public class HelloServiceConfiguration {

    @Autowired
    IClientConfig ribbonClientConfig;

    @Bean
    public IPing ribbonPing(IClientConfig config) {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return new RoundRobinRule();
    }


    @Bean
    public RetryHandler retryHandler(IClientConfig config) {
        return new DefaultLoadBalancerRetryHandler(config);
    }

    @Bean
    public ServerListSubsetFilter serverListFilter() {
        ServerListSubsetFilter filter = new ServerListSubsetFilter();
        return filter;
    }

    @Bean
    public ServerList<Server> serverList() {
        return new ServerList<Server>() {
            @Override
            public List<Server> getInitialListOfServers() {
                return servers();
            }

            @Override
            public List<Server> getUpdatedListOfServers() {
                return servers();
            }
        };
    }

    private List<Server> servers(){
        List<Server> servers = new ArrayList<>();
        servers.add(new Server("http://localhost:8090"));
        servers.add(new Server("http://localhost:8091"));
        servers.add(new Server("http://localhost:8092"));
        servers.add(new Server("http://localhost:8093"));
        return servers;
    }

}