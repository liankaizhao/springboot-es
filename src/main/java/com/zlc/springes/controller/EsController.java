package com.zlc.springes.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zlc.springes.domain.Person;
import com.zlc.springes.domain.Response;
import com.zlc.springes.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaoliancan
 * @version 1.0.0
 * @ClassName EsController.java
 * @Description TODO
 * @createTime 2019年09月12日 09:26:00
 */
@RestController
@RequestMapping("/es")
public class EsController {


    @Autowired
    PersonService personService;


    /**
     * 向es添加数据
     */
    @RequestMapping("/insert")
    public Object insert(@RequestBody Person person){
        JSONObject object=null;
        try {
          Person savePerson=  personService.save(person);
          object= (JSONObject) JSON.toJSON(savePerson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }


    /**
     * 从es删除数据
     * @param person
     * @return
     */
    @RequestMapping("/delete")
    public Object delete(@RequestBody Person person){

        try {
            personService.delete(person);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.failed404();
        }
        return Response.successMsg();

    }


    /**
     * 查询操作
     * @param pageNo
     * @param pageSize 查询大小
     * @param key
     * @return
     */
    @RequestMapping("/query")
    public Object query(@RequestParam(value = "pageNo",required = false)Integer pageNo,@RequestParam(value = "pageSize",required = false)Integer pageSize,
    @RequestParam(value = "key",required = false)String key){

        Response response=new Response();
        try {
            Page<Person> personPage= personService.pageQuery(pageNo,pageSize,key);
            response.setData(personPage);
            response.setMessage("请求成功");
            response.setCode(200);

        } catch (Exception e) {
            e.printStackTrace();
            return Response.failed404();
        }
        return response;

    }
}
