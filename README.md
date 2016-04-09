# **Fluent Test Framework**

* JVM Arguments

Argument            |Description                |Accepted Values                            |Example
--------------------|---------------------------|-------------------------------------------|--------------------------
browser             |Browser                    |chrome, firefox                            |-Dbrowser=firefox
resolution          |Browser resolution         |WIDTHxHEIGHT                               |-Dresolution=1024x768
user.agent          |User agent                 |Valid key from user.agents.properties file |-Duser.agent=iPhone6
capability          |Custom browser capability  |Valid                                      |-Dcapability={"startMaximised": "true"}
chrome.bin.version  |Chrome binary version      |2.21                                       |-Dchrome.bin.version=2.21
env                 |Target environment         |The prefix of the property file            |-Denv=dev
grid                |Is Grid execution          |true?false                                 |-Dgrid=true
browser.version     |Remote browser version     |39                                         |-Dbrowser.version=39
platform            |Remote platform            |windows                                    |-Dplatform=windows
hub                 |Remote hub URL             |Valid URL                                  |-Dhub=http://localhost:4444/grid/
