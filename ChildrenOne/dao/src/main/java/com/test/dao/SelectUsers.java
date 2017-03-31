package com.test.dao;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.test.commons.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class SelectUsers {

	private static final String QUERY = "SELECT u.id AS id, u.name AS name FROM lesson2.Users as u";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Cache<String, List<User>> build;

	public List<User> selectAllUsers() {

		List<User> users = jdbcTemplate.query(QUERY, new RowMapper<User>() {

			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
                System.out.println(String.format("мы пошли за юзером %s", user ));
				return user;
			}

		}, new Object[] {}

		);

		return users;
	}

	@PostConstruct
	public void init(){
		this.build = CacheBuilder.newBuilder().
				maximumSize(1000).
				expireAfterWrite(10, TimeUnit.MINUTES).
				build(new CacheLoader<String, List<User>>() {
					public List load(String key) {
						return selectAllUsers();
					}
				});
	}



	public List<User> selectCachedUsers() {

		try {
			return build.get("test");
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


		return null;
	}

}
