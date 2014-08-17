/*
 * Copyright (c) 2014. stuart-warren
 * Last modified: 17/08/14 12:06
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

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.atmosphere.config.service.*;
import org.atmosphere.cpr.*;
import org.atmosphere.plugin.hazelcast.HazelcastBroadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by stuart-warren on 17/08/14.
 */
@ManagedService(path = "/chat/{room: [a-zA-Z][a-zA-Z0-9]*}", broadcaster = HazelcastBroadcaster.class)
public class ChatRoomManagedService {

    private final HazelcastInstance hzlcst = Hazelcast.getAllHazelcastInstances().iterator().next();
    private final Logger logger = LoggerFactory.getLogger(ChatRoomManagedService.class);

    @PathParam("room")
    private String chatroomName;

    private ConcurrentMap<String, String> users;
    private BroadcasterFactory broadcasterFactory;
    private AtmosphereResourceFactory resourceFactory;

    @Ready(encoders = {JacksonEncoder.class})
    @DeliverTo(DeliverTo.DELIVER_TO.ALL)
    public ChatProtocol onReady(final AtmosphereResource resource) {
        logger.info("Browser {} connected.", resource.uuid());
        broadcasterFactory = resource.getAtmosphereConfig().getBroadcasterFactory();
        resourceFactory = resource.getAtmosphereConfig().resourcesFactory();
        users = hzlcst.getMap("users-in-room_" + chatroomName);

        return new ChatProtocol(users.keySet(), getRooms(broadcasterFactory.lookupAll()));
    }

    private static Collection<String> getRooms(Collection<Broadcaster> broadcasters) {
        Collection<String> result = new ArrayList<String>();
        for (Broadcaster broadcaster: broadcasters) {
            if (!("/*".equals(broadcaster.getID()))) {
                result.add(broadcaster.getID().split("/")[2]);
            }
        }
        return result;
    }

    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) {
        if (event.isCancelled()) {
            // We didn't get notified, so remove the user
            users.values().remove(event.getResource().uuid());
            logger.warn("Browser {} unexpectedly disconnected.", event.getResource().uuid());
        } else if (event.isClosedByClient()) {
            logger.info("Browser {} closed it's connection.", event.getResource().uuid());
        }
    }

    @Message(encoders = {JacksonEncoder.class}, decoders = {ChatProtocolDecoder.class})
    public ChatProtocol onMessage(ChatProtocol message) throws IOException{

        logger.debug("Author: {} | Users: {} | Msg: {} ", new String[]{message.getAuthor(), users.keySet().toString(), message.getUuid()});
        if ((!users.containsKey(message.getAuthor())) && (message.getAuthor() != "")) {
            users.putIfAbsent(message.getAuthor(), message.getUuid());
            return new ChatProtocol(message.getAuthor(), " entered room " + chatroomName, users.keySet(), getRooms(broadcasterFactory.lookupAll()));
        }

        if (message.getMessage().contains("disconnecting")) {
            users.remove(message.getAuthor());
            return new ChatProtocol(message.getAuthor(), " disconnected from room " + chatroomName, users.keySet(), getRooms(broadcasterFactory.lookupAll()));
        }

        message.setUsers(users.keySet());
        logger.info("{} just sent {}", message.getAuthor(), message.getMessage());
        return new ChatProtocol(message.getAuthor(), message.getMessage(), users.keySet(), getRooms(broadcasterFactory.lookupAll()));
    }
}
