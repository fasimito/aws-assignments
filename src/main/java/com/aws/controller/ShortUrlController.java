package com.aws.controller;

import com.aws.config.ServerConfig;
import com.aws.domain.ShortUrlBase;
import com.aws.service.ShortUrlBaseService;
import com.aws.utils.RequestLimit;
import com.aws.utils.ShortUrlTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/url")
@Api(value = "Short URL Controller")
public class ShortUrlController {
    private static final Logger logger = LoggerFactory.getLogger(ShortUrlController.class);
    @Autowired
    private ShortUrlBaseService shortUrlBaseService;
    @Autowired
    private ServerConfig serverConfig;
    @ResponseBody
    @RequestMapping(value ="/short", method= RequestMethod.POST)
    @ApiOperation(value="Generate short URL from the original long URL and store into database", notes="short url is right, it could return")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "url", value = "original url start with http/https", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "comments", value = "comments of the url", required = false, dataType = "String")
    })
    public String generateShortUrl(@RequestParam String url,
                                   @RequestParam String comments) {
        logger.info("start generate the short URL, url={},comments={}",url,comments);
        String[] keys = ShortUrlTool.ShortText(url);
        if(keys!=null&&keys.length>0){
            String result = shortUrlBaseService.save(keys,url,comments);
            logger.info("the result is: {}",result);
            return serverConfig.getUrl()+"/url/"+result;
        }else{
            return null;
        }
    }
    @RequestMapping(value="/{key}",method = RequestMethod.GET)
    @RequestLimit(count=10,time=60000)
    @ApiImplicitParam(paramType="query", name = "key", value = "the short URL access key", required = true, dataType = "String")
    public void redirect(@PathVariable String key){
        logger.info("start redirect, the key is {}",key);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        response.setStatus(301);
        String location = "";
        ShortUrlBase base = shortUrlBaseService.selectByKey(key);
        if(base!=null){
            location = base.getRealUrl();
        }
        if(!location.startsWith("http")){
            location = "http://".concat(location);
        }
        logger.info("the location is: {}",location);
        response.setHeader("Location",location);
        try {
            response.sendRedirect(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
