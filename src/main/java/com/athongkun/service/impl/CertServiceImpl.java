package com.athongkun.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athongkun.bean.Cert;
import com.athongkun.bean.Datas;
import com.athongkun.bean.Page;
import com.athongkun.dao.CertDao;
import com.athongkun.service.CertService;

@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private CertDao certDao;

	public Page<Cert> queryCertPage(Map<String, Object> paramMap) {

		Page<Cert> certPage = new Page<Cert>();
		
		List<Cert> certs = certDao.queryCertDatas(paramMap);
		int count = certDao.queryCertCount(paramMap);
		
		certPage.setDatas(certs);
		certPage.setTotalsize(count);
		
		return certPage;
	}

	public void insertCert(Cert cert) {
		certDao.insertCert(cert);
	}

	public Cert queryById(Integer id) {
		return certDao.queryById(id);
	}

	public int updateCert(Cert cert) {
		return certDao.updateCert(cert);
	}

	public int deleteById(Integer id) {
		return certDao.deleteById(id);
	}

	public int deleteByIds(Datas ds) {
		return certDao.deleteByIds(ds);
	}

	public List<Cert> queryAll() {
		return certDao.queryAll();
	}

	public void insertAccttypeCert(Map<String, Object> paramMap) {
		certDao.insertAccttypeCert(paramMap);
	}

	public void deleteAccttypeCert(Map<String, Object> paramMap) {
		certDao.deleteAccttypeCert(paramMap);
	}

	public List<Map<String, Object>> queryAccttypeCerts() {
		return certDao.queryAccttypeCerts();
	}
}
