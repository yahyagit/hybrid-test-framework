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

package com.atanas.kanchev.testframework.selenium.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;


import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;
import static java.util.Arrays.asList;

class SelenideElementProxy implements InvocationHandler {
  private static final Set<String> methodsToSkipLogging = new HashSet<>(asList(
      "toWebElement",
      "toString"
  ));

  private static final Set<String> methodsForSoftAssertion = new HashSet<>(asList(
      "should",
      "shouldBe",
      "shouldHave",
      "shouldNot",
      "shouldNotHave",
      "shouldNotBe",
      "waitUntil",
      "waitWhile"
  ));

  private final WebElementSource webElementSource;
  
  protected SelenideElementProxy(WebElementSource webElementSource) {
    this.webElementSource = webElementSource;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
    if (methodsToSkipLogging.contains(method.getName())){
      System.out.println(">>>>>>>>>>>>>>>>>>>>" );
      return Commands.collection.execute(proxy, webElementSource, method.getName(), args);
    }
//      return Commands.collection.execute(proxy, webElementSource, method.getName(), args);

    Object result = null;

//    validateAssertionMode();

//    long timeoutMs = getTimeoutMs(method, args);
//    long pollingIntervalMs = getPollingIntervalMs(method, args);
//    SelenideLog log = SelenideLogger.beginStep(webElementSource.getSearchCriteria(), method.getName(), args);
    try {
      result = dispatchAndRetry(5000, 1000, proxy, method, args);
      System.out.println(">>>>>>>>>>>>>>>>>>>>" + result.toString());
//      SelenideLogger.commitStep(log, PASS);
      return result;
    }
    catch (Error error) {
//      SelenideLogger.commitStep(log, error);
//      if (assertionMode == SOFT && methodsForSoftAssertion.contains(method.getName()))
//        return proxy;
//      else
//        throw UIAssertionError.wrap(error, timeoutMs);
    }
    catch (RuntimeException error) {
//      SelenideLogger.commitStep(log, error);
      throw error;
    }
    return result;
  }

  protected Object dispatchAndRetry(long timeoutMs, long pollingIntervalMs, 
                                    Object proxy, Method method, Object[] args) throws Throwable {
    final long startTime = currentTimeMillis();
    Throwable lastError;
    do {
      try {
        if (SelenideElement.class.isAssignableFrom(method.getDeclaringClass())) {
          return Commands.collection.execute(proxy, webElementSource, method.getName(), args);
        }

        return method.invoke(webElementSource.getWebElement(), args);
      }
      catch (InvocationTargetException e) {
        lastError = e.getTargetException();
      }
      catch (Throwable e) {
        lastError = e;
      }
//      if (Cleanup.of.isInvalidSelectorError(lastError)) {
//        throw Cleanup.of.wrap(lastError);
//      }
      sleep(pollingIntervalMs);
    }
    while (currentTimeMillis() - startTime <= timeoutMs);

//    if (lastError instanceof UIAssertionError) {
//      throw lastError;
//    }
//    else if (lastError instanceof InvalidElementStateException) {
////      throw new InvalidStateException(lastError);
//    }
//    else if (lastError instanceof WebDriverException) {
////      throw webElementSource.createElementNotFoundError(exist, lastError);
//    }
    throw lastError;
  }

  private long getTimeoutMs(Method method, Object[] args) {
    return isWaitCommand(method) ? 
        args.length == 3 ? (Long) args[args.length - 2] : (Long) args[args.length - 1] : 
        3000;
  }

  private long getPollingIntervalMs(Method method, Object[] args) {
    return isWaitCommand(method) && args.length == 3 ? (Long) args[args.length - 1] : 500;
  }

  private boolean isWaitCommand(Method method) {
    return "waitUntil".equals(method.getName()) || "waitWhile".equals(method.getName());
  }
}
