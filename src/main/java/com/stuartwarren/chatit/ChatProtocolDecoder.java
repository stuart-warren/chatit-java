/*
 * Copyright (c) 2014. stuart-warren
 * Last modified: 17/08/14 11:52
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

import org.atmosphere.config.managed.Decoder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by stuart-warren on 17/08/14.
 */
public class ChatProtocolDecoder implements Decoder<String, ChatProtocol> {

    private final ObjectMapper mapper = new ObjectMapper();

    public ChatProtocol decode(String s) {
        try {
            return mapper.readValue(s, ChatProtocol.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
