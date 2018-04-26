package ${packageName}.dao;

import java.util.List;
import java.util.Map;

import ${packageName}.bean.Datas;
import ${packageName}.bean.${className};

public interface ${className}Dao {

	List<${className}> query${className}Datas(Map<String, Object> paramMap);

	int query${className}Count(Map<String, Object> paramMap);

	void insert${className}(${className} ${className?lower_case});

	${className} queryById(Integer id);

	int update${className}(${className} ${className?lower_case});

	int deleteById(Integer id);

	int deleteByIds(Datas ds);

	List<${className}> queryAll();

}
