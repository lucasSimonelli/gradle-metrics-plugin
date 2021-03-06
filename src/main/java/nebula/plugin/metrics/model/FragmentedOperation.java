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

package nebula.plugin.metrics.model;

import com.google.common.collect.Sets;

import java.util.Collection;
import static com.google.common.base.Preconditions.checkNotNull;

public class FragmentedOperation extends Operation {
    private final String description;
    private final Collection<ContinuousOperation> children = Sets.newHashSet();

    public FragmentedOperation(String description) {
        checkNotNull(description);
        this.description = description;
    }

    @Override
    public long getElapsedTime() {
        long result = 0;
        for (ContinuousOperation child : children) {
            result += child.getElapsedTime();
        }
        return result;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public ContinuousOperation start(long start) {
        ContinuousOperation child = new ContinuousOperation("<child>");
        child.setStart(start);
        children.add(child);
        return child;
    }
}
