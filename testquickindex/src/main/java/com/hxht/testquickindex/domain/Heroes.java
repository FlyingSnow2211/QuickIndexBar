package com.hxht.testquickindex.domain;

import com.hxht.testquickindex.utils.PinyinUtils;

public class Heroes implements Comparable<Heroes>{

    private String name;
    private String pinyin;

    public Heroes(String name) {
        this.name = name;
        this.pinyin = PinyinUtils.getPinyinOfWord(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(Heroes another) {
        return this.pinyin.compareTo(another.getPinyin());
    }
}
