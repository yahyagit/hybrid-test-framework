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

package com.atanas.kanchev.testframework.selenium.element.interactions.element;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import com.atanas.kanchev.testframework.selenium.element.interactions.AbstractInteraction;
import org.openqa.selenium.interactions.Actions;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;

/**
 * Created by atanas on 27/09/16.
 */
public abstract class AbstractElementInteraction extends AbstractInteraction {

    protected Actions actions;

    public AbstractElementInteraction(ContextKey<SeleniumContext> currentContextKey) {
        super(currentContextKey);
    }

    protected Actions getActions() {
        if (actions == null)
            return this.actions = new Actions($context().getContext(currentContextKey).getDriver());
        else
            return this.actions;
    }

}
