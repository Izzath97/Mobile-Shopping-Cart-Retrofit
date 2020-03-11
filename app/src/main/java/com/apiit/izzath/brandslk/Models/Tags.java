package com.apiit.izzath.brandslk.Models;

import com.orm.SugarRecord;

/**
 * Created by Izzath on 5/12/2018.
 */

public class Tags extends SugarRecord<Tags> {
    String tagid;
    String tagName;

    public Tags() {
    }

    public Tags(String tagid, String tagName) {
        this.tagid = tagid;
        this.tagName = tagName;
    }

    public void setTagid(String tagid) {
        this.tagid = tagid;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagid() {
        return tagid;
    }

    public String getTagName() {
        return tagName;
    }
}
