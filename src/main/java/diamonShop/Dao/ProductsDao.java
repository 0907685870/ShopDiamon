package diamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import diamonShop.Dto.ProductsDto;
import diamonShop.Dto.ProductsDtoMapper;
import diamonShop.Entity.MapperCategorys;

@Repository
public class ProductsDao extends BaseDao {
	protected final boolean YES = true;
	protected final boolean NO = false;

	private StringBuffer sqlString() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("p.id as id_product ");
		sql.append(",p.id_category ");
		sql.append(",p.sizes ");
		sql.append(",p.name ");
		sql.append(",p.price ");
		sql.append(",p.sale ");
		sql.append(",p.title ");
		sql.append(",p.highlight ");
		sql.append(",p.new_product ");
		sql.append(",p.details ");
		sql.append(",c.id AS id_color ");
		sql.append(",c.name AS name_color ");
		sql.append(",c.code AS code_color ");
		sql.append(",p.created_at ");
		sql.append(",p.updated_at ");
		sql.append(",c.img ");
		sql.append("FROM products AS p ");
		sql.append("INNER JOIN ");
		sql.append("colors AS c ");
		sql.append("ON p.id = c.id_product ");

		return sql;
	}

	public String sqlnewProducts(boolean newproducts, boolean highlight) {
		StringBuffer sql = sqlString();
		sql.append("WHERE 1 = 1 ");
		if (highlight) {
			sql.append("AND p.highlight = true ");
		}
		if (newproducts) {
			sql.append("AND p.new_product = true ");
		}
		sql.append("GROUP BY p.id, c.id_product ");
		sql.append("ORDER BY RAND() ");
		if (highlight) {
			sql.append("LIMIT 9 ");
		}
		if (newproducts) {
			sql.append("LIMIT 12 ");
		}
		return sql.toString();
	}

	public StringBuffer sqlProductsById(int id) {
		StringBuffer sql = sqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND id_category = " + id + " ");
		return sql;
	}

	public String sqlProductsPaginate(int id, int start, int totalPage) {
		StringBuffer sql = sqlProductsById(id);
		sql.append("LIMIT " + start + ", " + totalPage);
		return sql.toString();
	}

	public List<ProductsDto> GetdataProducts() {
		String sql = sqlnewProducts(NO, YES);
		List<ProductsDto> list = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return list;
	}

	public List<ProductsDto> GetdatanewProducts() {
		String sql = sqlnewProducts(YES, NO);
		List<ProductsDto> list = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return list;
	}

	public List<ProductsDto> GetAllProductsById(int id) {
		String sql = sqlProductsById(id).toString();
		List<ProductsDto> listProducts = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return listProducts;
	}

	public List<ProductsDto> GetDataProductPaginate(int id, int start, int totalPage) {
		StringBuffer sqlGetDataById = sqlProductsById(id);
		List<ProductsDto> listProductsById = _jdbcTemplate.query(sqlGetDataById.toString(), new ProductsDtoMapper());
		List<ProductsDto> listProducts = new ArrayList<ProductsDto>();
		if(listProductsById.size() > 0) {
			String sql = sqlProductsPaginate(id,start,totalPage);
			listProducts = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		}
		return listProducts;
	}

	private String sqlProductById( long id) {
		StringBuffer sql = sqlString();
		sql.append("WHERE 1 = 1 ");
		sql.append("AND p.id = " + id +" ");
		sql.append("LIMIT 1 ");
		return sql.toString();
	}
	public List<ProductsDto> GetProductById(long id) {
		String sql = sqlProductById(id);
		List<ProductsDto> listProduct = _jdbcTemplate.query(sql, new ProductsDtoMapper());
		return listProduct;
	}
	
	public ProductsDto FindProductById(long id) {
		String sql = sqlProductById(id);
		ProductsDto product = _jdbcTemplate.queryForObject(sql, new ProductsDtoMapper());
		return product;
	}
}
