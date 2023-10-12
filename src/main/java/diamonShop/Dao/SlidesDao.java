package diamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import diamonShop.Entity.MapperSlides;
import diamonShop.Entity.Slides;

@Repository
public class SlidesDao extends BaseDao{
public List<Slides> GetdataSlides(){
	List<Slides> list = new ArrayList<Slides>();
	String sql = "SELECT * FROM slides";
	list = _jdbcTemplate.query(sql, new MapperSlides());
	return list;
}
}
