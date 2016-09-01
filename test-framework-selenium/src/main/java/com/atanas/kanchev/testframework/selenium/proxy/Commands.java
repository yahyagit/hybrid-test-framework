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

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Commands {
  public static Commands collection = new Commands();

  private final Map<String, Command> commands = new ConcurrentHashMap<>(128);

  public Commands() {
    resetDefaults();
  }

  public final void resetDefaults() {
    synchronized (this) {
      commands.clear();
      addFindCommands();
//      addClickCommands();
//      addModifyCommands();
//      addInfoCommands();
//      addSelectCommands();
//      addKeyboardCommands();
//      addActionsCommands();
//      addShouldCommands();
//      addShouldNotCommands();
//      addFileCommands();
//      addTechnicalCommands();
    }
  }

//  private void addTechnicalCommands() {
//    commands.put("toString", new ToString());
//    commands.put("toWebElement", new ToWebElement());
//    commands.put("getWrappedElement", new GetWrappedElement());
//    commands.put("screenshot", new TakeScreenshot());
//    commands.put("screenshotAsImage", new TakeScreenshotAsImage());
//  }
//
//  private void addActionsCommands() {
//    commands.put("dragAndDropTo", new DragAndDropTo());
//    commands.put("hover", new Hover());
//    commands.put("scrollTo", new ScrollTo());
//  }
//
//  private void addInfoCommands() {
//    commands.put("attr", new GetAttribute());
//    commands.put("data", new GetDataAttribute());
//    commands.put("exists", new Exists());
//    commands.put("innerText", new GetInnerText());
//    commands.put("innerHtml", new GetInnerHtml());
//    commands.put("has", new Matches());
//    commands.put("is", new Matches());
//    commands.put("isDisplayed", new IsDisplayed());
//    commands.put("isImage", new IsImage());
//    commands.put("getText", new GetText());
//    commands.put("name", new GetName());
//    commands.put("text", new GetText());
//    commands.put("getValue", new GetValue());
//  }
//
//  private void addClickCommands() {
//    commands.put("click", new Click());
//    commands.put("contextClick", new ContextClick());
//    commands.put("doubleClick", new DoubleClick());
//    commands.put("followLink", new FollowLink());
//  }
//
  private void addModifyCommands() {
//    commands.put("selectRadio", new SelectRadio());
//    commands.put("setSelected", new SetSelected());
//    commands.put("setValue", new SetValue());
//    commands.put("val", new Val());
//    commands.put("append", new Append());
  }

  private void addFindCommands() {
    commands.put("find", new Find());
//    commands.put("$", new Find());
//    commands.put("findAll", new FindAll());
//    commands.put("$$", new FindAll());
//    commands.put("closest", new GetClosest());
//    commands.put("parent", new GetParent());
  }

//  private void addKeyboardCommands() {
//    commands.put("pressEnter", new PressEnter());
//    commands.put("pressEscape", new PressEscape());
//    commands.put("pressTab", new PressTab());
//  }
//
//  private void addSelectCommands() {
//    commands.put("getSelectedOption", new GetSelectedOption());
//    commands.put("getSelectedText", new GetSelectedText());
//    commands.put("getSelectedValue", new GetSelectedValue());
//    commands.put("selectOption", new SelectOptionByTextOrIndex());
//    commands.put("selectOptionByValue", new SelectOptionByValue());
//  }
//
//  private void addFileCommands() {
//    commands.put("download", new DownloadFile());
//    commands.put("uploadFile", new UploadFile());
//    commands.put("uploadFromClasspath", new UploadFileFromClasspath());
//  }
//
//  private void addShouldNotCommands() {
//    commands.put("shouldNot", new ShouldNot());
//    commands.put("shouldNotHave", new ShouldNotHave());
//    commands.put("shouldNotBe", new ShouldNotBe());
//    commands.put("waitWhile", new ShouldNotBe());
//  }
//
//  private void addShouldCommands() {
//    commands.put("should", new Should());
//    commands.put("shouldHave", new ShouldHave());
//    commands.put("shouldBe", new ShouldBe());
//    commands.put("waitUntil", new ShouldBe());
//  }

  public void add(String method, Command command) {
    synchronized (this) {
      commands.put(method, command);
    }
  }

  @SuppressWarnings("unchecked")
  public <T> T execute(Object proxy, WebElementSource webElementSource, String methodName, Object[] args) throws IOException {
    Command command = commands.get(methodName);
    if (command == null) {
      throw new IllegalArgumentException("Unknown Selenide method: " + methodName);
    }
    return (T) command.execute((SelenideElement) proxy, webElementSource, args);
  }
}
