/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.a507.moweb.common.model;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * Map of users registered in the system. This class has a concurrent hash map to store users, using
 * its name as key in the map.
 *
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @authos Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 */
@Component
public class UserManager {

    private final ConcurrentHashMap<String, User> usersByName = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, User> usersBySessionId = new ConcurrentHashMap<>();

    public void register(User user) {
        usersByName.put(user.getName(), user);
        usersBySessionId.put(user.getSessionId(), user);
    }

    public User getByName(String name) {
        return usersByName.get(name);
    }

    public User getBySessionId(String sessionId) {
        return usersBySessionId.get(sessionId);
    }

    public boolean exists(String name) {
        return usersByName.keySet().contains(name);
    }

    public User removeBySession(String sessionId) {
        final User user = getBySessionId(sessionId);
        usersByName.remove(user.getName());
        usersBySessionId.remove(sessionId);
        return user;
    }

}