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

package org.gradle.api.internal.tasks;

import org.gradle.api.Action;
import org.gradle.api.Task;
import org.gradle.execution.taskgraph.TaskInfoFactory;
import org.gradle.execution.taskgraph.WorkInfo;

import javax.annotation.Nonnull;

public class TaskInfoWorkResolver implements WorkResolver<WorkInfo> {
    private final TaskInfoFactory taskInfoFactory;

    public TaskInfoWorkResolver(TaskInfoFactory taskInfoFactory) {
        this.taskInfoFactory = taskInfoFactory;
    }

    @Override
    public boolean resolve(Task task, Object node, final Action<? super WorkInfo> resolveAction) {
        return TASK_AS_TASK.resolve(task, node, new Action<Task>() {
            @Override
            public void execute(@Nonnull Task task) {
                resolveAction.execute(taskInfoFactory.getOrCreateNode(task));
            }
        });
    }

}
