package com.atanas.kanchev.testframework.selenium.handlers;

/**
 * Web Handler Interface
 */
public interface IWebHandler extends IBaseHandler{

    default Wait waitFor() {
        return new Wait();
    }

    default GoTo goTo() {
        return new GoTo();
    }

    default Driver setup(){return new Driver();}


    class Wait implements IWait {
    }

    class GoTo implements INavigate {
    }

    class Driver implements IDriver{}

}

