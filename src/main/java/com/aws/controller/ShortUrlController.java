package com.aws.controller;

import com.aws.config.ServerConfig;
import com.aws.domain.ShortUrlBase;
import com.aws.service.ShortUrlBaseService;
import com.aws.utils.RequestLimit;
import com.aws.utils.ShortUrlTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    private ShortUrlBaseService shortUrlBaseService;
    @Autowired
    private ServerConfig serverConfig;
    @ResponseBody
    @RequestMapping(value ="/short", method= RequestMethod.POST)
    @ApiOperation(value="generate Short URL from the original long URL and store into database", notes="short: 仅short url正确返回")
    @ApiImplicitParam(paramType="post", name = "url", value = "原始地址", required = true, dataType = "String")
    public String generateShortUrl(@RequestParam String url,
                                   @RequestParam String comments) {
        String[] keys = ShortUrlTool.ShortText(url);
        if(keys!=null&&keys.length>0){
            String result = shortUrlBaseService.save(keys,url,comments);
            System.out.println("result:"+result);
            return serverConfig.getUrl()+"/url/"+result;
        }else{
            return null;
        }
    }
    @RequestMapping(value="/{key}",method = RequestMethod.GET)
    @RequestLimit(count=10,time=60000)
    public void redirect(@PathVariable String key){
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
        response.setHeader("Location",location);
        try {
            response.sendRedirect(location);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
