/*
 * Copyright (c) 2014. stuart-warren
 * Last modified: 13/08/14 21:17
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

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by stuart-warren on 13/08/14.
 */
@XmlRootElement
public class Message {

    public String author = "";
    public String message = "";

    public Message() {}

    public Message(String author, String message) {
        this.author = author;
        this.message = message;
    }
}
