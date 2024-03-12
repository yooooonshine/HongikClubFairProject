package hongikclubfair.meetingproject.common.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableCaching
@RequiredArgsConstructor
public class RedisCacheConfig {

	private final RedisConnectionFactory redisConnectionFactory;

	@Bean
	public CacheManager cacheManager() {

		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
			.disableCachingNullValues()
			.serializeKeysWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new StringRedisSerializer()))
			.serializeValuesWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new GenericJackson2JsonRedisSerializer()))
			.entryTtl(Duration.ofMinutes(10L));

		return RedisCacheManager.RedisCacheManagerBuilder
			.fromConnectionFactory(redisConnectionFactory)
			.cacheDefaults(configuration)
			.build();
	}
}
