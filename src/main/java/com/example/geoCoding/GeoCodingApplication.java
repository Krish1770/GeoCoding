package com.example.geoCoding;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.security.Security;

@SpringBootApplication
public class GeoCodingApplication {



	public static void main(String[] args) {


		Security.addProvider(new BouncyCastleProvider());

		SpringApplication.run(GeoCodingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Bean
	public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
	{
		RedisTemplate<Object,Object> template=new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
         template.setKeySerializer(new StringRedisSerializer());
		 template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
}
