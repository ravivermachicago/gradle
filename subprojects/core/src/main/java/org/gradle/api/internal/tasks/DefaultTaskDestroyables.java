/*
 * Copyright 2017 the original author or authors.
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

package org.gradle.api.internal.tasks;

import com.google.common.collect.Lists;
import org.gradle.api.NonNullApi;
import org.gradle.util.DeprecationLogger;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NonNullApi
public class DefaultTaskDestroyables implements TaskDestroyablesInternal {
    private final TaskMutator taskMutator;
    private final List<Object> registeredPaths = Lists.newArrayList();

    public DefaultTaskDestroyables(TaskMutator taskMutator) {
        this.taskMutator = taskMutator;
    }

    @Override
    public void files(final Object... paths) {
        DeprecationLogger.nagUserOfReplacedMethod("TaskDestroys.files", "TaskDestroys.register");
        taskMutator.mutate("TaskDestroys.files(Object...)", new Runnable() {
            @Override
            public void run() {
                Collections.addAll(DefaultTaskDestroyables.this.registeredPaths, paths);
            }
        });
    }

    @Override
    public void file(final Object path) {
        DeprecationLogger.nagUserOfReplacedMethod("TaskDestroys.file", "TaskDestroys.register");
        taskMutator.mutate("TaskDestroys.file(Object...)", new Runnable() {
            @Override
            public void run() {
                registeredPaths.add(path);
            }
        });
    }

    @Override
    public void register(final Object... paths) {
        taskMutator.mutate("TaskDestroys.register(Object...)", new Runnable() {
            @Override
            public void run() {
                Collections.addAll(DefaultTaskDestroyables.this.registeredPaths, paths);
            }
        });
    }

    @Override
    public Collection<Object> getRegisteredPaths() {
        return registeredPaths;
    }

}
