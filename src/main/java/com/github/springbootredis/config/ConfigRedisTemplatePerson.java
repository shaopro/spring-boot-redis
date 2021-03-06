package com.github.springbootredis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.github.springbootredis.pojo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * Created in 15:59 2019-04-01
 * Project name spring-boot-redis
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */


@Configuration
public class ConfigRedisTemplatePerson {

    @Resource
    private RedisConnectionFactory factory;

    /**
     * JDK 序列化方式存储对象
     *
     * @return RedisTemplate
     */
    @Bean("RedisTemplateSerializable")
    public RedisTemplate<String, Person> getRedisTemplateSerialization() {
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 使用 FastJson 方式序列化对象
     *
     * ((LettuceConnectionFactory)factory).setDatabase(1)
     *
     * @return RedisTemplate
     */
    @Bean("RedisTemplateFastJson")
    public RedisTemplate<String, Person> getRedisTemplate() {
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new FastJsonRedisSerializer<>(Person.class));
        template.afterPropertiesSet();
        return template;
    }


}
