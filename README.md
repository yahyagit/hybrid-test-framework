# **Hybrid Test Framework**

* JVM Arguments

Argument            |Description                |Accepted Values                                      |Example
--------------------|---------------------------|-----------------------------------------------------|---------------------------------------
browser             |Browser type               |chrome, firefox, ie, edge, safari, opera, phantomjs  |-Dbrowser=firefox
grid.enabled        |Is Grid execution          |true?false                                           |-Dgrid.enabled=true
grid.browser.version|Remote browser version     |39                                                   |-Dgrid.browser.version=39
grid.platform       |Remote platform            |windows                                              |-Dgrid.platform=windows
grid.hub.url        |Remote hub URL             |Valid URL                                            |-Dgridhub.url=http://localhost:4444/grid/
proxy.enabled       |Proxy                      |true?false                                           |-Dproxy.enabled=true
proxy.host          |Proxy host URL             |Valid URL                                            |-Dproxy.host=localhost
proxy.port          |Proxy port                 |Integer value                                        |-Dproxy.port=8081
resolution          |Browser resolution         |WIDTHxHEIGHT                                         |-Dresolution=1024x768
start.maximised     |Start browser maximised    |true?false                                           |-Dstart.maximised=true
reuse.browser       |Reuse active browser       |true?false                                           |-Dreuse.browser=true 
user.agent          |User agent                 |Valid key from user.agents.properties csvFilePath    |-Duser.agent=iPhone6
env                 |Target environment         |The prefix of the property csvFilePath               |-Denv=dev