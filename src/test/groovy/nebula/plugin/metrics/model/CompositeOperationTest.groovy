/*
 * Copyright 2011 the original author or authors.
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
package nebula.plugin.metrics.model

import nebula.plugin.metrics.model.CompositeOperation
import nebula.plugin.metrics.model.Operation
import spock.lang.Specification
import spock.lang.Subject

@Subject(CompositeOperation)
class CompositeOperationTest extends Specification {

    def "execution time is sum of execution time of child operations"() {
        given:
        Operation child1 = operation(12)
        Operation child2 = operation(10)
        def operation = new CompositeOperation<Operation>([child1, child2])

        expect:
        operation.elapsedTime == 22
    }

    def operation(long elapsedTime) {
        Operation operation = Mock()
        _ * operation.elapsedTime >> elapsedTime
        return operation
    }
}