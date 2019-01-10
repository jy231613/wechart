package com.qb.wxui.page;

import static com.qb.wxui.page.WebActivity.WebType.web_page;

/**
 * 网页实体类
 */
public class WebEvent {
    private String url;
    private String title;
    private String tag;
    private String jsStr;
    private WebActivity.WebType type = web_page;

    public String getJsStr() {
        return jsStr;
    }

    public void setJsStr(String jsStr) {
        this.jsStr = jsStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public WebActivity.WebType getType() {
        return type;
    }

    public void setType(WebActivity.WebType type) {
        this.type = type;
    }
}