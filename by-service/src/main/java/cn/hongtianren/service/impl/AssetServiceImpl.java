package cn.hongtianren.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hongtianren.dao.AssetMapper;
import cn.hongtianren.dao.EmployeeMapper;
import cn.hongtianren.entity.Asset;
import cn.hongtianren.entity.AssetDeclaration;
import cn.hongtianren.entity.AssetUse;
import cn.hongtianren.entity.ComboboxModel;
import cn.hongtianren.service.AssetService;


/**
 * 
 * @Description 资产管理接口实现
 * @author 谭震弘
 * @time 2017年12月31日
 * @version 1.0
 */
@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetMapper assetMapper;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public PageInfo<AssetDeclaration> findDeclarationList(int pageNum, int pageSize, String username) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<>(assetMapper.findDeclarationList(username));
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void updateStatus(Long id, Integer status) {
		// 更新状态
		Date date = new Date();
		assetMapper.updateStatus(date, id, status);
		AssetDeclaration declaration = assetMapper.findDeclarationById(id);
		// 更新资产状态
		if (status == 1) {
			assetMapper.updateAssertUse(true, date, declaration.getAssetId(), declaration.getEmployeeId());
			// 创建资产使用
			AssetUse assetUse = new AssetUse();
			assetUse.setCreate(date);
			assetUse.setModifyed(date);
			assetUse.setId(id);
			assetUse.setEmployee(declaration.getEmployeeId());
			assetUse.setAsset(declaration.getAssetId());
			if (declaration.getTerm() != null) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, declaration.getTerm());
				assetUse.setExpireDate(calendar.getTime());
			}
			assetMapper.saveUse(assetUse);
		} else {
			assetMapper.updateAssertUse(false, date, declaration.getAssetId(), null);
		}

	}

	@Override
	public PageInfo<Asset> findAssetByUsername(String username, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		return new PageInfo<>(assetMapper.findAssetByUsername(username));
	}

	@Override
	public PageInfo<Asset> findAssetList(int pageNumber, int pageSize, String name) {
		PageHelper.startPage(pageNumber, pageSize);
		return new PageInfo<>(assetMapper.findAssetList(name));

	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void saveDeclare(String username, Long asset, Integer term) {
		Long employeeId = employeeMapper.findEmployeeId(username);
		Date date = new Date();
		AssetDeclaration assetDeclaration = new AssetDeclaration();
		assetDeclaration.setAssetId(asset);
		assetDeclaration.setEmployeeId(employeeId);
		assetDeclaration.setCreate(date);
		assetDeclaration.setModifyed(date);
		assetDeclaration.setTerm(term);
		assetDeclaration.setStatus(0);
		assetMapper.saveDeclare(assetDeclaration);
		assetMapper.updateAssertUse(true, date, asset, null);

	}

	@Override
	public List<ComboboxModel> findCategories() {
		return assetMapper.findCategories();
	}

	@Override
	public void saveAsset(String name, Long category) {
		Asset asset = new Asset();
		asset.setName(name);
		asset.setCategoryId(category);
		Date date = new Date();
		asset.setCreate(date);
		asset.setModifyed(date);
		assetMapper.saveAsset(asset);
	}

	@Override
	public void updateAsset(Asset asset) {
		asset.setModifyed(new Date());
		assetMapper.updateAsset(asset);
	}

	@Override
	public Asset findAssetById(Long id) {
		return assetMapper.findAssetById(id);
	}

	@Override
	public void deletedAssetById(Long id) {
		assetMapper.deletedAssetById(id);
	}

	@Override
	public List<Asset> findAssetListByName(String name) {
		return assetMapper.findAssetListByName(name);
	}

}
