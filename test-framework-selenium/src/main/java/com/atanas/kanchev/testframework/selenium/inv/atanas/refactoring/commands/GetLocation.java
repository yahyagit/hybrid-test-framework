/*
 * Copyright 2016 Atanas Stoychev Kanchev
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.atanas.kanchev.testframework.selenium.inv.atanas.refactoring.commands;

import com.atanas.kanchev.testframework.commons.context.ContextKey;
import com.atanas.kanchev.testframework.selenium.context.SeleniumContext;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriverException;

/**
 * Created by atanas on 27/09/16.
 */
public class GetLocation extends AbstractCommand {

    private Point result;

    public GetLocation(ContextKey<SeleniumContext> currentContextKey) {
        super(currentContextKey);
    }

    public Point getLocation() {
        try {
            return result = element.getLocation();
        } catch (WebDriverException e) {
            throwEx(e);
            return result;
        }
    }
}
