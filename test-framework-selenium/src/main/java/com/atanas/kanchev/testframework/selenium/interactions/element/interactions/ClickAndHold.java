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

package com.atanas.kanchev.testframework.selenium.interactions.element.interactions;

import com.atanas.kanchev.testframework.selenium.interactions.element.OmniaElement;
import org.openqa.selenium.interactions.Actions;

import static com.atanas.kanchev.testframework.selenium.interactions.element.OmniaElement.omniaElement;

/**
 * @author Atanas Kanchev
 */
public class ClickAndHold extends AbstractElementInteraction {

    private static void sleep(long sleep) {
        //TODO make wait generic
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public OmniaElement clickAndHold(long duration) {
        new ElementExecutor<OmniaElement>() {

            @Override public OmniaElement execute() {
                logger.debug("Click and Hold Element");
                try {
                    Actions actions = new Actions(driver);
                    actions.clickAndHold(element).perform();
                    sleep(duration);
                    actions.release(element).perform();
                } catch (Exception e) {
                    throwEx(e);
                }

                return omniaElement;
            }
        };
        return omniaElement;
    }

}
