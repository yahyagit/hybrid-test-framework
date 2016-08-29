/*
 * Copyright 2016 Atanas Stoychev Kanchev
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atanas.kanchev.testframework.commons.context;

import com.atanas.kanchev.testframework.commons.exceptions.CustomExceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.currentThread;

public abstract class AbstractContext {

    private static final Logger logger = LoggerFactory.getLogger(AbstractContext.class);
    private String contextName;
    private boolean isContextReusable;

    public AbstractContext(String contextName) {
        this.contextName = contextName + currentThread().getId();
        logger.debug("Creating " + toString());
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        if (contextName == null)
            throw new CustomExceptions.Common.NullArgumentException("Null argument contextName");
        if (contextName.isEmpty())
            throw new CustomExceptions.Common.EmptyArgumentException("Empty argument contextName");
        this.contextName = contextName;
    }

    public boolean isContextReusable() {
        return isContextReusable;
    }

    public void setContextReusable(boolean contextReusable) {
        isContextReusable = contextReusable;
    }

    public abstract ContextKey<?> getContextKey();

    public abstract void tearDownContext();

    @Override public String toString() {
        return "contextName='" + contextName + '\'' + ", isContextReusable=" + isContextReusable
            + ", contextReusable=" + isContextReusable();
    }
}
