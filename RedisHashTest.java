package Redis_Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lst.utils.StringUtil;

import domain.User;
import utils.RandomString;

@ContextConfiguration(locations="classpath:spring-beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisHashTest {
	@Resource
	private RedisTemplate<String, Serializable> redis;
	
	//https://github.com/lancer-lst/uuu.git
	@Test
	public void testHash() {
		/**
		 * 
		 *创建集合存数据
		 */
		List<User> list = new ArrayList<User>();
		/**
		 * 存入十万条数据
		 */
		for (int i = 0; i < 100000; i++) {
			list.add(new User(i,StringUtil.generateChineseName(),i%2==0?"男":"女","13"+RandomString.randomString(9),"@qq.com"+RandomString.randomString(8),"1"+RandomString.randomString(1)));
		}
		
		long starttime = System.currentTimeMillis();
		/**
		 * 存入user
		 */
		for (int i = 0; i < list.size(); i++) {
		   redis.opsForHash().put(""+i, "user", list.get(i));
		}
		
		long stoptime = System.currentTimeMillis();
		/**
		 * 输出时间
		 */
		System.out.println(stoptime-starttime);
	}
}
