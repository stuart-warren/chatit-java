/*
 * Copyright (c) 2014. stuart-warren
 * Last modified: 14/08/14 19:54
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.stuartwarren.chatit;

import org.atmosphere.nettosphere.Config;
import org.atmosphere.nettosphere.Nettosphere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by stuart-warren on 13/08/14.
 */
public class ChatMain {

    private static final Logger logger = LoggerFactory.getLogger(Nettosphere.class);

    public static void main(String[] args) throws IOException {

        String classpath = System.getProperty("java.class.path");
        logger.debug("CLASSPATH: {}", classpath);

        Config.Builder configBuilder = new Config.Builder();
        configBuilder.resource(ChatRoomManagedService.class)
                .resource("./src/main/resources")
                .port(8080)
                .host("0.0.0.0")
                .initParam("org.atmosphere.websocket.messageContentType","application/json")
                .initParam("org.atmosphere.websocket.messageMethod", "POST")
                .initParam("com.sun.jersey.api.json.POJOMappingFeature", "true")
                .build();

        Nettosphere server = new Nettosphere.Builder().config(configBuilder.build()).build();
        server.start();
        String input = "";

        logger.info("Nettosphere Jersey Server started on port {}", 8080);
        logger.info("Type 'quit` to stop the server");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!(input.equals("quit"))) {
            input = br.readLine();
        }
        System.exit(-1);
    }
}
