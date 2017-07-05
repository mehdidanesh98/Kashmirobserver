package com.kashmirobserver.news.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by YOoHOo on 2017/07/06.
 */
@Root(name = "source", strict = false)
public class Source {
    @Attribute(name = "url")
    public String url;
}
