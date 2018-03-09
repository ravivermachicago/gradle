/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.internal.logging.sink;

import org.gradle.internal.event.ListenerManager;
import org.gradle.internal.logging.events.OutputEventListener;

public class OutputEventListenerManager {

    private final ListenerManager listenerManager;
    private final OutputEventListener broadcaster;

    public OutputEventListenerManager(ListenerManager listenerManager) {
        this.listenerManager = listenerManager;
        this.broadcaster = listenerManager.getBroadcaster(OutputEventListener.class);
    }

    public void addListener(OutputEventListener listener) {
        listenerManager.addListener(listener);
    }

    public void removeListener(OutputEventListener listener) {
        listenerManager.removeListener(listener);
    }

    public OutputEventListener getBroadcaster() {
        return broadcaster;
    }
}
