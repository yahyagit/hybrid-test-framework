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

package com.atanas.kanchev.testframework.sikuli.context;

import com.atanas.kanchev.testframework.commons.context.AbstractContext;
import com.atanas.kanchev.testframework.commons.context.ContextKey;
import org.sikuli.script.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SikuliXContext extends AbstractContext {

    private static final Logger logger = LoggerFactory.getLogger(SikuliXContext.class.getName());

    //holds the result of the getImageFilePath operation
    private Match match;

    public SikuliXContext() {
        this("sikuliXCtx_");
    }

    public SikuliXContext(String contextName) {
        super(contextName);
    }

    public Match getMatch() {
        if (match == null)
            logger.warn("The current match is null");
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override public ContextKey<SikuliXContext> getContextKey() {
        return new ContextKey<>(getContextName(), SikuliXContext.class);
    }

    @Override public void tearDownContext() {
        logger.warn("Nothing to tear down"+toString());
    }

    @Override public String toString() {
        return "SikuliXContext{" + "match=" + match + ", contextKey=" + getContextKey() + "} "
            + super.toString();
    }
}
