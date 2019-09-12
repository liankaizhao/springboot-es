package com.zlc.springes;

import com.zlc.springes.domain.Person;
import com.zlc.springes.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEsApplicationTests {


    @Autowired
    public PersonService personService;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Test
    public void contextLoads() {
        Person person=new Person("123456","zlc","西湖区",18,"男","hangzhou");
        personService.save(person);
        Person person1=new Person("98712","张三","余杭区",18,"男","hangzhou");
        IndexQuery indexQuery=new IndexQueryBuilder().withObject(person1).build();
        elasticsearchTemplate.index(indexQuery);

    }

}
