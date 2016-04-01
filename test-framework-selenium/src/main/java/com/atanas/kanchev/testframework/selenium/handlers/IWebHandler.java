package com.atanas.kanchev.testframework.selenium.handlers;

/**
 * Web Handler Interface
 */
public interface IWebHandler extends IBaseHandler {

    default Wait waitFor() {
        return new Wait();
    }

    default GoTo goTo() {
        return new GoTo();
    }

    default Driver setup() {
        return new Driver();
    }

    default Probe probe() {
        return new Probe();
    }


    class Wait implements IWait, INavigation {
    }

    class GoTo implements INavigation {
    }

    class Driver implements IDriver {
    }

    class Probe implements IProbeElement {
    }


}

