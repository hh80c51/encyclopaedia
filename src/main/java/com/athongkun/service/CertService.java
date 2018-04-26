package com.athongkun.service;

import java.util.List;
import java.util.Map;

import com.athongkun.bean.Cert;
import com.athongkun.bean.Datas;
import com.athongkun.bean.Page;

public interface CertService {

	Page<Cert> queryCertPage(Map<String, Object> paramMap);

	void insertCert(Cert cert);

	Cert queryById(Integer id);

	int updateCert(Cert cert);

	int deleteById(Integer id);

	int deleteByIds(Datas ds);

	List<Cert> queryAll();

	void insertAccttypeCert(Map<String, Object> paramMap);

	void deleteAccttypeCert(Map<String, Object> paramMap);

	List<Map<String, Object>> queryAccttypeCerts();

}
