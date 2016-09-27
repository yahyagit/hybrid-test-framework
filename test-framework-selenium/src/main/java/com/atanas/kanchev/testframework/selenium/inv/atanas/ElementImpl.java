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

package com.atanas.kanchev.testframework.selenium.inv.atanas;

import com.atanas.kanchev.testframework.selenium.exceptions.ElementEx;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.List;

/**
 * Created by atanas on 23/09/16.
 */
public class ElementImpl implements IElement {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    protected static final Marker executorMarker = MarkerFactory.getMarker("INTERACTIONS_EXECUTOR");

    protected void throwEx(Throwable throwable) throws RuntimeException {
        logger.error(executorMarker, "Unable to execute " + throwable.toString());
        throw new ElementEx(throwable);
    }


}
