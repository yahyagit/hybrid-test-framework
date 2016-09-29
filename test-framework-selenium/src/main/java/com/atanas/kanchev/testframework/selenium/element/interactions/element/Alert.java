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
import com.atanas.kanchev.testframework.selenium.driverfactory.Defaults;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.atanas.kanchev.testframework.commons.accessors.ContextsAccessor.$context;

/**
 * Created by atanas on 27/09/16.
 */
public class Alert extends AbstractElementInteraction implements org.openqa.selenium.Alert {

    private final org.openqa.selenium.Alert alert;

    public Alert(ContextKey<SeleniumContext> currentContextKey) {
        super(currentContextKey);
        try {
            new WebDriverWait($context().getContext(currentContextKey).getDriver(),
                Defaults.DEF_IMPL_WAIT).until(ExpectedConditions.alertIsPresent());
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
        alert = $context().getContext(currentContextKey).getDriver().switchTo().alert();
    }

    public void handleAlert(boolean accept) {

        if (accept)
            alert.accept();
        else
            alert.dismiss();

    }

    @Override public void dismiss() {
        alert.dismiss();
    }

    @Override public void accept() {
        alert.accept();
    }

    @Override public String getText() {
        return alert.getText();
    }

    @Override public void sendKeys(String keysToSend) {
        alert.sendKeys(keysToSend);
    }

    @Override public void setCredentials(Credentials credentials) {
        alert.setCredentials(credentials);
    }

    @Override public void authenticateUsing(Credentials credentials) {
        alert.authenticateUsing(credentials);
    }
}
