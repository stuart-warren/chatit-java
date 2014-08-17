/*
 * Copyright (c) 2014. stuart-warren
 * Last modified: 17/08/14 13:03
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

import java.util.*;

/**
 * Created by stuart-warren on 17/08/14.
 */
public class ChatProtocol implements JacksonEncoder.Encodeable {

    private String message = "";
    private String author = "";
    private long time = System.currentTimeMillis();
    private List<String> users = new ArrayList<String>();
    private List<String> rooms = new ArrayList<String>();
    private String uuid = UUID.randomUUID().toString();

    public ChatProtocol() {
        this("", "");
    }

    public ChatProtocol(String author, String message) {
        this.author = author;
        this.message = message;
        this.time = new Date().getTime();
    }

    public ChatProtocol(Collection<String> users, Collection<String> rooms) {
        this.users.addAll(users);
        this.rooms.addAll(rooms);
    }

    public ChatProtocol(String author, String message, Collection<String> users, Collection<String> rooms) {
        this(author, message);
        this.users.addAll(users);
        this.rooms.addAll(rooms);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(Collection<String> users) {
        this.users.addAll(users);
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<String> rooms) {
        this.rooms.addAll(rooms);
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
