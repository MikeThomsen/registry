/*
 * Copyright 2016 Hortonworks.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hortonworks.registries.schemaregistry.client;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This class implements round robin strategy to select a url with the given list of urls.
 */
public class RoundRobinUrlSelector extends AbstractUrlSelector {

    private final AtomicLong currentIndex = new AtomicLong(0L);

    public RoundRobinUrlSelector(String clusterUrl) {
        super(clusterUrl);
    }

    @Override
    public String select() {
        return urls[(int) (currentIndex.getAndIncrement() % urls.length)];
    }

    @Override
    public void urlWithError(String url, Exception e) {
        // can be ignored
    }

}
