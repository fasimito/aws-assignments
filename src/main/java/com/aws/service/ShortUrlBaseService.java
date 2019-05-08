package com.aws.service;

import com.aws.domain.ShortUrlBase;

import java.util.List;

public interface ShortUrlBaseService {
    String save(String[] keys, String url, String comments);
    List<ShortUrlBase> pageQueryShortUrls(int currPage, int pageSize);
    ShortUrlBase selectByKey(String shortKey);
}
