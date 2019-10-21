/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.chtijbug.drools.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

// CHECKSTYLE:OFF
@SpringBootApplication
@Configuration
public class DroolsBusinessProxyServer {


    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        if (System.getProperty("org.kie.server.id") == null) {
            InetAddress inetAddress = null;
            try {
                inetAddress = InetAddress.getLocalHost();
                String hostName = inetAddress.getHostName();
                System.setProperty("org.kie.server.id", hostName);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        SpringApplication.run(DroolsBusinessProxyServer.class, args);
    }

}
// CHECKSTYLE:ON
