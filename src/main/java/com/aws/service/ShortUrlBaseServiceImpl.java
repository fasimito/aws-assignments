package com.aws.service;

import com.aws.domain.ShortUrlBase;
import com.aws.mapper.ShortUrlBaseMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("shortUrlBaseService")
public class ShortUrlBaseServiceImpl implements ShortUrlBaseService{

    @Autowired
    private ShortUrlBaseMapper shortUrlBaseMapper;
    @Override
    public String save(String[] keys, String url, String comments) {
        String result ="";
        int i = 0;
        int data = 0;
        for(i=0; i<keys.length; i++){
            ShortUrlBase base = new ShortUrlBase();
            base.setShortKey(keys[i]);
            base.setRealUrl(url);
            base.setComments(comments);
            try {
                // if success the data = 1
                data = shortUrlBaseMapper.insert(base);
            }catch (Exception ex){
                ex.printStackTrace();
                result = keys[i];
                if(ex instanceof MySQLIntegrityConstraintViolationException){
                    // means the key had been insert into the db;
                }
                break;
            }
            result = keys[i];
        }
        return result;
    }

    @Override
    public List<ShortUrlBase> pageQueryShortUrls(int currPage, int pageSize) {
        return null;
    }

    @Override
    public ShortUrlBase selectByKey(String shortKey) {
        ShortUrlBase urlBase = shortUrlBaseMapper.selectByShortKey(shortKey);
        return urlBase;
    }
}
