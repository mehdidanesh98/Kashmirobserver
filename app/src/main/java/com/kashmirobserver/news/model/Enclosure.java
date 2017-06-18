package com.kashmirobserver.news.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by YOoHOo on 2017/06/17.
 */
@Root(name = "enclosure", strict = false)
public class Enclosure {
    @Attribute(name = "url")
    public String url;

    @Attribute(name = "length")
    public String length;

    @Attribute(name = "type")
    public String type;
}
