package diamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import diamonShop.Entity.Menus;
import diamonShop.Entity.MapperMenus;
@Repository
public class MenusDao extends BaseDao {
	
	public List<Menus> GetdataMenus() {
		List<Menus> list = new ArrayList<Menus>();
		String sql = "SELECT * FROM menus";
		list = _jdbcTemplate.query(sql, new MapperMenus());
		return list;
	}

}
