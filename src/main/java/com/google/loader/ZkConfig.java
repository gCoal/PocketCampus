package com.google.loader;

import com.longrise.interfaces.ZkConf;
import org.springframework.stereotype.Component;

@Component("ZkConf")
public class ZkConfig implements ZkConf {

    @Override
    public String ProjectSpace() {
        return "newJerseyTest";
    }

    @Override
    public String[] RemoteProject() {
        return new String[]{"JerseyDemo"};
    }
}
